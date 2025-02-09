package howmuch.com.service;


import java.util.List;
import java.util.Optional;

import howmuch.com.dto.MailDTO;
import howmuch.com.dto.UsersDTO;


public interface LoginService {
    // 사용자를 username으로 조회하는 예시
    public UsersDTO getUserByUserId(String userId);
    public void saveUser(String userId, String password, String name, String email);
    public void modifyUser(String userId, String password, String name, String email);
    public void createUser(String userId, String password, String name, String email);
    public void sendMail(MailDTO mailDTO);
    public void modifyLoginFailNum(String userId);
    public void modifyLoginFailNumReset(String userId);
    public UsersDTO validateEmail(String email);
}
