package howmuch.com.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import howmuch.com.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<UnitKeyNameDTO>>> GetAllUnitName(@RequestParam("UnitKey") String unitKey, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<List<UnitKeyNameDTO>> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			List<UnitKeyNameDTO> unitNames = unitService.getUnitNameByIngredientUnitName(userDetails.getUsername(), unitKey);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Get Ingredient UnitName Success", unitNames, null);
			return ResponseEntity.ok(response);
		}   
    }
	
	@GetMapping("/getallunitname")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<UnitKeyNameDTO>>> GetAllUnitName(@AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<List<UnitKeyNameDTO>> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			List<UnitKeyNameDTO> unitNames = unitService.getAllUnitName(userDetails.getUsername());
			response = new ApiResponse<>(HttpStatus.OK.value(), "Get UnitName Success", unitNames, null);
			return ResponseEntity.ok(response);
		}
    }
	
	
	@GetMapping("/getallunit")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<UnitDTO>>> GetAllUnit(@AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<List<UnitDTO>> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			List<UnitDTO> units = unitService.getAllUnit(userDetails.getUsername());
			response = new ApiResponse<>(HttpStatus.OK.value(), "Get Unit Success", units, null);
			return ResponseEntity.ok(response);
		}
    }
	
	@PostMapping("/modifyunit")
    public ResponseEntity<ApiResponse<Void>> ModifyUnit(@RequestBody UnitVO unitVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			unitService.modifyUnit(userDetails.getUsername(), unitVO);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Modify Unit Success", null, null);
			return ResponseEntity.ok(response);
		}   
    }
	
	@PostMapping("/addunit")
    public ResponseEntity<ApiResponse<Void>> AddUnit(@RequestBody UnitVO unitVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			unitService.addUnit(userDetails.getUsername(), unitVO);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Add Unit Success", null, null);
			return ResponseEntity.ok(response);
		}    
    }	
	
}
