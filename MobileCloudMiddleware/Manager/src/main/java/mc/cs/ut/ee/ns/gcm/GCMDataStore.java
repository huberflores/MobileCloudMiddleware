package mc.cs.ut.ee.ns.gcm;

import java.util.List;
import java.util.ArrayList;

/*
 * Temporal mobile storage
*/

public final class GCMDataStore {
	  private static final List<String> regIds = new ArrayList<String>();

	  private GCMDataStore() {
		    throw new UnsupportedOperationException();
	  }
	  
	  /*
	   * Register a device
	   */
	  public static void register(String regId) {
		    regIds.add(regId);
      }

	  /*
	   * Remove a device
	   */
	  public static void unregister(String regId) {
		    regIds.remove(regId);
	  }

	  /*
	   * Get devices registered	  
	   */
	  public static List<String> getDevices() {
		    return regIds;
	  }


}
