package howmuch.com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import howmuch.com.dto.IngredientSourceDTO;
import howmuch.com.dto.RecipeDTO;
import howmuch.com.dto.RecipeDetailDTO;
import howmuch.com.repository.RecipeRepository;
import howmuch.com.vo.RecipeDetailVO;
import howmuch.com.vo.RecipeVO;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Override
	public List<IngredientSourceDTO> allIngredientSource(String userId) {
		return recipeRepository.allIngredientSource(userId);
	}
	@Override
	public List<RecipeDetailDTO> allRecipeDetailByUserIdRecipeKey(String userId, String recipeKey) {
		return recipeRepository.allRecipeDetailByUserIdRecipeKey(userId, recipeKey);
	}
	@Override
	public List<RecipeDTO> getAllRecipe(String userId) {
		return recipeRepository.allRecipeByUserId(userId);
	}
	@Override
	public Map<String, Object> modify(String userId, RecipeVO recipe) {
		RecipeDTO recipeDTO = new RecipeDTO();
		recipeDTO.setUserId(userId);
		recipeDTO.setRecipeKey(recipe.getRecipeKey());
		recipeDTO.setRecipeName(recipe.getRecipeName());
		recipeDTO.setPrice(recipe.getPrice());

		return recipeRepository.modify(recipeDTO);
	}
	@Override
	public Map<String, Object> modifyDetail(String userId, RecipeDetailVO recipeDetail) {
		RecipeDetailDTO recipeDetailDTO = new RecipeDetailDTO();
		recipeDetailDTO.setUserId(userId);
		recipeDetailDTO.setRecipeKey(recipeDetail.getRecipeKey());
		recipeDetailDTO.setRecipeDetailKey(recipeDetail.getRecipeDetailKey());
		recipeDetailDTO.setIngredientKey(recipeDetail.getIngredientKey());
		recipeDetailDTO.setUnitKey(recipeDetail.getIngredientUnitKey());
		recipeDetailDTO.setMaterialUsage(recipeDetail.getMaterialUsage());

		return recipeRepository.modifyDetail(recipeDetailDTO);
	}
	@Override
	public Map<String, Object> save(String userId, RecipeVO recipe) {
		RecipeDTO recipeDTO = new RecipeDTO();
		recipeDTO.setUserId(userId);
		recipeDTO.setRecipeName(recipe.getRecipeName());
		recipeDTO.setPrice(recipe.getPrice());

		return recipeRepository.save(recipeDTO);
	}
	@Override
	public Map<String, Object> saveDetail(String userId, RecipeDetailVO recipeDetail) {
		RecipeDetailDTO recipeDetailDTO = new RecipeDetailDTO();
		recipeDetailDTO.setUserId(userId);
		recipeDetailDTO.setRecipeKey(recipeDetail.getRecipeKey());
		recipeDetailDTO.setIngredientKey(recipeDetail.getIngredientKey());
		recipeDetailDTO.setUnitKey(recipeDetail.getIngredientUnitKey());
		recipeDetailDTO.setMaterialUsage(recipeDetail.getMaterialUsage());

		return recipeRepository.saveDetail(recipeDetailDTO);
	}
}
