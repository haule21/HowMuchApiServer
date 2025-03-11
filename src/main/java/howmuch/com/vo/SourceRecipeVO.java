package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SourceRecipeVO {
	@Pattern(regexp = "^S0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("SourceKey")
	private String SourceKey;
	@Pattern(regexp = "^SR[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("SourceRecipeKey")
	private String SourceRecipeKey;
	@Pattern(regexp = "^I0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("IngredientKey")
	private String IngredientKey;
	@Pattern(regexp = "^-?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)$", message = "재료량을 확인해 주세요.")
	@JsonProperty("MaterialUsage")
	private Float MaterialUsage;
	@Pattern(regexp = "^U0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("UnitKey")
	private String IngredientUnitKey;
}
