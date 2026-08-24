package com.praveena.wknd.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

@Component(service=	Servlet.class, immediate=true)
@SlingServletPaths(value = {"/bin/wknd/service/praveena"})
public class SlingnodeAPIDemo extends SlingAllMethodsServlet{
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException,IOException{
		
		ResourceResolver resolver=request.getResourceResolver();
		Resource userResource= resolver.getResource("/content/users");
		
		if(userResource !=null) {
			Iterator<Resource> childUserList=userResource.listChildren();
			JsonArrayBuilder userJsonList=Json.createArrayBuilder();
			while(childUserList.hasNext()) {
				Resource childUserResource=(Resource) childUserList.next();
				ValueMap property=childUserResource.getValueMap();
				String firstName=property.get("FIRSTNAME", String.class);
				String lastName=property.get("LASTNAME", String.class);
				String email=property.get("EMAIL", String.class);
				String phoneNumber=property.get("CONTACTNUMBER", String.class);
				
				JsonObjectBuilder userJson=Json.createObjectBuilder();
				
				userJson.add("FIRSTNAME", firstName);
				userJson.add("LASTNAME", lastName);
				userJson.add("EMAIL", email);
				userJson.add("CONTACTNUMBER", phoneNumber);
				
				userJsonList.add(userJson);
				}
			response.getWriter().write(userJsonList.build().toString());
		}
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
		
		ResourceResolver resolver = request.getResourceResolver();
        Resource userResource = resolver.getResource("/content/users");  
        
        String userID = request.getParameter("userID");
        
        Map<String, Object> properties = new HashMap();
        properties.put("FIRSTNAME", request.getParameter("firstName"));
        properties.put("LASTNAME", request.getParameter("lastName"));
        properties.put("EMAIL", request.getParameter("email"));
        properties.put("CONTACTNUMBER", request.getParameter("phoneNumber"));
        
        resolver.create(userResource, userID, properties);
        resolver.commit();
		response.getWriter().write("User ID successfully Created " + userID);
	}
	
	@Override
	protected void doPut(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
		
        String userID = request.getParameter("userID");
		
		ResourceResolver resolver = request.getResourceResolver();
        Resource userResource = resolver.getResource("/content/users/" + userID);  
        
        if(userResource != null) {
        	
        	ModifiableValueMap mProp = userResource.adaptTo(ModifiableValueMap.class);
        	
        	String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			
			if(firstName != null) {
				
				mProp.put("firstName", firstName);
			}
            if(lastName != null) {
				
				mProp.put("lastName", lastName);
			}
            if(email != null) {
	
	            mProp.put("email", email);
                                  }
            if(phoneNumber != null) {
	
	            mProp.put("phoneNumber", phoneNumber);
                                   }
        	resolver.commit();
        }
        
		response.getWriter().write("UserID successfully Updated");
	} 
	@Override
	protected void doDelete(SlingHttpServletRequest request,SlingHttpServletResponse response)throws ServletException, IOException {
	
		String userID = request.getParameter("userID");
		
		ResourceResolver resolver = request.getResourceResolver();
        Resource userResource = resolver.getResource("/content/users/" + userID);  
        resolver.delete(userResource);
        resolver.commit();
        
		response.getWriter().write("UserID successfully Deleted " + userID);
	}
	
}