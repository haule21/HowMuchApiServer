package howmuch.com.service;


import java.util.List;
import java.util.Optional;

import howmuch.com.dto.UsersDTO;


public interface LoginService {
    // 사용자를 username으로 조회하는 예시
    public UsersDTO getUserByUserId(String userId);
    // 새로운 사용자 추가 예시
    public void createUser(String userId, String password);
}
