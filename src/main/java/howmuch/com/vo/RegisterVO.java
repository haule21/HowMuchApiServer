package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;

@Getter 
public class RegisterVO {
	@JsonProperty("UserId")
	private String UserId;
	@JsonProperty("Password")
	private String Password;
	@JsonProperty("Name")
	private String Name;
	@JsonProperty("Email")
	private String Email;
	
	public RegisterVO() {
	}
}
