package howmuch.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import howmuch.com.dto.SourceRecipeDTO;

public class SourceRecipeRowMapper implements org.springframework.jdbc.core.RowMapper<SourceRecipeDTO>{
	@Override
    public SourceRecipeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SourceRecipeDTO sourceRecipe = new SourceRecipeDTO();
		sourceRecipe.setSourceKey(rs.getString("SourceKey"));
		sourceRecipe.setSourceName(rs.getString("SourceName"));
		sourceRecipe.setSourceRecipeKey(rs.getString("SourceRecipeKey"));
		sourceRecipe.setSeq(rs.getInt("Seq"));
		sourceRecipe.setIngredientKey(rs.getString("IngredientKey"));
		sourceRecipe.setIngredientName(rs.getString("IngredientName"));
		sourceRecipe.setMaterialUsage(rs.getFloat("MaterialUsage"));
		sourceRecipe.setUnitKey(rs.getString("UnitKey"));
		sourceRecipe.setUnitName(rs.getString("UnitName"));
        return sourceRecipe;
    }
}
