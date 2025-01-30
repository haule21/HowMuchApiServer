package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RecipeVO {
	@JsonProperty("RecipeKey")
	private String RecipeKey;
	@JsonProperty("RecipeName")
	private String RecipeName;
	@JsonProperty("Price")
	private Float Price;
}
