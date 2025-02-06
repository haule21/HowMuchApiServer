package howmuch.com.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
	private final JdbcTemplate jdbcTemplate;
    
    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
	public Map<String, Object> delete(String userId) {
    	String procedureName = "sp_DELETE_ACCOUNT";
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {
    		jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?", userId);
        	
        	result.put("message", "Success");
        	result.put("state", true);
        } catch (DataAccessException  e) {
            result.put("message", e.getMessage());
        	result.put("state", false);
        }
    	
    	return result;
    }
}
