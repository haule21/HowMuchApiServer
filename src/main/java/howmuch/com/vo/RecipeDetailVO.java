package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RecipeDetailVO {
	@Pattern(regexp = "^R0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("RecipeKey")
	private String RecipeKey;
	@Pattern(regexp = "^RD[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("RecipeDetailKey")
	private String RecipeDetailKey;
	@Pattern(regexp = "^I0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("IngredientKey")
	private String IngredientKey;
	@Pattern(regexp = "^-?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)$", message = "재료 양을 확인해 주세요.")
	@JsonProperty("MaterialUsage")
	private Float MaterialUsage;
	@Pattern(regexp = "^U0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("UnitKey")
	private String IngredientUnitKey;
}
