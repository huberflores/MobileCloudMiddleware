package mc.cs.ut.ee.data;

import java.util.HashMap;
import java.util.Map;

public class TransactionalSpace {
	private static TransactionalSpace instance = new TransactionalSpace();
	private Map <String, String> results;
	
	private TransactionalSpace() {
		results = new HashMap<String, String>();
	}
	
	public void addResults(String taskID, String result) {
		
		results.put(taskID, result);
	}
	
	public String getResultForTaskID(String taskID) {
		return results.containsKey(taskID) ? results.get(taskID) : "Result not found";
	}
	
	public static TransactionalSpace getInstance() {
		if (instance == null) {
			instance = new TransactionalSpace();
		}
		return instance;
	}

}
