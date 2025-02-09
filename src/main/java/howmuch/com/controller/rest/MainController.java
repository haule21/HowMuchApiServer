package howmuch.com.controller.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import howmuch.com.common.RandomCreateManager;
import howmuch.com.dto.MailDTO;
import howmuch.com.dto.UsersDTO;
import howmuch.com.service.AccountService;
import howmuch.com.service.LoginService;
import howmuch.com.vo.EmailUserIdVO;
import howmuch.com.vo.EmailVO;
import howmuch.com.vo.UserVO;
import howmuch.com.vo.VerifyCodeVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

// TODO: User Regex, Pass Regex
@RestController
@RequestMapping(value = "/api")
public class MainController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private LoginService loginService;
	@Autowired
	private AccountService accountService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserVO userVO, HttpServletRequest req, HttpServletResponse res) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	try {
    		UsernamePasswordAuthenticationToken authenticationToken =
    	            new UsernamePasswordAuthenticationToken(userVO.getUserId(), userVO.getPassword());
    		// System.out.println(authenticationToken.toString());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            HttpSession session = req.getSession();
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            
            if (authentication != null) {
                System.out.println("Authenticated user: " + authentication.getName());
                System.out.println("Granted Authorities: " + authentication.getAuthorities());
            }
            loginService.modifyLoginFailNumReset(userVO.getUserId());
            response.put("message", "Sucess Login");
            response.put("state", true);
    	} catch (UsernameNotFoundException ex) {
    		response.put("message", "잘못된 사용자 정보입니다.");
            response.put("state", false);
    	} catch (LockedException ex) {
    		response.put("message", "계정이 잠겨있습니다. 패스워드 찾기를 통해 초기화 하여주세요.");
            response.put("state", false);
    	} catch (org.springframework.security.core.AuthenticationException ex) {
    		loginService.modifyLoginFailNum(userVO.getUserId());
    		response.put("message", "잘못된 사용자 정보입니다.");
            response.put("state", false);
    	}
    	return response;
    }
    
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserVO userVO) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	if (loginService.getUserByUserId(userVO.getUserId()) == null) {
    		if (loginService.validateEmail(userVO.getEmail()) == null) {
    			loginService.createUser(userVO.getUserId(), userVO.getPassword(), userVO.getName(), userVO.getEmail());
    		} else {
    			response.put("message", "Duplicate User");
                response.put("state", false);
                return response;
    		}
    	} else {
    		response.put("message", "Duplicate User");
            response.put("state", false);
            return response;
    	}
    	
        response.put("message", "Register User!");
        response.put("state", true);
        return response;
    }
    
    @PostMapping("/modifyuser")
    public Map<String, Object> modifyUser(@RequestBody UserVO userVO, @AuthenticationPrincipal UserDetails userDetails) {
    	Map<String, Object> response = new HashMap<String, Object>();

    	loginService.modifyUser(userDetails.getUsername(), userVO.getPassword(), userVO.getName(), userVO.getEmail());
    	
        response.put("message", "Modfiy Success.");
        response.put("state", true);
        return response;
    }
    
    @PostMapping("/sendverifyemail")
    public Map<String, Object> sendVerifyEmail(@RequestBody EmailVO emailVO, HttpServletRequest req) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	
    	String verifyCode = RandomCreateManager.getValidateCode();
    	HttpSession session = req.getSession();
    	session.setAttribute("VerifyCode", verifyCode);
    	MailDTO mailDTO = new MailDTO();
    	mailDTO.setMessage("마진요정 인증번호입니다.\n인증번호: " + verifyCode);
    	mailDTO.setTo(emailVO.getEmail());
    	mailDTO.setTitle("마진요정 이메일 인증번호 입니다.");
    	
    	try {
    		loginService.sendMail(mailDTO);
        	
    		response.put("message", "Success");
            response.put("state", true);
    	} catch (MailException e){
    		System.out.println(e);
    		response.put("message", e.getMessage());
            response.put("state", false);
    	}
    	
        return response;
    }
    
    @PostMapping("/validationcheckemail")
    public Map<String, Object> validateEmail(@RequestBody EmailVO emailVO) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	 
    	if (loginService.validateEmail(emailVO.getEmail()) == null) {
    		response.put("message", "Validate.");
            response.put("state", true);
    	} else {
    		response.put("message", "Duplicate");
            response.put("state", false);
    	}
    	
        return response;
    }
    @GetMapping("/mailCheck")
    public Map<String, Object> mailCheck(@RequestParam("VerifyCode") String verifyCode, HttpServletRequest req) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	HttpSession session = req.getSession();
    	String serverVerifyCode = (String) session.getAttribute("VerifyCode");
    	System.out.println("ServerVerifyCode: " + serverVerifyCode);
    	if (serverVerifyCode.equals(verifyCode)) {
    		response.put("result", null);
    		response.put("message", "Validate.");
            response.put("state", true);
            session.setAttribute("VerifyCode", null);
    	} else {
    		response.put("result", null);
    		response.put("message", "Fail");
            response.put("state", false);
    	}

        return response;
    }
    @Transactional
    @PostMapping("/findPw")
    public Map<String, Object> findPassword(@RequestBody EmailUserIdVO emailUserIdVO) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	UsersDTO user = loginService.validateEmail(emailUserIdVO.getEmail());
    	if (user == null) {
    		response.put("message", "Not Exists Email");
            response.put("state", false);
    	} else {
    		if (user.getUserId() == emailUserIdVO.getUserId()) {
    			String tempPassword = RandomCreateManager.getTmpPassword();
        		loginService.modifyUser(user.getUserId(), tempPassword, user.getName(), user.getEmail());
        		MailDTO mail = new MailDTO();
        		mail.setTo(emailUserIdVO.getUserId());
        		mail.setTitle("임시 비밀번호 안내 이메일입니다.");
        		mail.setMessage("안녕하세요. 임시 비밀번호 안내 메일입니다. "
        		          + "\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요." + "\n" + "패스워드: " + tempPassword+ "\n");
        		
        		try {
        			loginService.sendMail(mail);
            		response.put("message", "Send Email");
                    response.put("state", true);
            	} catch (MailException e){
            		response.put("message", "Fail send mail");
                    response.put("state", false);
            	}
        		
    		} else {
    			response.put("message", "Not Exists Email");
                response.put("state", false);
    		}	
    	}
    	
        
        return response;
    }
    @PostMapping("/logout")
    public Map<String, Object> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse res) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    	logoutHandler.logout(request, res, authentication);
		response.put("message", "Logout.");
        response.put("state", true);

        return response;
    }
    
    @PostMapping("/deleteaccount")
    public Map<String, Object> deleteUser(@AuthenticationPrincipal UserDetails userDetails, Authentication authentication, HttpServletRequest request, HttpServletResponse res) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	Map<String, Object> deleteState = accountService.DeleteUser(userDetails.getUsername());
    	if ((boolean)deleteState.get("state")) {
    		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        	logoutHandler.logout(request, res, authentication);
    		response.put("message", "Delete Account Success.");
            response.put("state", true);
    	} else {
    		response.put("message", "Delete Fail.");
    		response.put("state", false);
    	}
    	

        return response;
    }
}
