package howmuch.com.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_001", "User not found"),
	ACCOUNT_LOCKED(HttpStatus.FORBIDDEN, "USER_002", "Account Locekd"),
    DUPLICATE_EMAIL(HttpStatus.FORBIDDEN, "USER_003", "Duplicate Email"),
    DUPLICATE_USER(HttpStatus.FORBIDDEN, "USER_004", "Duplicate User"),
    INVALID_VERIFY_CODE(HttpStatus.FORBIDDEN, "USER_005", "Invalid VerifyCode"),
    NOT_EXISTS_EMAIL(HttpStatus.NOT_FOUND, "USER_006", "Not Exists Email"),
    DELETE_ACCOUNT_FAILED(HttpStatus.FORBIDDEN, "USER_007", "Delete Account Failed"),
    SESSION_EXPIRED(HttpStatus.FORBIDDEN, "USER_008", "Session Expired"),
    DB_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "PROC_001", "Procedure Execute Failed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SYS_001", "Internal server error");
	
	private final HttpStatus httpStatus;
	private final String code;
    private final String description;

    ErrorCode(HttpStatus httpStatus, String code, String description) {
    	this.httpStatus = httpStatus;
        this.code = code;
        this.description = description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
