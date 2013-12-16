package mc.cs.ut.ee.manager;

import java.io.File;

import java.util.List;

import mc.cs.ut.ee.data.TransactionalSpace;
import mc.cs.ut.ee.manager.MobileTaskStatus.Status;
import mc.cs.ut.ee.ns.core.NotificationManager;

import com.google.gson.annotations.Expose;



@SuppressWarnings("serial")
public abstract class MobileTask implements Runnable {
	
   List<File> data;
   @Expose
   private List<String> parameters; 
   @Expose
   private String ownerDevice;
   @Expose
   private String deviceID;
   @Expose
   private String taskID;
   @Expose
   private String description;
   
   private String results;
   
   public void run() {
	    MobileTaskMonitor.getInstance().getStatus(taskID).setStatus(Status.RUNNING);
			performTask();
	   		storeResultIntoTransactionalSpace();	
	   		pushResultToMobile();
	    MobileTaskMonitor.getInstance().getStatus(taskID).setStatus(Status.COMPLETED);
   }

   
   /*
    * Abstract methods
    */
   
   abstract public void performTask();
   
   
   /*
    * Methods
    */
   
   
   public void storeResultIntoTransactionalSpace() {
		TransactionalSpace.getInstance().addResults(taskID, results);
   }

   
   public void pushResultToMobile() {
	   NotificationManager.getNotificationProvider("android")
	    .withPayload(getResults())
		.withMessage(getMessage())
		.sendMessage();
   }
   
   
   
   /*
    * Getters and setters 
    */
   
      
   public List<File> getData(){
	   return this.data;
   }
     
   public List<String> getParameters() {
       return parameters;
   }

   public void setParameters(List<String> parameters) {
       this.parameters = parameters;
   }

   public String getOwnerDevice() {
       return ownerDevice;
   }

   public void setOwnerDevice(String ownerDevice) {
       this.ownerDevice = ownerDevice;
   }

   public String getDeviceID() {
       return deviceID;
   }

   public void setDeviceID(String deviceID) {
       this.deviceID = deviceID;
   }

   public String getTaskID() {
       return taskID;
   }

   public void setTaskID(String taskID) {
       this.taskID = taskID;
   }

   public String getDescription() {
       return description;
   }

   public void setDescription(String description) {
       this.description = description;
   }

   public String getResults() {
       return results == null ? "" : results;
   }

   public void setResults(String results) {
       this.results = results;
   }

   private String getMessage() {
	   return "MCM task complete";
   }
   
}
