package howmuch.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import howmuch.com.dto.UnitDTO;

public class UnitRowMapper implements org.springframework.jdbc.core.RowMapper<UnitDTO> {
    @Override
    public UnitDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UnitDTO unit = new UnitDTO();
    	unit.setUnitKey(rs.getString("UnitKey"));
    	unit.setParentUnitKey(rs.getString("ParentUnitKey"));
    	unit.setParentUnitRelation(rs.getFloat("ParentUnitRelation"));
        unit.setUnitName(rs.getString("UnitName"));
        unit.setValue(rs.getFloat("Value"));
        unit.setDescription(rs.getString("Description"));
        return unit;
    }
}
