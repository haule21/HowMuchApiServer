package howmuch.com.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import howmuch.com.dto.SourceDTO;
import howmuch.com.dto.SourceRecipeDTO;
import howmuch.com.mapper.SourceRecipeRowMapper;
import howmuch.com.mapper.SourceRowMapper;

@Repository
public class SourceRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public SourceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<SourceDTO> allSourceByUserId(String userId) {
    	String procedureName = "sp_ALL_SOURCE_BY_USERID ?";
        List<SourceDTO> sources = jdbcTemplate.query(procedureName, new SourceRowMapper(), userId);

        return sources.isEmpty() ? null : sources;
    }
    
    public List<SourceRecipeDTO> allSourceRecipeByUserIdSourceKey(String userId, String sourceKey) {
    	String procedureName = "sp_ALL_SOURCE_RECIPE_BY_USERID_SOURCEKEY ?, ?";
        List<SourceRecipeDTO> sources = jdbcTemplate.query(procedureName, new SourceRecipeRowMapper(), userId, sourceKey);

        return sources.isEmpty() ? null : sources;
    }
    
    public int modify(SourceDTO source) {
    	String procedureName = "sp_MODIFY_SOURCE_BY_SOURCEKEY";

        return jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @SourceKey = ?, @SourceName = ?, @Amount = ?",
        			source.getUserId(), source.getSourceKey(), source.getSourceName(), source.getAmount());

    }
    public int save(SourceDTO source) {
    	String procedureName = "sp_ADD_SOURCE_BY_USERID";

        
    	return jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @SourceName = ?, @Amount = ?",
        			source.getUserId(), source.getSourceName(), source.getAmount());
    }
    
    public int modifySourceRecipe(SourceRecipeDTO sourceRecipe) {
    	String procedureName = "sp_MODIFY_SOURCE_RECIPE_BY_USERID_SOURCERECIPEKEY";

    	return jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @SourceKey = ?, @SourceRecipeKey = ?, @IngredientKey = ?, @MaterialUsage = ?, @IngredientUnitKey = ?",
        			sourceRecipe.getUserId(), sourceRecipe.getSourceKey(), sourceRecipe.getSourceRecipeKey(), sourceRecipe.getIngredientKey(), sourceRecipe.getMaterialUsage(), sourceRecipe.getUnitKey());

    }
    public int saveSourceRecipe(SourceRecipeDTO sourceRecipe) {
    	String procedureName = "sp_ADD_SOURCE_RECIPE_BY_USERID";

        return jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @SourceKey = ?, @IngredientKey = ?, @MaterialUsage = ?, @IngredientUnitKey = ?",
        			sourceRecipe.getUserId(), sourceRecipe.getSourceKey(), sourceRecipe.getIngredientKey(), sourceRecipe.getMaterialUsage(), sourceRecipe.getUnitKey());

    }
}
