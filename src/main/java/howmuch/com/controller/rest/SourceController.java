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
import howmuch.com.dto.SourceDTO;
import howmuch.com.dto.SourceRecipeDTO;
import howmuch.com.service.SourceService;
import howmuch.com.vo.SourceRecipeVO;
import howmuch.com.vo.SourceVO;
import howmuch.com.vo.UnitVO;

@RestController
@RequestMapping(value = "/api/source")
public class SourceController {
	@Autowired
	private SourceService sourceService;
	
	@GetMapping("/getallsource")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<SourceDTO>>> GetAllSource(@AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<List<SourceDTO>> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			List<SourceDTO> source = sourceService.getAllSource(userDetails.getUsername());
			response = new ApiResponse<>(HttpStatus.OK, "Get Source Success", source, null);
			return ResponseEntity.ok(response);
		}   
    }
	
	@GetMapping("/getallsourcerecipeingredients")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<SourceRecipeDTO>>> GetAllSourceRecipe(@RequestParam("SourceKey") String sourceKey, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<List<SourceRecipeDTO>> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			List<SourceRecipeDTO> sourceRecipes = sourceService.allSourceRecipeByUserIdSourceKey(userDetails.getUsername(), sourceKey);
			response = new ApiResponse<>(HttpStatus.OK, "Get Source Recipe Success", sourceRecipes, null);
			return ResponseEntity.ok(response);
		}   
    }
	@PostMapping("/modifysoruce")
    public ResponseEntity<ApiResponse<Void>> ModifyUnit(@RequestBody SourceVO sourceVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			sourceService.modify(userDetails.getUsername(), sourceVO);
			response = new ApiResponse<>(HttpStatus.OK, "Modify Source Success", null, null);
			return ResponseEntity.ok(response);
		}
    }
	
	@PostMapping("/addsoruce")
    public ResponseEntity<ApiResponse<Void>> AddUnit(@RequestBody SourceVO sourceVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			sourceService.save(userDetails.getUsername(), sourceVO);
			response = new ApiResponse<>(HttpStatus.OK, "Add Source Success", null, null);
			return ResponseEntity.ok(response);
		}
    }
	@PostMapping("/modifysorucerecipe")
    public ResponseEntity<ApiResponse<Void>> ModifyUnit(@RequestBody SourceRecipeVO sourceRecipeVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			sourceService.modifySourceRecipe(userDetails.getUsername(), sourceRecipeVO);
			response = new ApiResponse<>(HttpStatus.OK, "Modify Source Recipe Success", null, null);
			return ResponseEntity.ok(response);
		}   
    }
	
	@PostMapping("/addsorucerecipe")
    public ResponseEntity<ApiResponse<Void>> AddUnit(@RequestBody SourceRecipeVO sourceRecipeVO, @AuthenticationPrincipal UserDetails userDetails) {
		ApiResponse<Void> response;
		if (userDetails.getUsername() == null) {
			throw new SessionAuthenticationException("먼저 로그인하여 주세요.");
		} else {
			sourceService.saveSourceRecipe(userDetails.getUsername(), sourceRecipeVO);
			response = new ApiResponse<>(HttpStatus.OK, "Add Source Recipe Success", null, null);
			return ResponseEntity.ok(response);
		}    
    }	
}
