package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VerifyCodeVO {
	@Size(max = 6, message = "값은 6자 이내여야 합니다.")
	@JsonProperty("VerifyCode")
	private String VerifyCode;
}
