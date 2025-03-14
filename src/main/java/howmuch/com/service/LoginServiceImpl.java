package howmuch.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import howmuch.com.dto.MailDTO;
import howmuch.com.dto.UsersDTO;
import howmuch.com.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	@Value("${spring.mail.username}")
    private String from;
	
	@Autowired
	private final JavaMailSender javaMailSender;
	
	@Autowired
	@Lazy
    private UserRepository userRepository;

	@Override
    public UsersDTO getUserByUserId(String userId) {
		UsersDTO user;
		user = userRepository.findByUserId(userId);
        return user;
    }

	@Override
    // 새로운 사용자 추가 예시
    public int createUser(String userId, String password, String name, String email) {
		UsersDTO user = new UsersDTO();
        user.setUserId(userId);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setName(name);
        user.setEmail(email);
        user.setStoreNumber(null);
        user.setROLE("USER");
        user.setSubscriptionId(null);
        
        return userRepository.save(user);  // 데이터베이스에 저장
    }
	
	@Override
    public int saveUser(String userId, String password, String name, String email) {
		UsersDTO user = new UsersDTO();
        user.setUserId(userId);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setName(name);
        user.setEmail(email);
        user.setStoreNumber(null);
        user.setROLE("USER");
        user.setSubscriptionId(null);
        
        return userRepository.save(user);  // 데이터베이스에 저장
    }
	
	@Override
    public int modifyLoginFailNum(String userId) {
        return userRepository.modifyLoginFailNum(userId);  // 데이터베이스에 저장
    }
	
	@Override
    public int modifyLoginFailNumReset(String userId) {
        return userRepository.modifyLoginFailNumReset(userId);  // 데이터베이스에 저장
    }
	
	@Override
	public Boolean sendMail(MailDTO mailDTO) {	
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailDTO.getTo());
        mailMessage.setSubject(mailDTO.getTitle());
        mailMessage.setText(mailDTO.getMessage());
        mailMessage.setFrom(from);
        mailMessage.setReplyTo(from);
        javaMailSender.send(mailMessage);
        return true;
	}
	@Override
	public UsersDTO validateEmail(String email) {
		return userRepository.validateEmail(email);
	}
	@Override
	public int modifyUser(String userId, String password, String name, String email) {
		UsersDTO user = new UsersDTO();
        user.setUserId(userId);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setName(name);
        user.setEmail(email);
        user.setStoreNumber(null);
        user.setROLE("GUEST");
        user.setSubscriptionId(null);
        
        return userRepository.modifyUser(user);  // 데이터베이스에 저장
	}
	
}
