package mc.cs.ut.ee.ns.utilities;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mc.cs.ut.ee.ns.core.NotificationManager;

public class SendMessageToDevices extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
		
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	{
		NotificationManager.getNotificationProvider("android")
	    .withPayload("test")
		.withMessage("test")
		.sendMessage();
	}
	
	
	

}
