package howmuch.com.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class IngredientDTO {
	private String UserId;
	private String IngredientKey;
	private String IngredientName;
	private Float UnitValue;
	private String UnitKey;
	private String UnitName;
	private Float Price;
}