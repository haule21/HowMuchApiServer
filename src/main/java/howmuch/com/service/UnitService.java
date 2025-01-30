package howmuch.com.service;

import java.util.List;
import java.util.Map;

import howmuch.com.dto.UnitDTO;
import howmuch.com.dto.UnitKeyNameDTO;
import howmuch.com.vo.UnitVO;

public interface UnitService {
	public Map<String, Object> modifyUnit(String userId, UnitVO unitVO);
	public Map<String, Object> addUnit(String userId, UnitVO unitVO);
	public List<UnitDTO> getAllUnit(String userId);
	public List<UnitKeyNameDTO> getAllUnitName(String userId);
	public List<UnitKeyNameDTO> getUnitNameByIngredientUnitName(String userId, String ingredientUnitName);
}
