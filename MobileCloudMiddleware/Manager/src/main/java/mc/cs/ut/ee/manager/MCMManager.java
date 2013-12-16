package mc.cs.ut.ee.manager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mc.cs.ut.ee.utilities.DefaultConfigurations;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@SuppressWarnings("serial")
public class MCMManager extends HttpServlet{
	String mostrar;
		
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MobileTask requestedTask = null;        
        
		if (ServletFileUpload.isMultipartContent(request)) {
		   requestedTask = processMultipartTaskRequest(request);
			
		} else {
			requestedTask = processTask(request);
		}
		//requestedTask.setTaskID("prueba");
		
        if (requestedTask!=null){
        	Thread mcmAdapter= new Thread(requestedTask);
            mcmAdapter.start();
            printResponse(request, response, true);
	
        }else{
        	printResponse(request, response, false);
        }
                
    }
	
	
	MobileTask processTask(HttpServletRequest request){
		//Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		Gson gson = new Gson();
		MobileTask task = null;
		String taskClassName = request.getParameter("taskClass").trim();
		
		if (taskClassName!=null){
			 try {
				    Class taskClass = Class.forName(DefaultConfigurations.CLASS_PATH_MCM_SERVICES + taskClassName);
				    JSONObject mobileTaskDescriptor = requestParamsToJSON(request);
				    mostrar = mobileTaskDescriptor.toJSONString();
		            task = (MobileTask) gson.fromJson(mobileTaskDescriptor.toJSONString(), taskClass);
		            
		        } catch (Exception ex) {} 	
		}
		
		return task;
	}
	
	MobileTask processMultipartTaskRequest(HttpServletRequest request) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<File> files = new ArrayList<File>();
        MobileTask task = null;

        List items;
        try {
			items = upload.parseRequest(request);
            Iterator it = items.iterator();
            String taskClass = null;
            JSONObject taskDescriptionJson=new JSONObject();


            while (it.hasNext()) {
                FileItem item = (FileItem) it.next();
                if (!item.isFormField()) {
                    // TODO: add file in multipart to list of files
                    files.add(storeAndReturnFileItemContents(item));
                } else {
                	taskDescriptionJson.put(item.getFieldName(), item.getString());
                    if (item.getFieldName().equals("taskClass")) {
                        taskClass = item.getString();
                    }
                }
            }
            mostrar = taskDescriptionJson.toJSONString();
			task = taskWithClassAndDescription(taskClass, taskDescriptionJson.toJSONString());
        } catch (Exception ex) {
            //Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (task!=null){
        	task.data = files;	
        }
	
        return task;
    }
	
	private MobileTask taskWithClassAndDescription(String taskClassName, String taskDescriptionJson) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Class taskClass = null;

        try {
            taskClass = Class.forName(DefaultConfigurations.CLASS_PATH_MCM_SERVICES + taskClassName);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (MobileTask) gson.fromJson(taskDescriptionJson, taskClass);
    }
	
	 private File storeAndReturnFileItemContents(FileItem item) {
	        File path = new File(DefaultConfigurations.TEMP_FOLDER_FOR_IMAGES);
	        String fileName = item.getName();
	        if (!path.exists()) {
	            boolean status = path.mkdirs();
	        }
	        File uploadedFile = new File(path + "/" + fileName);
	        try {
	            item.write(uploadedFile);
	        } catch (Exception ex) {
	            //Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return uploadedFile;
	    }

	/*
	 * Convert REST (parameters) to JSON
	 */
	public JSONObject requestParamsToJSON(HttpServletRequest req) {
		  JSONObject jsonObj = new JSONObject();
		  Map<String,String[]> params = req.getParameterMap();
		  for (Map.Entry<String,String[]> entry : params.entrySet()) {
		    String v[] = entry.getValue();
		    Object o = (v.length == 1) ? v[0] : v;
		    jsonObj.put(entry.getKey(), o);
		  }
		  return jsonObj;
	}
	
	/*
	 * HTML Writter
	 */
	private void printResponse(HttpServletRequest request, HttpServletResponse response, Boolean success) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mobile Cloud Middleware Manager</title>");
            out.println("</head>");
            out.println("<body>");
            
            if (success == true){
            	out.println("<h1>MCMManager at " + request.getContextPath() + "</h1>");
            	out.println("Mobile task is being processed..." + mostrar);
            	//out.println("The result of the task is..." + TransactionalSpace.getInstance().getResultForTaskID("prueba"));
            	
            	
            }else{
            	out.println("<h1>MCMManager at " + request.getContextPath() + "</h1>");
            	out.println("Mobile task cannot be processed... ");
            }
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
	}
	
	
	/*
	 * REST
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		processRequest(req,resp);
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		processRequest(req,resp);
	}
	
}
