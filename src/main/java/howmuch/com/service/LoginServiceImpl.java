package howmuch.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import howmuch.com.dto.UsersDTO;
import howmuch.com.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class LoginServiceImpl implements LoginService {
	
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
	@Transactional
    // 새로운 사용자 추가 예시
    public void createUser(String userId, String password) {
		UsersDTO user = new UsersDTO();
        user.setUserId(userId);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setName(null);
        user.setStoreNumber(null);
        user.setROLE("GUEST");
        user.setSubscriptionId(null);
        
        System.out.println(
        		user.getUserId() + 
            	user.getPassword() + 
            	user.getName() +
            	user.getStoreNumber() +
            	user.getROLE() +
            	user.getSubscriptionId()
        );
        userRepository.save(user);  // 데이터베이스에 저장
    }
}
