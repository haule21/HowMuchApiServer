package howmuch.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import howmuch.com.dto.RecipeDTO;

public class RecipeRowMapper implements org.springframework.jdbc.core.RowMapper<RecipeDTO> {
	@Override
    public RecipeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecipeDTO recipe = new RecipeDTO();
		recipe.setRecipeKey(rs.getString("RecipeKey"));
		recipe.setRecipeName(rs.getString("RecipeName"));
		recipe.setPricePerUnit(rs.getFloat("PricePerUnit"));
		recipe.setPrice(rs.getFloat("Price"));
		recipe.setCostRatio(rs.getFloat("CostRatio"));
        return recipe;
    }
}
