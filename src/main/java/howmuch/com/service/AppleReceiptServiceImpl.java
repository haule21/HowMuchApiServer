package howmuch.com.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppleReceiptServiceImpl implements AppleReceiptService{
	@Value("${apple.shared.secret}") // App Store Connect에서 발급받은 공유 비밀 키
    private String sharedSecret;
	
    private final String APPLE_VERIFY_URL = "https://buy.itunes.apple.com/verifyReceipt"; // 프로덕션 환경 URL
    // 테스트 환경을 사용할 경우 URL: "https://sandbox.itunes.apple.com/verifyReceipt"

    public boolean verifyAppleReceipt(String receiptData) throws IOException, InterruptedException {
        String appleUrl = "https://sandbox.itunes.apple.com/verifyReceipt"; // 실제 환경에서는 "https://buy.itunes.apple.com/verifyReceipt"
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(appleUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"receipt-data\": \"" + receiptData + "\"}"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            // 응답에서 필요한 데이터 파싱하여 유효성 검사
            return responseBody.contains("\"status\": 0"); // 0이면 유효한 영수증
        } else {
            return false; // 오류 발생
        }
    }
}
