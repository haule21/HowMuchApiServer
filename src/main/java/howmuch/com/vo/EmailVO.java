package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmailVO {
	@JsonProperty("Email")
	private String Email;
}
