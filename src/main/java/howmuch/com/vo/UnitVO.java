package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UnitVO {
	@Pattern(regexp = "^U0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("UnitKey")
	private String UnitKey;
	@Pattern(regexp = "^(X0[0-9]{17})?$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("ParentUnitKey")
	private String ParentUnitKey;
	@Size(max = 8, message = "값은 8자 이내여야 합니다.")
	@Pattern(regexp = "^(-?([0-9]+(\\.[0-9]*)?|\\.[0-9]+))?$", message = "상위 단위 값을 확인해 주세요.")
	@JsonProperty("ParentUnitRelation")
	private Float ParentUnitRelation;
	@Size(max = 32, message = "단위 명은 32자 이내여야 합니다.")
	@JsonProperty("UnitName")
	private String UnitName;
	@Size(max = 8, message = "단위 값은 8자 이내여야 합니다.")
	@Pattern(regexp = "^-?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)$", message = "단위 값을 확인해 주세요.")
	@JsonProperty("Value")
	private Float Value;
	@Size(max = 64, message = "설명은 64자 이내여야 합니다.")
	@JsonProperty("Description")
	private String Description;
}
