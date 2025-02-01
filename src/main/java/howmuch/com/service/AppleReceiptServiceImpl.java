package howmuch.com.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import howmuch.com.api.AppleInAppPurchaseRequest;
import howmuch.com.api.AppleInAppPurchaseResponse;

@Service
public class AppleReceiptServiceImpl implements AppleReceiptService{
	@Value("${apple.shared.secret}") // App Store Connect에서 발급받은 공유 비밀 키
    private String sharedSecret;
	
    private final String APPLE_VERIFY_URL = "https://buy.itunes.apple.com/verifyReceipt"; // 프로덕션 환경 URL
    // 테스트 환경을 사용할 경우 URL: "https://sandbox.itunes.apple.com/verifyReceipt"
    @Override
    public Map<String, Object> verifyReceipt(AppleInAppPurchaseRequest receiptRequest) throws IOException, InterruptedException {
    	String appleUrl = "https://sandbox.itunes.apple.com/verifyReceipt"; // 실제 환경에서는 "https://buy.itunes.apple.com/verifyReceipt"
    	
    	Map<String, Object> result = new HashMap<>();
    	Map<String, String> requestMap = new HashMap<>();
        requestMap.put("receipt-data", receiptRequest.getReceiptData());
        
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<AppleInAppPurchaseResponse> responseEntity = restTemplate.postForEntity(appleUrl, requestMap, AppleInAppPurchaseResponse.class);
        
        AppleInAppPurchaseResponse purchaseResponse = responseEntity.getBody();

        if (purchaseResponse != null) {
            int status = purchaseResponse.getStatus();

            // status -> 0 이면 정상 처리
            if (status == 21007) {
                // Test 환경이라면 다시 체크
                responseEntity = restTemplate.postForEntity(appleUrl, requestMap, AppleInAppPurchaseResponse.class);
                purchaseResponse = responseEntity.getBody();
            } else if (status != 0) {
                throw new IllegalArgumentException("apple_receipt_error_" + status);
            }
            
            result.put("message", "success");
            result.put("state", true);
        } else {
        	result.put("message", "fail");
            result.put("state", false);
        }
        
        return result;
    }
}
