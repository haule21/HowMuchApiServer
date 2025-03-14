package howmuch.com.service;

import java.util.List;
import java.util.Map;

import howmuch.com.dto.IngredientDTO;
import howmuch.com.vo.IngredientVO;

public interface IngredientService {
	public int modifyIngredient(String userId, IngredientVO ingredientVO);
	public int addIngredient(String userId, IngredientVO ingredientVO);
	public List<IngredientDTO> getAllIngredient(String userId);
}
