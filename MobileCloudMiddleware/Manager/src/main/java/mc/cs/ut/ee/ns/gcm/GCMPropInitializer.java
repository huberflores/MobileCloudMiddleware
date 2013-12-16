package mc.cs.ut.ee.ns.gcm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mc.cs.ut.ee.ns.utilities.DefaultConfigurations;

public class GCMPropInitializer implements ServletContextListener {
	
	static final String ATTRIBUTE_ACCESS_KEY = "apiKey";
	
	private String apiKey;
	
	private Properties GCMprops = new Properties();

	/*
	 * gcm.properties is to be located at the same level of WEB-INF
	 */
	public void contextInitialized(ServletContextEvent event) {
		
		
		try {
			apiKey = getKey("gcm.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//may be removed at deployment
		if (apiKey == null || apiKey.trim().isEmpty()){
			apiKey = DefaultConfigurations.GCM_APIKEY;
		}
		
		event.getServletContext().setAttribute(ATTRIBUTE_ACCESS_KEY, apiKey);	

		
	}
	
	public String getKey(String filepath) throws FileNotFoundException, IOException{
		GCMprops.load(new FileInputStream(filepath));
		String key = GCMprops.getProperty("apiKey");

		return key;
	}

	
    public void contextDestroyed(ServletContextEvent event) {

	}	

}


