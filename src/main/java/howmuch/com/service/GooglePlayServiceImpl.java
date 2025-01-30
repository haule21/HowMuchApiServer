package howmuch.com.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisher.Purchases;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

@Service
public class GooglePlayServiceImpl implements GooglePlayService{

    private AndroidPublisher publisher;
    private GoogleCredentials credential;

    public GooglePlayServiceImpl() throws GeneralSecurityException {
        try {
        	ClassPathResource resource = new ClassPathResource("testvision01-13aa4b7c04e3.json");
            InputStream inputStream = resource.getInputStream();
            
            // GoogleCredential 초기화
            credential = GoogleCredentials.fromStream(inputStream)
                    // .createScoped(Collections.singleton("https://www.googleapis.com/auth/androidpublisher"));
            		.createScoped(AndroidPublisherScopes.ANDROIDPUBLISHER);
            publisher = new AndroidPublisher.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    (HttpRequestInitializer) new HttpCredentialsAdapter(credential)
                    
            ).setApplicationName("howmuch").build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 구독 상태 확인
    public Map<String, Object> isSubscriptionActive(String packageName, String subscriptionId, String purchaseToken) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	Purchases.PurchasesClient purchasesClient = BillingClient.newBuilder(context).setListener(new PurchasesUpdatedListener() {}).build();
        Purchase purchase = purchasesClient.queryPurchaseHistoryAsync(BillingClient.SkuType.SUBS).getPurchaseList();
        
        if (purchase != null && purchase.getPurchaseToken().equals(purchaseToken)) {
            return true; // 유효한 구매
        } else {
            return false; // 구매 실패
        }
    }
}
