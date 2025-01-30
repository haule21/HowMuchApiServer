package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SourceRecipeVO {
	@JsonProperty("SourceKey")
	private String SourceKey;
	@JsonProperty("SourceRecipeKey")
	private String SourceRecipeKey;
	@JsonProperty("IngredientKey")
	private String IngredientKey;
	@JsonProperty("MaterialUsage")
	private Float MaterialUsage;
	@JsonProperty("UnitKey")
	private String IngredientUnitKey;
}
