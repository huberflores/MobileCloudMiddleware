package mc.cs.ut.ee.data;

import java.util.List;

import mc.cs.ut.ee.manager.MobileTaskStatus;



public interface MobileTaskKeeper {
	MobileTaskStatus getStatus(String taskID);
	List<MobileTaskStatus> getAllTasks();
}
