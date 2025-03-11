package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter 
public class RegisterVO {
	@Size(min = 4, max = 16, message = "아이디는 4자 이상 16자 이하로 입력해주세요.")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "아이디는 알파벳과 숫자만 포함할 수 있습니다.")
	@JsonProperty("UserId")
	private String UserId;
	
	@Size(min = 8, max = 64, message = "패스워드는 8자 이상이여야 합니다.")
	@JsonProperty("Password")
	private String Password;
	
	@Size(min = 2, max = 16, message = "이름은 2자 이상 16자 이하로 입력해주세요.")
	@JsonProperty("Name")
	private String Name;

	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "유효한 이메일 주소를 입력해주세요.")
	@JsonProperty("Email")
	private String Email;
	
	public RegisterVO() {
	}
}
