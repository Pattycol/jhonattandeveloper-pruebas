package pe.com.claro.caef.web.auth;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CaefWebURLInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> session = invocation.getInvocationContext().getSession();
		ServletActionContext.setContext(invocation.getInvocationContext());
//		System.out.println("TEST MESSAGE");
//		System.out.println(ServletActionContext.getServletContext().getAttribute("flagSession"));
//		int flag=(Integer) ServletActionContext.getServletContext().getAttribute("flagSession");
//		boolean indicador=(Boolean)ServletActionContext.getServletContext().getAttribute("indicador");
		
		//invocation.getInvocationContext(
		

	      if(session.isEmpty())
	      {
	          return "session"; // session is empty/expired
	      }
	      return invocation.invoke();
	}
	
	

}
