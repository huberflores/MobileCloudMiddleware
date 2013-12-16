package mc.cs.ut.ee.ns.gcm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GCMUnregister extends GCMBase {

	private static final String PARAMETER_REG_ID = "regId";

	  @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException {
	    String regId = getParameter(req, PARAMETER_REG_ID);
	    GCMDataStore.unregister(regId);
	    setSuccess(resp);
	  }
}
