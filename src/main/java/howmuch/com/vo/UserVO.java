package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter 
public class UserVO {
	@NotBlank(message="ID를 입력해 주세요.")
	@JsonProperty("UserId")
	private String UserId;
	@NotBlank(message="Password를 입력해 주세요.")
	@JsonProperty("Password")
	private String Password;
	
	public UserVO() {
	}
}
