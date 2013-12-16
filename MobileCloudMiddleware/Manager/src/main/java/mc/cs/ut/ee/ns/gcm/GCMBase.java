package mc.cs.ut.ee.ns.gcm;

//import java.util.Enumeration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GCMBase extends HttpServlet {
	
	static final boolean DEBUG = true;
	

	protected String getParameter(HttpServletRequest req, String parameter)
		      throws ServletException {
		    String value = req.getParameter(parameter);
		    if (value == null || value.trim().isEmpty()) {
		      /*if (DEBUG) {
		        StringBuilder parameters = new StringBuilder();
		        @SuppressWarnings("unchecked")
		        Enumeration<String> names = req.getParameterNames();
		        while (names.hasMoreElements()) {
		          String name = names.nextElement();
		          String param = req.getParameter(name);
		          parameters.append(name).append("=").append(param).append("\n");
		        }
		        
		      }*/
		      throw new ServletException("Parameter " + parameter + " not found");
		    }
		    return value.trim();
		  }

		  protected String getParameter(HttpServletRequest req, String parameter,
		      String defaultValue) {
		    String value = req.getParameter(parameter);
		    if (value == null || value.trim().isEmpty()) {
		      value = defaultValue;
		    }
		    return value.trim();
		  }

		  protected void setSuccess(HttpServletResponse resp) {
		    setSuccess(resp, 0);
		  }

		  protected void setSuccess(HttpServletResponse resp, int size) {
		    resp.setStatus(HttpServletResponse.SC_OK);
		    resp.setContentType("text/plain");
		    resp.setContentLength(size);
		  }
	
}
