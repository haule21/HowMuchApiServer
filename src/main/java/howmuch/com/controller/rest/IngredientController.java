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
    public Map<String, Object> GetAllIngredient(@AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
			response.put("result", null);
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			List<IngredientDTO> ingredients = ingredientService.getAllIngredient(userDetails.getUsername());
	        response.put("result", ingredients);
	        response.put("message", "Sucess");
	        response.put("state", true);
		}   
		
		return response;
    }
	
	@PostMapping("/modifyingredient")
    public Map<String, Object> ModifyUnit(@RequestBody IngredientVO ingredientVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = ingredientService.modifyIngredient(userDetails.getUsername(), ingredientVO);
		}   
		
		return response;
    }
	
	@PostMapping("/addingredient")
    public Map<String, Object> AddUnit(@RequestBody IngredientVO ingredientVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = ingredientService.addIngredient(userDetails.getUsername(), ingredientVO);
		}   
		
		return response; 
    }	
}
