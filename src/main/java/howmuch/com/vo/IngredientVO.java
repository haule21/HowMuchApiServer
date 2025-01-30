package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class IngredientVO {
	@JsonProperty("IngredientKey")
	private String IngredientKey;
	@JsonProperty("IngredientName")
	private String IngredientName;
	@JsonProperty("UnitValue")
	private Float UnitValue;
	@JsonProperty("UnitKey")
	private String UnitKey;
	@JsonProperty("Price")
	private Float Price;
}
