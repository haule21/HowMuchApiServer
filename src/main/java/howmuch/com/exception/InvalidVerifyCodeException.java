package howmuch.com.exception;

public class InvalidVerifyCodeException extends RuntimeException {
    public InvalidVerifyCodeException(String message) {
        super(message);
    }
}