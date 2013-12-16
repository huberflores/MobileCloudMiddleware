/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.cs.ut.ee.ns.apns;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import mc.cs.ut.ee.ns.core.NotificationService;


public class IPhoneServer implements NotificationService {

    String token;
    String message;
    Map<String, String> customFields;
    public IPhoneServer() {
    }

    
    public NotificationService withDeviceData(String deviceID, String... args) {
        token = deviceID;
        return this;
    }

    
    public NotificationService withMessage(String message) {
        this.message = message;
        return this;
    }
    
    
    public NotificationService withKeyValuePairs(String... keyValuePairs) {
        int pairs = keyValuePairs.length % 2 == 0 ? keyValuePairs.length : keyValuePairs.length - 1;
        if (customFields == null) {
            customFields = new HashMap<String, String>();
        }
        for (int i = 0; i < pairs; i+=2) {
            customFields.put(keyValuePairs[i], keyValuePairs[i+1]);
        }
        return this;
    }
    
    public void sendMessage() {
        ApnsService service = null;

        try {
            InputStream certStream = this.getClass().getClassLoader().getResourceAsStream("Certificates.p12");
            APNS.newPayload();
            service = APNS.newService()
					.withCert(certStream, "iphone4")
					.withSandboxDestination()
					.build();

            service.start();

            String payload = APNS.newPayload()
                    .alertBody(message)
                    .badge(1)
                    .customFields(customFields)
                    .build();
			System.out.println("IPHONE NOTIFICATION PROVIDER : SENDING MESSAGE");
            service.push(token, payload);
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            if (service != null) {
                service.stop();
            }
        }
    }

	public NotificationService withPayload(String payload) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
