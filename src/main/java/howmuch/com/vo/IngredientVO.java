package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IngredientVO {
	@Pattern(regexp = "^I0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("IngredientKey")
	private String IngredientKey;
	@Size(max = 32, message = "재료 명은 32자 이내여야 합니다.")
	@JsonProperty("IngredientName")
	private String IngredientName;
	@Size(max = 8, message = "단위 값은 8자 이내여야 합니다.")
	@Pattern(regexp = "^-?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)$", message = "단위 값을 확인해 주세요.")
	@JsonProperty("UnitValue")
	private Float UnitValue;
	@Pattern(regexp = "^U0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("UnitKey")
	private String UnitKey;
	@Pattern(regexp = "^-?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)$", message = "가격을 확인해 주세요.")
	@JsonProperty("Price")
	private Float Price;
}
