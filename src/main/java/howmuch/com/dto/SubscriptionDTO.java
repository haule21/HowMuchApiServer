package howmuch.com.dto;

import lombok.Data;

@Data
public class SubscriptionDTO {
	private String PackageName;
    private String SubscriptionId;
    private String PurchaseToken;
}
