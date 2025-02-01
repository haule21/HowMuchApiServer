package howmuch.com.controller.rest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import howmuch.com.api.AppleInAppPurchaseRequest;
import howmuch.com.dto.SubscriptionDTO;
import howmuch.com.service.AppleReceiptService;
import howmuch.com.service.AppleReceiptServiceImpl;
import howmuch.com.service.GooglePlayService;
import howmuch.com.service.GooglePlayServiceImpl;

@RestController
@RequestMapping(value = "/api/subscription")
public class SubscriptionController {
	
	private final GooglePlayService googlePlayService;
	private final AppleReceiptService appleReceiptService;

	public SubscriptionController() throws GeneralSecurityException {
		googlePlayService = new GooglePlayServiceImpl();
		this.appleReceiptService = new AppleReceiptServiceImpl();
	}

    @PostMapping("/check/android")
    public Map<String, Object> checkSubscriptionStatus(@RequestBody SubscriptionDTO request) throws IOException {
        return googlePlayService.isSubscriptionActive(
                request.getPackageName(),
                request.getSubscriptionId(),
                request.getPurchaseToken()
        );
    }
    
    @PostMapping("/check/ios")
    public Map<String, Object> checkAppleSubscription(@RequestBody AppleInAppPurchaseRequest receiptData) throws IOException, InterruptedException {
        return appleReceiptService.verifyReceipt(receiptData);
    }
}
