package howmuch.com.service;

import java.util.List;
import java.util.Map;

import howmuch.com.dto.IngredientSourceDTO;
import howmuch.com.dto.RecipeDTO;
import howmuch.com.dto.RecipeDetailDTO;
import howmuch.com.vo.RecipeDetailVO;
import howmuch.com.vo.RecipeVO;

public interface RecipeService {
	List<RecipeDTO> getAllRecipe(String userId);
	List<RecipeDetailDTO> allRecipeDetailByUserIdRecipeKey(String userId, String recipeKey);
	List<IngredientSourceDTO> allIngredientSource(String userId);
	Map<String, Object> modify(String userId, RecipeVO source);
	Map<String, Object> save(String userId, RecipeVO source);
	Map<String, Object> modifyDetail(String userId, RecipeDetailVO recipeDetail);
	Map<String, Object> saveDetail(String userId, RecipeDetailVO recipeDetail);
}
