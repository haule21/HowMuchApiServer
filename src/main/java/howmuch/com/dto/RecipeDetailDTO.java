package howmuch.com.dto;

import lombok.Data;

@Data
public class RecipeDetailDTO {
	private String UserId;
	private String RecipeKey;
	private String RecipeName;
	private String RecipeDetailKey;
	private Integer Seq;
	private String IngredientKey;
	private String IngredientName;
	private Float MaterialUsage;
	private String UnitKey;
	private String UnitName;
}
