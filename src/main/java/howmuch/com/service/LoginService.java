package howmuch.com.service;


import java.util.List;
import java.util.Optional;

import howmuch.com.dto.MailDTO;
import howmuch.com.dto.UsersDTO;


public interface LoginService {
    // 사용자를 username으로 조회하는 예시
    public UsersDTO getUserByUserId(String userId);
    public int saveUser(String userId, String password, String name, String email);
    public int modifyUser(String userId, String password, String name, String email);
    public int createUser(String userId, String password, String name, String email);
    public Boolean sendMail(MailDTO mailDTO);
    public int modifyLoginFailNum(String userId);
    public int modifyLoginFailNumReset(String userId);
    public UsersDTO validateEmail(String email);
}
