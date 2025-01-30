package howmuch.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import howmuch.com.dto.UsersDTO;
import howmuch.com.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private final UserRepository userRepository;
    
    public CustomUserDetailsService(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UsersDTO user;
		user = userRepository.findByUserId(userId);
		 if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

	    // DB에서 가져온 사용자 정보로 UserDetails 객체 생성
        return User.builder()
            .username(user.getUserId())
            .password(user.getPassword())
            .roles(user.getROLE())  // 예시로 사용자에게 할당된 roles를 설정
            .build();
    }
}
