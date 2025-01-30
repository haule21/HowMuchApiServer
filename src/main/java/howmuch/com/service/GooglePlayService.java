package howmuch.com.service;

public interface GooglePlayService {
	boolean isSubscriptionActive(String packageName, String subscriptionId, String purchaseToken);
}
