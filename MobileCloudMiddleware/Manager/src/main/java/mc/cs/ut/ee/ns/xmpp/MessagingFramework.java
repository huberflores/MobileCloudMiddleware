package mc.cs.ut.ee.ns.xmpp;

import java.io.FileNotFoundException;
import java.io.IOException;

import mc.cs.ut.ee.ns.utilities.DefaultConfigurations;

import org.jivesoftware.smack.XMPPException;

/*
 * author
 */

public class MessagingFramework {
	
	private SessionHandler server = null;
	
	public MessagingFramework(){
		try {
			initialize();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initialize() throws FileNotFoundException, XMPPException, IOException{
		server =  new SessionHandler(DefaultConfigurations.JID, DefaultConfigurations.password);
		
	}	
	
	public void sendNotification(String receiver, Message message) throws FileNotFoundException, XMPPException, IOException{
		if (getServer()!=null){
			String jsonPayload = new MessagePacker(message).getJsonNotification().toString();
			getServer().sendMessage(jsonPayload, receiver);
		}else{
			System.out.println("Initialize messaging server");
		}
		
	}
	
	private SessionHandler getServer(){
		return this.server;
	}
	

}
