package mc.cs.ut.ee.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MobileTaskStatus {
    public enum Status {
        NEW,
        WAITING,
        RUNNING,
        COMPLETED,
		UNKNOWN,
        ERROR
    }
    String taskID;
    String description;
    Status status;
    
    public MobileTaskStatus(String taskID, String description, Status status) {
        this.taskID = taskID;
        this.description = description;
        this.status = status;
    }
	public MobileTaskStatus(String taskID) {
		this.description = "No description";
		this.taskID = taskID;
		this.status = Status.UNKNOWN;
	}
    
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status getStatus(){
		return this.status;
	}
	
    String toJson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}