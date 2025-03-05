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
    
	public int delete(String userId) {
    	String procedureName = "sp_DELETE_ACCOUNT";
		return jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?", userId);
    }
}
