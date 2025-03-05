package howmuch.com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import howmuch.com.dto.UnitDTO;
import howmuch.com.dto.UnitKeyNameDTO;
import howmuch.com.repository.UnitRepository;
import howmuch.com.vo.UnitVO;

@Service
public class UnitServiceImpl implements UnitService {
	
	@Autowired
	@Lazy
    private UnitRepository unitRepository;
	
	@Override
	public List<UnitDTO> getAllUnit(String userId) {
		return unitRepository.allUnitByUserId(userId);
	}
	
	@Override
	public int addUnit(String userId, UnitVO unitVO) {
		UnitDTO unitDTO = new UnitDTO();
		unitDTO.setUserId(userId);
		unitDTO.setParentUnitKey(unitVO.getParentUnitKey());
		unitDTO.setParentUnitRelation(unitVO.getParentUnitRelation());
		unitDTO.setUnitName(unitVO.getUnitName());
		unitDTO.setValue(unitVO.getValue());
		unitDTO.setDescription(unitVO.getDescription());
		return unitRepository.save(unitDTO);
	}
	@Override
	public int modifyUnit(String userId, UnitVO unitVO) {
		UnitDTO unitDTO = new UnitDTO();
		unitDTO.setUserId(userId);
		unitDTO.setUnitKey(unitVO.getUnitKey());
		unitDTO.setParentUnitKey(unitVO.getParentUnitKey());
		unitDTO.setParentUnitRelation(unitVO.getParentUnitRelation());
		unitDTO.setUnitName(unitVO.getUnitName());
		unitDTO.setValue(unitVO.getValue());
		unitDTO.setDescription(unitVO.getDescription());
		return unitRepository.modify(unitDTO);
	}
	@Override
	public List<UnitKeyNameDTO> getAllUnitName(String userId) {
		return unitRepository.allUnitNameByUserId(userId);
	}
	@Override
	public List<UnitKeyNameDTO> getUnitNameByIngredientUnitName(String userId, String ingredientUnitKey) {
		return unitRepository.UnitNameByIngredientUnit(userId, ingredientUnitKey);
	}
}
