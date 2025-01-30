package howmuch.com.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import howmuch.com.service.LoginService;
import howmuch.com.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// TODO: User Regex, Pass Regex
@RestController
@RequestMapping(value = "/api")
public class MainController {

	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private LoginService loginService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserVO userVO, HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("==================================================================");
    	System.out.println(userVO.getUserId());
    	System.out.println(userVO.getPassword());
    	System.out.println("==================================================================");
    	UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userVO.getUserId(), userVO.getPassword());

		Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        HttpSession session = req.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        
        if (authentication != null) {
            System.out.println("Authenticated user: " + authentication.getName());
            System.out.println("Granted Authorities: " + authentication.getAuthorities());
        }
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "Sucess Login");
        response.put("state", true);
        return response;
    }
    
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserVO userVO) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	System.out.println("==================================================================");
    	System.out.println(userVO.getUserId());
    	System.out.println(userVO.getPassword());
    	System.out.println(loginService.getUserByUserId(userVO.getUserId()));
    	System.out.println("==================================================================");
    	if (loginService.getUserByUserId(userVO.getUserId()) == null) {
    		loginService.createUser(userVO.getUserId(), userVO.getPassword());
    	} else {
    		response.put("message", "Duplicate User");
            response.put("state", false);
            return response;
    	}
    	
        response.put("message", "Register User!");
        response.put("state", true);
        return response;
    }
}
