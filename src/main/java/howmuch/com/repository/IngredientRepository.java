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
    public int save(IngredientDTO ingredient) {
    	String procedureName = "sp_ADD_INGREDIENT_BY_USERID";
		return jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @IngredientName = ?, @UnitValue = ?, @UnitKey = ?, @Price = ?",
        		ingredient.getUserId(), ingredient.getIngredientName(), ingredient.getUnitValue(), ingredient.getUnitKey(), ingredient.getPrice());
    }

    // ingredient 수정
    public int modify(IngredientDTO ingredient) {
    	String procedureName = "sp_MODIFY_INGREDIENT_BY_USERID_INGREDIENTKEY";
    	
    	return jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @IngredientKey = ?, @IngredientName = ?, @UnitValue = ?, @UnitKey = ?, @Price = ?",
        		ingredient.getUserId(), ingredient.getIngredientKey(), ingredient.getIngredientName(), ingredient.getUnitValue(), ingredient.getUnitKey(), ingredient.getPrice());
    }
    
    public List<IngredientDTO> allIngredientByUserId(String userId) {
    	String procedureName = "sp_ALL_INGREDIENT_BY_USERID ?";
        List<IngredientDTO> ingredients = jdbcTemplate.query(procedureName, new IngredientRowMapper(), userId);

        return ingredients.isEmpty() ? null : ingredients;
    }
}
