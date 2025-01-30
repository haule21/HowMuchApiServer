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

import howmuch.com.dto.IngredientSourceDTO;
import howmuch.com.dto.RecipeDTO;
import howmuch.com.dto.RecipeDetailDTO;
import howmuch.com.dto.SourceDTO;
import howmuch.com.dto.SourceRecipeDTO;
import howmuch.com.service.RecipeService;
import howmuch.com.service.SourceService;
import howmuch.com.vo.RecipeDetailVO;
import howmuch.com.vo.RecipeVO;
import howmuch.com.vo.SourceRecipeVO;
import howmuch.com.vo.SourceVO;

@RestController
@RequestMapping(value = "/api/recipe")
public class RecipeController {
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/getallrecipe")
	@PreAuthorize("hasRole('USER')")
    public Map<String, Object> GetAllRecipe(@AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
			response.put("result", null);
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			List<RecipeDTO> recipe = recipeService.getAllRecipe(userDetails.getUsername());
	        response.put("result", recipe);
	        response.put("message", "Sucess");
	        response.put("state", true);
		}   
		
		return response;
    }
	
	@GetMapping("/getallrecipedetailingredients")
	@PreAuthorize("hasRole('USER')")
    public Map<String, Object> GetAllRecipeDetail(@RequestParam("RecipeKey") String recipeKey, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
			response.put("result", null);
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			List<RecipeDetailDTO> recipeDetail = recipeService.allRecipeDetailByUserIdRecipeKey(userDetails.getUsername(), recipeKey);
	        response.put("result", recipeDetail);
	        response.put("message", "Sucess");
	        response.put("state", true);
		}   
		
		return response;
    }
	
	@GetMapping("/ingredientsource")
	@PreAuthorize("hasRole('USER')")
    public Map<String, Object> GetAllIngredientSource(@AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
			response.put("result", null);
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			List<IngredientSourceDTO> ingredientSource = recipeService.allIngredientSource(userDetails.getUsername());
	        response.put("result", ingredientSource);
	        response.put("message", "Sucess");
	        response.put("state", true);
		}   
		
		return response;
    }
	@PostMapping("/modifyrecipe")
    public Map<String, Object> ModifyUnit(@RequestBody RecipeVO recipeVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = recipeService.modify(userDetails.getUsername(), recipeVO);
		}   
		
		return response;
    }
	
	@PostMapping("/addrecipe")
    public Map<String, Object> AddUnit(@RequestBody RecipeVO recipeVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = recipeService.save(userDetails.getUsername(), recipeVO);
		}   
		
		return response; 
    }
	@PostMapping("/modifyrecipedetail")
    public Map<String, Object> ModifyUnit(@RequestBody RecipeDetailVO recipeDetailVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = recipeService.modifyDetail(userDetails.getUsername(), recipeDetailVO);
		}   
		
		return response;
    }
	
	@PostMapping("/addrecipedetail")
    public Map<String, Object> AddUnit(@RequestBody RecipeDetailVO recipeDetailVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = recipeService.saveDetail(userDetails.getUsername(), recipeDetailVO);
		}   
		
		return response; 
    }
}
