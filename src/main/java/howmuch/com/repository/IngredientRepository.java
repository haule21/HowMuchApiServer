package howmuch.com.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import howmuch.com.dto.IngredientDTO;
import howmuch.com.mapper.IngredientRowMapper;

@Repository
public class IngredientRepository {
	private final JdbcTemplate jdbcTemplate;
    
    public IngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // ingredient 저장
    public Map<String, Object> save(IngredientDTO ingredient) {
    	String procedureName = "sp_ADD_INGREDIENT_BY_USERID";
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {
    		jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @IngredientName = ?, @UnitValue = ?, @UnitKey = ?, @Price = ?",
            		ingredient.getUserId(), ingredient.getIngredientName(), ingredient.getUnitValue(), ingredient.getUnitKey(), ingredient.getPrice());
        	
        	result.put("message", "Success");
        	result.put("state", true);
        } catch (DataAccessException  e) {
            result.put("message", e.getMessage());
        	result.put("state", false);
        }
    	
    	return result;
    }

    // ingredient 수정
    public Map<String, Object> modify(IngredientDTO ingredient) {
    	String procedureName = "sp_MODIFY_INGREDIENT_BY_USERID_INGREDIENTKEY";
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {
    		jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @IngredientKey = ?, @IngredientName = ?, @UnitValue = ?, @UnitKey = ?, @Price = ?",
            		ingredient.getUserId(), ingredient.getIngredientKey(), ingredient.getIngredientName(), ingredient.getUnitValue(), ingredient.getUnitKey(), ingredient.getPrice());
        	
        	result.put("message", "Success");
        	result.put("state", true);
        } catch (DataAccessException  e) {
            result.put("message", e.getMessage());
        	result.put("state", false);
        }
        return result;
    }
    
    public List<IngredientDTO> allIngredientByUserId(String userId) {
    	String procedureName = "sp_ALL_INGREDIENT_BY_USERID ?";
        List<IngredientDTO> ingredients = jdbcTemplate.query(procedureName, new IngredientRowMapper(), userId);

        return ingredients.isEmpty() ? null : ingredients;
    }
}
