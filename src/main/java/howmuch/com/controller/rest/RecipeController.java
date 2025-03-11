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
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/recipe")
public class RecipeController {
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/getallrecipe")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<RecipeDTO>>> GetAllRecipe(@AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<List<RecipeDTO>> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			List<RecipeDTO> recipe = recipeService.getAllRecipe(userDetails.getUsername());
			response = new ApiResponse<>(HttpStatus.OK.value(), "Get Recipe Success", recipe, null);
	        return ResponseEntity.ok(response);
		}
    }
	
	@GetMapping("/getallrecipedetailingredients")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<RecipeDetailDTO>>> GetAllRecipeDetail(@RequestParam("RecipeKey") String recipeKey, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<List<RecipeDetailDTO>> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			List<RecipeDetailDTO> recipeDetail = recipeService.allRecipeDetailByUserIdRecipeKey(userDetails.getUsername(), recipeKey);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Get RecipeDetail Success", recipeDetail, null);
	        return ResponseEntity.ok(response);
		}
    }
	
	@GetMapping("/ingredientsource")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<IngredientSourceDTO>>> GetAllIngredientSource(@AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<List<IngredientSourceDTO>> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			List<IngredientSourceDTO> ingredientSource = recipeService.allIngredientSource(userDetails.getUsername());
			response = new ApiResponse<>(HttpStatus.OK.value(), "Get Ingredient Source Success", ingredientSource, null);
	        return ResponseEntity.ok(response);
		}   
    }
	@PostMapping("/modifyrecipe")
    public ResponseEntity<ApiResponse<Void>> ModifyUnit(@RequestBody @Valid RecipeVO recipeVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			recipeService.modify(userDetails.getUsername(), recipeVO);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Modify Recipe Unit Success", null, null);
	        return ResponseEntity.ok(response);
		}
    }
	
	@PostMapping("/addrecipe")
    public ResponseEntity<ApiResponse<Void>> AddUnit(@RequestBody @Valid RecipeVO recipeVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			recipeService.save(userDetails.getUsername(), recipeVO);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Add Recipe Success", null, null);
			return ResponseEntity.ok(response);
		}
    }
	
	@PostMapping("/modifyrecipedetail")
    public ResponseEntity<ApiResponse<Void>> ModifyUnit(@RequestBody @Valid RecipeDetailVO recipeDetailVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			recipeService.modifyDetail(userDetails.getUsername(), recipeDetailVO);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Modify RecipeDetail Success", null, null);
	        return ResponseEntity.ok(response);
		}   
    }
	
	@PostMapping("/addrecipedetail")
    public ResponseEntity<ApiResponse<Void>> AddUnit(@RequestBody @Valid RecipeDetailVO recipeDetailVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			recipeService.saveDetail(userDetails.getUsername(), recipeDetailVO);
			response = new ApiResponse<>(HttpStatus.OK.value(), "Add RecipeDetail Success", null, null);
	        return ResponseEntity.ok(response);
		}   
    }
}
