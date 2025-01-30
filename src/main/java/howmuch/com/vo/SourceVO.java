package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SourceVO {
	@JsonProperty("SourceKey")
	private String SourceKey;
	@JsonProperty("SourceName")
	private String SourceName;
	@JsonProperty("Amount")
	private Float Amount;
}
