package howmuch.com.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("UserId", user.getUserId());
        params.put("Password", user.getPassword());
        params.put("Name", user.getName());
        params.put("StoreNumber", user.getStoreNumber());
        params.put("ROLE", user.getROLE());
        params.put("SubscriptionId", user.getSubscriptionId());

        // 저장 프로시저 실행
        jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @Password = ?, @Name = ?, @StoreNumber = ?, @ROLE = ?, @SubscriptionId = ?",
                user.getUserId(), user.getPassword(), user.getName(), user.getStoreNumber(), user.getROLE(), user.getSubscriptionId());
    }

    // 사용자 조회 (id 기준)
    public UsersDTO findByUserId(String userId) {
    	String procedureName = "sp_FIND_USER_BY_USERID ?";
        List<UsersDTO> users = jdbcTemplate.query(procedureName, new UserRowMapper(), userId);
        System.out.println("==================================================================");
    	System.out.println(users);
    	System.out.println("==================================================================");
        return users.isEmpty() ? null : users.get(0);
    }
   
}