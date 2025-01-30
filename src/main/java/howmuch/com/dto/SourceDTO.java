package howmuch.com.dto;

import lombok.Data;

@Data
public class SourceDTO {
	private String UserId;
	private String SourceKey;
	private String SourceName;
	private Float Amount;
	private Float PricePerUnit;
}
