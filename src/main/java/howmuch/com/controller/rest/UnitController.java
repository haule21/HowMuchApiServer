package howmuch.com.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import howmuch.com.dto.UnitDTO;
import howmuch.com.dto.UnitKeyNameDTO;
import howmuch.com.service.UnitService;
import howmuch.com.vo.UnitVO;


@RestController
@RequestMapping(value = "/api/unit")
public class UnitController {
	
	@Autowired
	private UnitService unitService;
	
	@GetMapping("/getunitingredient")
	@PreAuthorize("hasRole('USER')")
    public Map<String, Object> GetAllUnitName(@RequestParam("UnitKey") String unitKey, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
			response.put("result", null);
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			List<UnitKeyNameDTO> unitNames = unitService.getUnitNameByIngredientUnitName(userDetails.getUsername(), unitKey);
	        response.put("result", unitNames);
	        response.put("message", "Sucess");
	        response.put("state", true);
		}   
		
		return response;
    }
	
	@GetMapping("/getallunitname")
	@PreAuthorize("hasRole('USER')")
    public Map<String, Object> GetAllUnitName(@AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
			response.put("result", null);
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			List<UnitKeyNameDTO> unitNames = unitService.getAllUnitName(userDetails.getUsername());
	        response.put("result", unitNames);
	        response.put("message", "Sucess");
	        response.put("state", true);
		}   
		
		return response;
    }
	
	
	@GetMapping("/getallunit")
	@PreAuthorize("hasRole('USER')")
    public Map<String, Object> GetAllUnit(@AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
			response.put("result", null);
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			List<UnitDTO> units = unitService.getAllUnit(userDetails.getUsername());
	        response.put("result", units);
	        response.put("message", "Sucess");
	        response.put("state", true);
		}   
		
		return response;
    }
	
	@PostMapping("/modifyunit")
    public Map<String, Object> ModifyUnit(@RequestBody UnitVO unitVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = unitService.modifyUnit(userDetails.getUsername(), unitVO);
		}   
		
		return response;
    }
	
	@PostMapping("/addunit")
    public Map<String, Object> AddUnit(@RequestBody UnitVO unitVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = unitService.addUnit(userDetails.getUsername(), unitVO);
		}   
		
		return response; 
    }	
	
}
