package howmuch.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import howmuch.com.dto.RecipeDetailDTO;
import lombok.Data;

@Data
public class RecipeDetailRowMapper implements org.springframework.jdbc.core.RowMapper<RecipeDetailDTO>  {
	@Override
    public RecipeDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecipeDetailDTO recipeDetail = new RecipeDetailDTO();
		recipeDetail.setRecipeKey(rs.getString("RecipeKey"));
		recipeDetail.setRecipeDetailKey(rs.getString("RecipeDetailKey"));
		recipeDetail.setSeq(rs.getInt("Seq"));
		recipeDetail.setIngredientKey(rs.getString("IngredientKey"));
		recipeDetail.setIngredientName(rs.getString("IngredientName"));
		recipeDetail.setMaterialUsage(rs.getFloat("MaterialUsage"));
		recipeDetail.setUnitKey(rs.getString("UnitKey"));
		recipeDetail.setUnitName(rs.getString("UnitName"));
        return recipeDetail;
    }
}
