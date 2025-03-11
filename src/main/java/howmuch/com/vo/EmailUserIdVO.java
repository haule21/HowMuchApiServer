package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmailUserIdVO {
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "유효한 이메일 주소를 입력해주세요.")
	@JsonProperty("Email")
	private String Email;
	@NotBlank(message="ID를 입력해 주세요.")
	@JsonProperty("UserId")
	private String UserId;
}
