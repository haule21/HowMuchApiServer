package howmuch.com.service;

import java.io.IOException;
import java.util.Map;

import howmuch.com.api.AppleInAppPurchaseRequest;

public interface AppleReceiptService {
	Map<String, Object> verifyReceipt(AppleInAppPurchaseRequest receiptRequest) throws IOException, InterruptedException;
}
