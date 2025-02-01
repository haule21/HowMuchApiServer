package howmuch.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import howmuch.com.dto.UsersDTO;

public class UserRowMapper implements org.springframework.jdbc.core.RowMapper<UsersDTO> {
    @Override
    public UsersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UsersDTO user = new UsersDTO();
        user.setUserId(rs.getString("UserId"));
        user.setPassword(rs.getString("Password"));
        user.setName(rs.getString("Name"));
        user.setStoreNumber(rs.getString("StoreNumber"));
        user.setROLE(rs.getString("ROLE"));
        user.setEmail(rs.getString("Email"));
        user.setSubscriptionId(rs.getString("SubscriptionId"));
        user.setCreateDate(rs.getDate("CreateDate"));
        user.setUpdateDate(rs.getDate("UpdateDate"));
        user.setDeleteDate(rs.getDate("DeleteDate"));
        return user;
    }
}
