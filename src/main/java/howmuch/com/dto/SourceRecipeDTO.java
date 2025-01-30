package howmuch.com.dto;

import lombok.Data;

@Data
public class SourceRecipeDTO {
	private String UserId;
	private String SourceKey;
	private String SourceName;
	private String SourceRecipeKey;
	private Integer Seq;
	private String IngredientKey;
	private String IngredientName;
	private Float MaterialUsage;
	private String UnitKey;
	private String UnitName;
}
