package howmuch.com.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;  // HTTP status code (e.g., 200, 404)
    private String message;     // Description of the outcome
    private T data;             // The actual data (can be null for error cases)
    private Object metadata;    // Optional metadata like pagination info
}