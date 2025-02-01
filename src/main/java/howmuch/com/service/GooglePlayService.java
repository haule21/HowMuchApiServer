package howmuch.com.service;

import java.io.IOException;
import java.util.Map;

public interface GooglePlayService {
	Map<String, Object> isSubscriptionActive(String packageName, String subscriptionId, String purchaseToken) throws IOException;
}
