package howmuch.com.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import howmuch.com.dto.SubscriptionDTO;
import howmuch.com.service.AppleReceiptService;
import howmuch.com.service.GooglePlayService;

@RestController
@RequestMapping(value = "/api/subscription")
public class SubscriptionController {
	@Autowired
	private GooglePlayService googlePlayService;
	
	@Autowired
	private AppleReceiptService appleReceiptService;


    @PostMapping("/check/android")
    public boolean checkSubscriptionStatus(@RequestBody SubscriptionDTO request) {
        return googlePlayService.isSubscriptionActive(
                request.getPackageName(),
                request.getSubscriptionId(),
                request.getPurchaseToken()
        );
    }
    
    @PostMapping("/check/ios")
    public boolean checkAppleSubscription(@RequestBody String receiptData) {
        return appleReceiptService.verifyReceipt(receiptData);
    }
}
