package com.praveena.wknd.core.servlets;

	import java.io.IOException;

	import javax.servlet.Servlet;
	import javax.servlet.ServletException;

	import org.apache.sling.api.SlingHttpServletRequest;
	import org.apache.sling.api.SlingHttpServletResponse;
	import org.apache.sling.api.servlets.SlingAllMethodsServlet;
	import org.apache.sling.servlets.annotations.SlingServletPaths;
	import org.osgi.service.component.annotations.Component;

	@Component(service = Servlet.class, immediate = true)
	//@SlingServletResourceTypes(resourceTypes = { "/bin/wknd/servlet/praveena" })
	@SlingServletPaths(value = {"/bin/wknd/services/praveena/sunitha"})

	   public class DemoServlets extends SlingAllMethodsServlet{
		@Override
		protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response)
				throws ServletException, IOException {
			response.getWriter().write("Response from Resource DemoServlet -->GET");
		}

		@Override
		protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response)
				throws ServletException, IOException {
			response.getWriter().write("Response from Resource DemoServlet -->POST");
		}
		
		@Override
		protected void doPut(SlingHttpServletRequest request,SlingHttpServletResponse response)
				throws ServletException, IOException {
			response.getWriter().write("Response from Resource DemoServlet -->PUT");
		}

		@Override
		protected void doDelete(SlingHttpServletRequest request,SlingHttpServletResponse response)
				throws ServletException, IOException {
			response.getWriter().write("Response from Resource DemoServlet -->DELETE");
		}
	}
	