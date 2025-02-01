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

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisher.Purchases;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.api.services.androidpublisher.model.SubscriptionPurchase;

@Service
public class GooglePlayServiceImpl implements GooglePlayService{

    private AndroidPublisher publisher;
    private GoogleCredential credential;

    public GooglePlayServiceImpl() throws GeneralSecurityException {
        try {
        	ClassPathResource resource = new ClassPathResource("testvision01-13aa4b7c04e3.json");
            InputStream inputStream = resource.getInputStream();
            
            // GoogleCredential 초기화
            credential = GoogleCredential.fromStream(inputStream)
                    .createScoped(Collections.singleton("https://www.googleapis.com/auth/androidpublisher"));
            publisher = new AndroidPublisher.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    (HttpRequestInitializer)(credential)
                    
            ).setApplicationName("howmuch").build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 구독 상태 확인
    public Map<String, Object> isSubscriptionActive(String packageName, String subscriptionId, String purchaseToken) throws IOException {
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	AndroidPublisher.Purchases.Subscriptions.Get get = publisher.purchases().subscriptions().get(packageName, subscriptionId, purchaseToken);
        get.setToken(credential.getAccessToken());
        SubscriptionPurchase purchase = get.execute();
        
        if (purchase != null && purchase.getLinkedPurchaseToken().equals(purchaseToken) && purchase.getPaymentState() == 0) {
        	result.put("message", "success");
        	result.put("state", true);
        } else {
        	result.put("message", "not available subscription.");
        	result.put("state", false);
        }
        return result;
    }
}
