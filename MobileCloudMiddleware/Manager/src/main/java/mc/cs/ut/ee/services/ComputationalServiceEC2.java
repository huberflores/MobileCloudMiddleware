package mc.cs.ut.ee.services;

import java.io.IOException;

import mc.cs.ut.ee.manager.MobileTask;
import mc.cs.ut.ee.ns.core.Payload;
import mc.cs.ut.ee.utilities.DefaultConfigurations;

import WebApiWrapper.mcminteroperability;

public class ComputationalServiceEC2 extends MobileTask{

	private Payload keypairs;
	
	@Override
	public void performTask() {
		// TODO Auto-generated method stub
		mcminteroperability.startInstance(DefaultConfigurations.AMAZON_ACCESSKEY, DefaultConfigurations.AMAZON_SECRETKEY, DefaultConfigurations.AMI_TEST);
		
		keypairs = new Payload();
		keypairs.addAttribute("payload", "Computational instances is running in cloud");

		try {
			setResults(keypairs.getJsonNotification());
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}

}
