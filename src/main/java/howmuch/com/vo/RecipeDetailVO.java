package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RecipeDetailVO {
	@JsonProperty("RecipeKey")
	private String RecipeKey;
	@JsonProperty("RecipeDetailKey")
	private String RecipeDetailKey;
	@JsonProperty("IngredientKey")
	private String IngredientKey;
	@JsonProperty("MaterialUsage")
	private Float MaterialUsage;
	@JsonProperty("UnitKey")
	private String IngredientUnitKey;
}
