package mc.cs.ut.ee.ns.gcm;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import mc.cs.ut.ee.ns.core.NotificationService;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;


import mc.cs.ut.ee.ns.utilities.DefaultConfigurations;

@SuppressWarnings("serial")
public class GCMServer implements NotificationService {

	private Sender sender;
	
    private String message;
    private String jsonKeyPairs;
 

    public GCMServer (){
    	sender = new Sender(DefaultConfigurations.GCM_APIKEY);
    }
    
   
	public NotificationService withDeviceData(String deviceID, String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	public NotificationService withMessage(String message) {
		this.message = message;
        return this;
	}

	public NotificationService withPayload(String jsonKeyPairs) {
		this.jsonKeyPairs = jsonKeyPairs;
		return this;
	}

	public void sendMessage() {
		// TODO Auto-generated method stub
		List<String> devices = GCMDataStore.getDevices();
	    StringBuilder status = new StringBuilder();
	    
	    if (devices.isEmpty()) {
	        status.append("Message ignored as there is no device registered!");
	    } else {
	        List<Result> results;
	        
	        if (devices.size() == 1) {
	          // send a single message using plain post
	          String registrationId = devices.get(0);
	          
	          Message message = new Message.Builder()
              .collapseKey("1")
              .timeToLive(3)
              .delayWhileIdle(true)
              .addData("payload", jsonKeyPairs)
              .build();
	          
	          Result result;
	          	try {
	          		result = sender.send(message, registrationId, 5);
	          		results = Arrays.asList(result);
	          	} catch (IOException e) {
	          		e.printStackTrace();
	          	}
	          
	        } else {
	          Message message = new Message.Builder().build();
	          MulticastResult result;
	          	try {
	          		result = sender.send(message, devices, 5);
	          		results = result.getResults();
	          	} catch (IOException e) {
	          		e.printStackTrace();
	          	}
	          
	        }
	    }		
	}
	
	
			
}
