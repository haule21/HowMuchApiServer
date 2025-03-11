package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SourceVO {
	@Pattern(regexp = "^S0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("SourceKey")
	private String SourceKey;
	@Size(max = 32, message = "소스 명은 32자 이내여야 합니다.")
	@JsonProperty("SourceName")
	private String SourceName;
	@Pattern(regexp = "^-?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)$", message = "소스량을 확인해 주세요.")
	@JsonProperty("Amount")
	private Float Amount;
}
