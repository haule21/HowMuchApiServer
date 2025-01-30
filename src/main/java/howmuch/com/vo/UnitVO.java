package howmuch.com.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UnitVO {
	@JsonProperty("UnitKey")
	private String UnitKey;
	@JsonProperty("ParentUnitKey")
	private String ParentUnitKey;
	@JsonProperty("ParentUnitRelation")
	private Float ParentUnitRelation;
	@JsonProperty("UnitName")
	private String UnitName;
	@JsonProperty("Value")
	private Float Value;
	@JsonProperty("Desc")
	private String Description;
}
