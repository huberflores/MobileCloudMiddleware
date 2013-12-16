package mc.cs.ut.ee.ns.core;

import mc.cs.ut.ee.ns.apns.IPhoneServer;
import mc.cs.ut.ee.ns.gcm.GCMServer;

public class NotificationManager {

	public static NotificationService getNotificationProvider(String platform) {
		if (platform.equals("iOS")) {
			return new IPhoneServer();
		} else if (platform.equalsIgnoreCase("android")) {
			return new GCMServer();
		}  else if (platform.equalsIgnoreCase("windows7")) {
			return null;
		} else if (platform.equalsIgnoreCase("raw-xmpp")) {
			//Configuration of Openfire server needed first
			return null;
		}
		
		return null;
	}


}
