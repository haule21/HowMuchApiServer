package howmuch.com.handler;

import java.util.Map;

import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import howmuch.com.aop.LoggingAspect;
import howmuch.com.dto.ApiResponse;
import howmuch.com.exception.DuplicateEmailException;
import howmuch.com.exception.DuplicateUserException;
import howmuch.com.exception.ErrorCode;
import howmuch.com.exception.InvalidVerifyCodeException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
		return handleException("An error occurred", ErrorCode.INTERNAL_SERVER_ERROR);
    }
	
    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ConfigDataResourceNotFoundException ex) {
    	return handleException(ex.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidExceptionr(MethodArgumentNotValidException ex) {
    	return handleException(ex.getBindingResult().getFieldError().getDefaultMessage(), ErrorCode.INVALID_VALUE);
    }
    
    @ExceptionHandler(MailException.class)
	public ResponseEntity<ApiResponse<Void>> handleMailException(MailException ex) {
    	return handleException(ex.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(LockedException.class)
	public ResponseEntity<ApiResponse<Void>> handleLockedException(LockedException ex) {
		return handleException(ex.getMessage(), ErrorCode.ACCOUNT_LOCKED);
    }
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiResponse<Void>> handleAuthenticationException(AuthenticationException ex) {
		return handleException(ex.getMessage(), ErrorCode.USER_NOT_FOUND);
    }
	
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateEmailException(DuplicateEmailException ex) {
		return handleException(ex.getMessage(), ErrorCode.DUPLICATE_EMAIL);
    }
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateUserException(DuplicateEmailException ex) {
		return handleException(ex.getMessage(), ErrorCode.DUPLICATE_USER);
    }
	
	@ExceptionHandler(InvalidVerifyCodeException.class)
	public ResponseEntity<ApiResponse<Void>> handleInvalidVerifyCodeException(InvalidVerifyCodeException ex) {
		return handleException(ex.getMessage(), ErrorCode.INVALID_VERIFY_CODE);
    }
	
	@ExceptionHandler(SessionAuthenticationException.class)
	public ResponseEntity<ApiResponse<Void>> handleSessionAuthenticationException(SessionAuthenticationException ex) {
		return handleException(ex.getMessage(), ErrorCode.SESSION_EXPIRED);
    }
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ApiResponse<Void>> handleDataAccessException(DataAccessException ex) {
		return handleException(ex.getMessage(), ErrorCode.DB_ERROR);
    }
	
	protected ResponseEntity<ApiResponse<Void>> handleException(String exMessage, ErrorCode code) {
		Map<String, String> metadata = Map.of("errorCode", code.getCode());
        ApiResponse<Void> response = new ApiResponse<>(code.getHttpStatus().value(), exMessage, null, metadata);
		return ResponseEntity.status(code.getHttpStatus()).body(response);
	}
}
