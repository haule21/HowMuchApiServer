package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VerifyCodeVO {
	@JsonProperty("VerifyCode")
	private String VerifyCode;
}
