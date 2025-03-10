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
import howmuch.com.dto.IngredientDTO;
import howmuch.com.service.IngredientService;
import howmuch.com.vo.IngredientVO;

@RestController
@RequestMapping(value = "/api/ingredient")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/getallingredient")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<IngredientDTO>>> GetAllIngredient(@AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<List<IngredientDTO>> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			List<IngredientDTO> ingredients = ingredientService.getAllIngredient(userDetails.getUsername());
	        response = new ApiResponse<>(HttpStatus.OK.value(), "Get Ingredients Success", ingredients, null);
	        return ResponseEntity.ok(response);
		}   
    }
	
	@PostMapping("/modifyingredient")
    public ResponseEntity<ApiResponse<Void>> ModifyUnit(@RequestBody IngredientVO ingredientVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			ingredientService.modifyIngredient(userDetails.getUsername(), ingredientVO);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Modify Ingredients Success", null, null);
			return ResponseEntity.ok(response);
		}   
    }
	
	@PostMapping("/addingredient")
    public ResponseEntity<ApiResponse<Void>> AddUnit(@RequestBody IngredientVO ingredientVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			ingredientService.addIngredient(userDetails.getUsername(), ingredientVO);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Add Ingredients Success", null, null);
			return ResponseEntity.ok(response);
		}   
    }	
}
