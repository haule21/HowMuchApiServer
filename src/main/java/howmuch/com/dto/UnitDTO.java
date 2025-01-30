package howmuch.com.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UnitDTO {
	private String UserId;
	private String UnitKey;
	private String ParentUnitKey;
	private Float ParentUnitRelation;
	private String UnitName;
	private Float Value;
	private String Description;
}
