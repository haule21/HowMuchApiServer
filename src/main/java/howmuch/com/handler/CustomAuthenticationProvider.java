package howmuch.com.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import howmuch.com.dto.UsersDTO;
import howmuch.com.repository.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private final UserRepository userRepository;
    
    public CustomAuthenticationProvider(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        
        if (isCredentialsValid(name, password)) {
            return authenticateAgainstThirdPartyAndGetAuthentication(name, password);
        } else {
            throw new BadCredentialsException("잘못된 사용자 정보입니다.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
    
    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password) {
        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }
    
    private Boolean isCredentialsValid(String username, String password) {
        // UserRepository는 DB에서 사용자 정보를 조회하는 리포지토리라고 가정
        UsersDTO user = userRepository.findByUserId(username);
        
        if (user == null) {
        	throw new UsernameNotFoundException("잘못된 사용자 정보입니다.");
        } else {
        	if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
        		if (user.getLoginFailNum() > 5) {
            		throw new LockedException("계정이 잠겨있습니다. 패스워드 찾기를 통해 초기화 하여주세요.");
            	} else {
            		return true;
            	}
        	}
        }
        
        return false;
    }
}
