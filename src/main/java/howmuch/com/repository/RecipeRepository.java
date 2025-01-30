package howmuch.com.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import howmuch.com.dto.IngredientSourceDTO;
import howmuch.com.dto.RecipeDTO;
import howmuch.com.dto.RecipeDetailDTO;
import howmuch.com.mapper.IngredientSourceRowMapper;
import howmuch.com.mapper.RecipeDetailRowMapper;
import howmuch.com.mapper.RecipeRowMapper;

@Repository
public class RecipeRepository {
	private final JdbcTemplate jdbcTemplate;
    
    public RecipeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<RecipeDTO> allRecipeByUserId(String userId) {
    	String procedureName = "sp_ALL_RECIPE_BY_USERID ?";
        List<RecipeDTO> recipes = jdbcTemplate.query(procedureName, new RecipeRowMapper(), userId);

        return recipes.isEmpty() ? null : recipes;
    }
    
    public List<RecipeDetailDTO> allRecipeDetailByUserIdRecipeKey(String userId, String recipeKey) {
    	String procedureName = "sp_ALL_RECIPE_DETAIL_BY_USERID_RECIPEKEY ?, ?";
        List<RecipeDetailDTO> recipes = jdbcTemplate.query(procedureName, new RecipeDetailRowMapper(), userId, recipeKey);

        return recipes.isEmpty() ? null : recipes;
    }
    
    public List<IngredientSourceDTO> allIngredientSource(String userId) {
    	String procedureName = "sp_ALL_INGREDIENT_SOURCE ?";
        List<IngredientSourceDTO> ingredients = jdbcTemplate.query(procedureName, new IngredientSourceRowMapper(), userId);

        return ingredients.isEmpty() ? null : ingredients;
    }
    
    public Map<String, Object> modify(RecipeDTO recipe) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	String procedureName = "sp_MODIFY_RECIPE_BY_RECIPEKEY";

        // 저장 프로시저 실행
        try {
        	jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @RecipeKey = ?, @RecipeName = ?, @Price = ?",
        			recipe.getUserId(), recipe.getRecipeKey(), recipe.getRecipeName(), recipe.getPrice());
        	result.put("message", "Success");
        	result.put("state", true);
        } catch (DataAccessException  e) {
            result.put("message", e.getMessage());
        	result.put("state", false);
        }

        return result;
    }
    public Map<String, Object> save(RecipeDTO recipe) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	String procedureName = "sp_ADD_RECIPE_BY_USERID";

        // 저장 프로시저 실행
        try {
        	jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @RecipeName = ?, @Price = ?",
        			recipe.getUserId(), recipe.getRecipeName(), recipe.getPrice());
        	result.put("message", "Success");
        	result.put("state", true);
        } catch (DataAccessException  e) {
            result.put("message", e.getMessage());
        	result.put("state", false);
        }
        return result;
    }
    
    public Map<String, Object> modifyDetail(RecipeDetailDTO recipeDetail) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	String procedureName = "sp_MODIFY_RECIPE_DETAIL_BY_USERID_RECIPEDETAILKEY";

        // 저장 프로시저 실행
        try {
        	jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @RecipeKey = ?, @RecipeDetailKey = ?, @IngredientKey = ?, @MaterialUsage = ?, @IngredientUnitKey = ?",
        			recipeDetail.getUserId(), recipeDetail.getRecipeKey(), recipeDetail.getRecipeDetailKey(), recipeDetail.getIngredientKey(), recipeDetail.getMaterialUsage(), recipeDetail.getUnitKey());
        	result.put("message", "Success");
        	result.put("state", true);
        } catch (DataAccessException  e) {
            result.put("message", e.getMessage());
        	result.put("state", false);
        }

        return result;
    }
    public Map<String, Object> saveDetail(RecipeDetailDTO sourceRecipe) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	String procedureName = "sp_ADD_RECIPE_DETAIL_BY_USERID";

        // 저장 프로시저 실행
        try {
        	jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @RecipeKey = ?, @IngredientKey = ?, @MaterialUsage = ?, @IngredientUnitKey = ?",
        			sourceRecipe.getUserId(), sourceRecipe.getRecipeKey(), sourceRecipe.getIngredientKey(), sourceRecipe.getMaterialUsage(), sourceRecipe.getUnitKey());
        	result.put("message", "Success");
        	result.put("state", true);
        } catch (DataAccessException  e) {
            result.put("message", e.getMessage());
        	result.put("state", false);
        }
        return result;
    }
}
