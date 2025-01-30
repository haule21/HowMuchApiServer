package howmuch.com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import howmuch.com.dto.SourceDTO;
import howmuch.com.dto.SourceRecipeDTO;
import howmuch.com.repository.SourceRepository;
import howmuch.com.vo.SourceRecipeVO;
import howmuch.com.vo.SourceVO;

@Service
public class SourceServiceImpl implements SourceService {
	
	@Autowired
	SourceRepository sourceRepository;
	@Override
	public List<SourceDTO> getAllSource(String userId) {
		return sourceRepository.allSourceByUserId(userId);
	}
	@Override
	public List<SourceRecipeDTO> allSourceRecipeByUserIdSourceKey(String userId, String sourceRecipeKey) {
		return sourceRepository.allSourceRecipeByUserIdSourceKey(userId, sourceRecipeKey);
	}
	@Override
	public Map<String, Object> modify(String userId, SourceVO sourceVO) {
		SourceDTO sourceDTO = new SourceDTO();
		sourceDTO.setUserId(userId);
		sourceDTO.setSourceKey(sourceVO.getSourceKey());
		sourceDTO.setSourceName(sourceVO.getSourceName());
		sourceDTO.setAmount(sourceVO.getAmount());

		return sourceRepository.modify(sourceDTO);
	}
	@Override
	public Map<String, Object> modifySourceRecipe(String userId, SourceRecipeVO sourceRecipeVO) {
		SourceRecipeDTO sourceRecipeDTO = new SourceRecipeDTO();
		sourceRecipeDTO.setUserId(userId);
		sourceRecipeDTO.setSourceKey(sourceRecipeVO.getSourceKey());
		sourceRecipeDTO.setSourceRecipeKey(sourceRecipeVO.getSourceRecipeKey());
		sourceRecipeDTO.setIngredientKey(sourceRecipeVO.getIngredientKey());
		sourceRecipeDTO.setMaterialUsage(sourceRecipeVO.getMaterialUsage());
		sourceRecipeDTO.setUnitKey(sourceRecipeVO.getIngredientUnitKey());

		return sourceRepository.modifySourceRecipe(sourceRecipeDTO);
	}
	@Override
	public Map<String, Object> save(String userId, SourceVO sourceVO) {
		SourceDTO sourceDTO = new SourceDTO();
		sourceDTO.setUserId(userId);
		sourceDTO.setSourceName(sourceVO.getSourceName());
		sourceDTO.setAmount(sourceVO.getAmount());
		return sourceRepository.save(sourceDTO);
	}
	@Override
	public Map<String, Object> saveSourceRecipe(String userId, SourceRecipeVO sourceRecipeVO) {
		SourceRecipeDTO sourceRecipeDTO = new SourceRecipeDTO();
		sourceRecipeDTO.setUserId(userId);
		sourceRecipeDTO.setSourceKey(sourceRecipeVO.getSourceKey());
		sourceRecipeDTO.setIngredientKey(sourceRecipeVO.getIngredientKey());
		sourceRecipeDTO.setMaterialUsage(sourceRecipeVO.getMaterialUsage());
		sourceRecipeDTO.setUnitKey(sourceRecipeVO.getIngredientUnitKey());
		
		return sourceRepository.saveSourceRecipe(sourceRecipeDTO);
	}
}
