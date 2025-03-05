package howmuch.com.controller.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.naming.AuthenticationException;

import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
import org.springframework.web.server.ResponseStatusException;

import howmuch.com.common.RandomCreateManager;
import howmuch.com.dto.ApiResponse;
import howmuch.com.dto.MailDTO;
import howmuch.com.dto.UsersDTO;
import howmuch.com.exception.DuplicateEmailException;
import howmuch.com.exception.DuplicateUserException;
import howmuch.com.exception.EmailNotFoundException;
import howmuch.com.exception.InvalidVerifyCodeException;
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
    public ResponseEntity<ApiResponse<Void>> login(@RequestBody UserVO userVO, HttpServletRequest req, HttpServletResponse res) {
    	ApiResponse<Void> response;
    	// Map<String, Object> response = new HashMap<String, Object>();
		UsernamePasswordAuthenticationToken authenticationToken =
	            new UsernamePasswordAuthenticationToken(userVO.getUserId(), userVO.getPassword());
		// System.out.println(authenticationToken.toString());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        HttpSession session = req.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        
        loginService.modifyLoginFailNumReset(userVO.getUserId());
        response = new ApiResponse<>(HttpStatus.OK, "Login Success", null, null);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody UserVO userVO) {
    	ApiResponse<Void> response;
    	if (loginService.getUserByUserId(userVO.getUserId()) == null) {
    		if (loginService.validateEmail(userVO.getEmail()) == null) {
    			loginService.createUser(userVO.getUserId(), userVO.getPassword(), userVO.getName(), userVO.getEmail());
    			response = new ApiResponse<>(HttpStatus.OK, "Register Success", null, null);
    			return ResponseEntity.ok(response);
    		} else {
    			throw new DuplicateEmailException("중복된 이메일 입니다.");
    		}
    	} else {
    		throw new DuplicateUserException("중복된 계정 입니다.");
    	}
    }
    
    @PostMapping("/modifyuser")
    public ResponseEntity<ApiResponse<Void>> modifyUser(@RequestBody UserVO userVO, @AuthenticationPrincipal UserDetails userDetails) {
    	ApiResponse<Void> response;
    	loginService.modifyUser(userDetails.getUsername(), userVO.getPassword(), userVO.getName(), userVO.getEmail());
    	
    	response = new ApiResponse<>(HttpStatus.OK, "Modify User Success", null, null);
    	return ResponseEntity.ok(response);
    }
    
    @PostMapping("/sendverifyemail")
    public ResponseEntity<ApiResponse<Void>> sendVerifyEmail(@RequestBody EmailVO emailVO, HttpServletRequest req) {
    	ApiResponse<Void> response;
    	
    	String verifyCode = RandomCreateManager.getValidateCode();
    	HttpSession session = req.getSession();
    	session.setAttribute("VerifyCode", verifyCode);
    	MailDTO mailDTO = new MailDTO();
    	mailDTO.setMessage("마진요정 인증번호입니다.\n인증번호: " + verifyCode);
    	mailDTO.setTo(emailVO.getEmail());
    	mailDTO.setTitle("마진요정 이메일 인증번호 입니다.");
    	
		loginService.sendMail(mailDTO);
		response = new ApiResponse<>(HttpStatus.OK, "Mail Send Success", null, null);
		return ResponseEntity.ok(response);
    }
    
    @PostMapping("/validationcheckemail")
    public ResponseEntity<ApiResponse<Void>> validateEmail(@RequestBody EmailVO emailVO) {
    	ApiResponse<Void> response;
    	 
    	if (loginService.validateEmail(emailVO.getEmail()) == null) {
    		response = new ApiResponse<>(HttpStatus.OK, "Validate Email", null, null);
    		return ResponseEntity.ok(response);
    	} else {
    		throw new DuplicateEmailException("이미 가입된 이메일 입니다.");
    	}
    }
    @GetMapping("/mailCheck")
    public ResponseEntity<ApiResponse<Void>> mailCheck(@RequestParam("VerifyCode") String verifyCode, HttpServletRequest req) {
    	ApiResponse<Void> response;
    	HttpSession session = req.getSession();
    	String serverVerifyCode = (String) session.getAttribute("VerifyCode");
    	System.out.println("ServerVerifyCode: " + serverVerifyCode);
    	if (serverVerifyCode.equals(verifyCode)) {
            session.setAttribute("VerifyCode", null);
            response = new ApiResponse<>(HttpStatus.OK, "Verify Success", null, null);
            return ResponseEntity.ok(response);
    	} else {
    		throw new InvalidVerifyCodeException("잘못된 인증번호 입니다.");
    	}
    }
    @Transactional
    @PostMapping("/findPw")
    public ResponseEntity<ApiResponse<Void>> findPassword(@RequestBody EmailUserIdVO emailUserIdVO) {
    	ApiResponse<Void> response;
    	UsersDTO user = loginService.validateEmail(emailUserIdVO.getEmail());
    	if (user == null) {
    		throw new EmailNotFoundException("존재하지 않는 이메일 입니다.");
    	} else {
    		if (user.getUserId() == emailUserIdVO.getUserId()) {
    			String tempPassword = RandomCreateManager.getTmpPassword();
        		loginService.modifyUser(user.getUserId(), tempPassword, user.getName(), user.getEmail());
        		MailDTO mail = new MailDTO();
        		mail.setTo(emailUserIdVO.getUserId());
        		mail.setTitle("임시 비밀번호 안내 이메일입니다.");
        		mail.setMessage("안녕하세요. 임시 비밀번호 안내 메일입니다. "
        		          + "\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요." + "\n" + "패스워드: " + tempPassword+ "\n");
        		
    			loginService.sendMail(mail);
    			response = new ApiResponse<>(HttpStatus.OK, "Send Email", null, null);
                return ResponseEntity.ok(response);
    		} else {
    			throw new EmailNotFoundException("존재하지 않는 이메일 입니다.");
    		}	
    	}
    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse res) {
    	ApiResponse<Void> response;
    	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    	logoutHandler.logout(request, res, authentication);
    	response = new ApiResponse<>(HttpStatus.OK, "Logout", null, null);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/deleteaccount")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@AuthenticationPrincipal UserDetails userDetails, Authentication authentication, HttpServletRequest request, HttpServletResponse res) {
    	ApiResponse<Void> response;
    	if (accountService.DeleteUser(userDetails.getUsername()) > 0) {
    		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        	logoutHandler.logout(request, res, authentication);
        	response = new ApiResponse<>(HttpStatus.OK, "Delete Account Success", null, null);
            return ResponseEntity.ok(response);
    	} else {
    		throw new UsernameNotFoundException("계정 삭제에 실패하였습니다.");
    	}    	
    }
}
