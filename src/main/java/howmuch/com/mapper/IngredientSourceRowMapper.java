package howmuch.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import howmuch.com.dto.IngredientSourceDTO;

public class IngredientSourceRowMapper implements org.springframework.jdbc.core.RowMapper<IngredientSourceDTO> {
	@Override
    public IngredientSourceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		IngredientSourceDTO ingredient = new IngredientSourceDTO();
		ingredient.setIngredientKey(rs.getString("IngredientKey"));
		ingredient.setIngredientName(rs.getString("IngredientName"));
		ingredient.setUnitKey(rs.getString("UnitKey"));
        return ingredient;
    }
}
