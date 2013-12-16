package mc.cs.ut.ee.services;

import java.io.IOException;

import mc.cs.ut.ee.manager.MobileTask;
import mc.cs.ut.ee.ns.core.Payload;
import mc.cs.ut.ee.utilities.DefaultConfigurations;

import WebApiWrapper.mcminteroperability;


public class StorageServiceS3 extends MobileTask{
	
    private Payload keypairs;
	
	@Override
	public void performTask() {
		// TODO Auto-generated method stub
		if (getData().isEmpty()){
			
		}else{
			mcminteroperability.awsS3(DefaultConfigurations.AMAZON_ACCESSKEY, DefaultConfigurations.AMAZON_SECRETKEY,getData().get(0).getAbsolutePath(), "clobucket");
		}
	
		keypairs = new Payload();
		keypairs.addAttribute("payload", "File was upload to S3");

		try {
			setResults(keypairs.getJsonNotification());
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

	}
}
