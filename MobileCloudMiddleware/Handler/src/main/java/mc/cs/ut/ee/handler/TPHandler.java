package mc.cs.ut.ee.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TPHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected String defineRequestType(HttpServletRequest request, HttpServletResponse response){
		
		String taskClass = request.getParameter("taskClass");
		
		// TODO: check Class for resource type
		
		if (taskClass!=null){
			return "single";
		}else{
			return null;
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestType = defineRequestType(request, response);
		
		
		if (requestType.equals("single")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/taskmanager");
			
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}

		}else{
			if (requestType.equals("compose")){
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/taskcomposition");
				
				try {
					dispatcher.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				}	
			}
		}
		
		
	}
   
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
