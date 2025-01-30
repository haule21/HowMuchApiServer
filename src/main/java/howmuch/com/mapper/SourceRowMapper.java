package howmuch.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import howmuch.com.dto.SourceDTO;

public class SourceRowMapper implements org.springframework.jdbc.core.RowMapper<SourceDTO> {
	@Override
    public SourceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SourceDTO source = new SourceDTO();
		source.setSourceKey(rs.getString("SourceKey"));
    	source.setSourceName(rs.getString("SourceName"));
    	source.setAmount(rs.getFloat("Amount"));
    	source.setPricePerUnit(rs.getFloat("PricePerUnit"));
        return source;
    }
}
