package howmuch.com.service;

import java.util.List;
import java.util.Map;

import howmuch.com.dto.SourceDTO;
import howmuch.com.dto.SourceRecipeDTO;
import howmuch.com.vo.SourceRecipeVO;
import howmuch.com.vo.SourceVO;

public interface SourceService {

	List<SourceDTO> getAllSource(String userId);
	List<SourceRecipeDTO> allSourceRecipeByUserIdSourceKey(String userId, String sourceRecipeKey);
	int modify(String userId, SourceVO source);
	int save(String userId, SourceVO source);
	int modifySourceRecipe(String userId, SourceRecipeVO sourceRecipe);
	int saveSourceRecipe(String userId, SourceRecipeVO sourceRecipe);
}
