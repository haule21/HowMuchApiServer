package howmuch.com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import howmuch.com.dto.IngredientDTO;
import howmuch.com.repository.IngredientRepository;
import howmuch.com.vo.IngredientVO;

@Service
public class IngredientServiceImpl implements IngredientService{
	
	@Autowired
	@Lazy
    private IngredientRepository ingredientRepository;
	
	@Override
	public List<IngredientDTO> getAllIngredient(String userId) {
		return ingredientRepository.allIngredientByUserId(userId);
	}
	
	@Override
	public Map<String, Object> addIngredient(String userId, IngredientVO ingredientVO) {
		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.setUserId(userId);
		ingredientDTO.setIngredientKey(ingredientVO.getIngredientKey());
		ingredientDTO.setIngredientName(ingredientVO.getIngredientName());
		ingredientDTO.setUnitValue(ingredientVO.getUnitValue());
		ingredientDTO.setUnitKey(ingredientVO.getUnitKey());
		ingredientDTO.setPrice(ingredientVO.getPrice());
		return ingredientRepository.save(ingredientDTO);
	}
	@Override
	public Map<String, Object> modifyIngredient(String userId, IngredientVO ingredientVO) {
		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.setUserId(userId);
		ingredientDTO.setIngredientKey(ingredientVO.getIngredientKey());
		ingredientDTO.setIngredientName(ingredientVO.getIngredientName());
		ingredientDTO.setUnitValue(ingredientVO.getUnitValue());
		ingredientDTO.setUnitKey(ingredientVO.getUnitKey());
		ingredientDTO.setPrice(ingredientVO.getPrice());
		return ingredientRepository.modify(ingredientDTO);
	}
}
