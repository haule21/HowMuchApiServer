package howmuch.com.dto;

import lombok.Data;

@Data
public class RecipeDTO {
	private String UserId;
	private String RecipeKey;
	private String RecipeName;
	private Float PricePerUnit;
	private Float Price;
	private Float CostRatio;
}
