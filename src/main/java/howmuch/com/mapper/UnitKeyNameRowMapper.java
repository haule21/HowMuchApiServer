package howmuch.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import howmuch.com.dto.UnitKeyNameDTO;


public class UnitKeyNameRowMapper implements org.springframework.jdbc.core.RowMapper<UnitKeyNameDTO> {
	@Override
    public UnitKeyNameDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UnitKeyNameDTO unit = new UnitKeyNameDTO();
    	unit.setUnitKey(rs.getString("UnitKey"));
        unit.setUnitName(rs.getString("UnitName"));
        return unit;
    }
}