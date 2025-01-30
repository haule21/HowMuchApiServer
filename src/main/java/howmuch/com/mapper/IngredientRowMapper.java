package howmuch.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import howmuch.com.dto.IngredientDTO;

public class IngredientRowMapper  implements org.springframework.jdbc.core.RowMapper<IngredientDTO> {
	@Override
    public IngredientDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		IngredientDTO ingredient = new IngredientDTO();
		ingredient.setIngredientKey(rs.getString("IngredientKey"));
		ingredient.setIngredientName(rs.getString("IngredientName"));
		ingredient.setUnitValue(rs.getFloat("UnitValue"));
		ingredient.setUnitKey(rs.getString("UnitKey"));
		ingredient.setUnitName(rs.getString("UnitName"));
		ingredient.setPrice(rs.getFloat("Price"));
        return ingredient;
    }
}
