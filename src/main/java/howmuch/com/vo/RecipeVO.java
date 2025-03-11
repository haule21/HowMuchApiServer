package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecipeVO {
	@Pattern(regexp = "^R0[0-9]{17}$", message = "잘못된 양식의 Key입니다.")
	@JsonProperty("RecipeKey")
	private String RecipeKey;
	@Size(min = 1, max = 32, message = "요리명은 32자 이내여야 합니다.")
	@JsonProperty("RecipeName")
	private String RecipeName;
	@Pattern(regexp = "^-?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)$", message = "가격을 확인해 주세요.")
	@JsonProperty("Price")
	private Float Price;
}
