package mc.cs.ut.ee.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mc.cs.ut.ee.data.MobileTaskKeeper;


public class MobileTaskMonitor implements MobileTaskKeeper {
	private static MobileTaskKeeper instance;
	private Map<String, MobileTaskStatus> statusData;
	
	private MobileTaskMonitor() {
		statusData = new HashMap<String, MobileTaskStatus>();
	}
	public static MobileTaskKeeper getInstance() {
		if (instance == null) {
			instance = new MobileTaskMonitor();
		}
		return instance;
	}
	
	public MobileTaskStatus getStatus(String taskID) {
		MobileTaskStatus requestedStatus = statusData.get(taskID);
		if (requestedStatus == null) {
			requestedStatus = new MobileTaskStatus(taskID);
			statusData.put(taskID, requestedStatus);
		}
		return requestedStatus;
	}
	

	public List<MobileTaskStatus> getAllTasks() {
		List<MobileTaskStatus> statusList = new ArrayList<MobileTaskStatus>(statusData.values());
		return statusList;
		
	}

}
