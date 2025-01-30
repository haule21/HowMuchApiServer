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
    public Map<String, Object> GetAllSource(@AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
			response.put("result", null);
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			List<SourceDTO> source = sourceService.getAllSource(userDetails.getUsername());
	        response.put("result", source);
	        response.put("message", "Sucess");
	        response.put("state", true);
		}   
		
		return response;
    }
	
	@GetMapping("/getallsourcerecipeingredients")
	@PreAuthorize("hasRole('USER')")
    public Map<String, Object> GetAllSourceRecipe(@RequestParam("SourceKey") String sourceKey, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
			response.put("result", null);
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			List<SourceRecipeDTO> sourceRecipes = sourceService.allSourceRecipeByUserIdSourceKey(userDetails.getUsername(), sourceKey);
	        response.put("result", sourceRecipes);
	        response.put("message", "Sucess");
	        response.put("state", true);
		}   
		
		return response;
    }
	@PostMapping("/modifysoruce")
    public Map<String, Object> ModifyUnit(@RequestBody SourceVO sourceVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = sourceService.modify(userDetails.getUsername(), sourceVO);
		}   
		
		return response;
    }
	
	@PostMapping("/addsoruce")
    public Map<String, Object> AddUnit(@RequestBody SourceVO sourceVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = sourceService.save(userDetails.getUsername(), sourceVO);
		}   
		
		return response; 
    }
	@PostMapping("/modifysorucerecipe")
    public Map<String, Object> ModifyUnit(@RequestBody SourceRecipeVO sourceRecipeVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = sourceService.modifySourceRecipe(userDetails.getUsername(), sourceRecipeVO);
		}   
		
		return response;
    }
	
	@PostMapping("/addsorucerecipe")
    public Map<String, Object> AddUnit(@RequestBody SourceRecipeVO sourceRecipeVO, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (userDetails.getUsername() == null) {
	        response.put("message", "Log in first.");
	        response.put("state", false);
		} else {
			response = sourceService.saveSourceRecipe(userDetails.getUsername(), sourceRecipeVO);
		}   
		
		return response; 
    }	
}
