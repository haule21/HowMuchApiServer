package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;

@Getter 
public class UserVO {
	@JsonProperty("UserId")
	private String UserId;
	@JsonProperty("Password")
	private String Password;
	
	public UserVO() {
	}
}
