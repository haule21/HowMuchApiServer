package howmuch.com.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import howmuch.com.dto.UnitDTO;
import howmuch.com.dto.UnitKeyNameDTO;
import howmuch.com.mapper.UnitKeyNameRowMapper;
import howmuch.com.mapper.UnitRowMapper;

@Repository
public class UnitRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public UnitRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // Unit 저장
    public int save(UnitDTO unit) {
    	String procedureName = "sp_ADD_UNIT_BY_USERID";
        
        return jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @UnitName = ?, @ParentUnitKey = ?, @ParentUnitRelation = ?, @Value = ?, @Description = ?",
            		unit.getUserId(), unit.getUnitName(), unit.getParentUnitKey(), unit.getParentUnitRelation(), unit.getValue(), unit.getDescription());
    }

    // Unit 수정
    public int modify(UnitDTO unit) {
    	String procedureName = "sp_MODIFY_UNIT_BY_USERID_UNITKEY";

        return jdbcTemplate.update("EXEC " + procedureName + " @UserId = ?, @UnitKey = ?, @UnitName = ?, @ParentUnitKey = ?, @ParentUnitRelation = ?, @Value = ?, @Description = ?",
            		unit.getUserId(), unit.getUnitKey(), unit.getUnitName(), unit.getParentUnitKey(), unit.getParentUnitRelation(), unit.getValue(), unit.getDescription());

    }
    
    public List<UnitDTO> allUnitByUserId(String userId) {
    	String procedureName = "sp_ALL_UNIT_BY_USERID ?";
        List<UnitDTO> units = jdbcTemplate.query(procedureName, new UnitRowMapper(), userId);

        return units.isEmpty() ? null : units;
    }
    
    public List<UnitKeyNameDTO> allUnitNameByUserId(String userId) {
    	String procedureName = "sp_ALL_UNIT_NAME_BY_USERID ?";
        List<UnitKeyNameDTO> units = jdbcTemplate.query(procedureName, new UnitKeyNameRowMapper(), userId);

        return units.isEmpty() ? null : units;
    }
    
    public List<UnitKeyNameDTO> UnitNameByIngredientUnit(String userId, String unitName) {
    	String procedureName = "sp_UNITNAME_BY_INGREDIENT_UNITKEY ?, ?";
        List<UnitKeyNameDTO> units = jdbcTemplate.query(procedureName, new UnitKeyNameRowMapper(), userId, unitName);

        return units.isEmpty() ? null : units;
    }
    
}
