package howmuch.com.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import howmuch.com.dto.UsersDTO;
import howmuch.com.mapper.UserRowMapper;


@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 사용자 저장
    public void save(UsersDTO user) {
    	String procedureName = "sp_CREATE_USER";

        // 저장 프로시저 파라미터 설정
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("UserId", user.getUserId());
//        params.put("Password", user.getPassword());
//        params.put("Name", user.getName());
//        params.put("StoreNumber", user.getStoreNumber());
//        params.put("ROLE", user.getROLE());
//        params.put("Email", user.getEmail());
//        params.put("SubscriptionId", user.getSubscriptionId());

        // 저장 프로시저 실행
        jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @Password = ?, @Name = ?, @Email = ?, @StoreNumber = ?, @ROLE = ?, @SubscriptionId = ?",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), user.getStoreNumber(), user.getROLE(), user.getSubscriptionId());
    }

    // 사용자 조회 (id 기준)
    public UsersDTO findByUserId(String userId) {
    	String procedureName = "sp_FIND_USER_BY_USERID ?";
        List<UsersDTO> users = jdbcTemplate.query(procedureName, new UserRowMapper(), userId);
        return users.isEmpty() ? null : users.get(0);
    }
    
    public UsersDTO validateEmail(String email) {
    	String procedureName = "sp_CHECK_BY_EMAIL ?";
    	List<UsersDTO> users = jdbcTemplate.query(procedureName, new UserRowMapper(), email);
    	return users.isEmpty() ? null : users.get(0);
    }
    
    public void modifyUser(UsersDTO user) {
    	String procedureName = "sp_MODIFY_USER_BY_USERID";
//    	Map<String, Object> params = new HashMap<String, Object>();
//        params.put("UserId", user.getUserId());
//        params.put("Password", user.getPassword());
//        params.put("Name", user.getName());
//        params.put("StoreNumber", user.getStoreNumber());
//        params.put("ROLE", user.getROLE());
//        params.put("Email", user.getEmail());
//        params.put("SubscriptionId", user.getSubscriptionId());

        // 저장 프로시저 실행
        jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @Password = ?, @Name = ?, @Email = ?",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }
    
    public void modifyLoginFailNum(String userId) {
    	String procedureName = "sp_MODIFY_LOGIN_FAIL_NUM_BY_USERID";

        // 저장 프로시저 실행
        jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?",
                userId);
    }
    
    public void modifyLoginFailNumReset(String userId) {
    	String procedureName = "sp_MODIFY_LOGIN_FAIL_NUM_RESET_BY_USERID";

        // 저장 프로시저 실행
        jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?",
                userId);
    }
   
}