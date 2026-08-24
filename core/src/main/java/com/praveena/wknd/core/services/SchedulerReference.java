package com.praveena.wknd.core.services;

	import java.util.HashMap;
	import java.util.Map;

	import org.apache.sling.api.resource.ResourceResolver;
	import org.apache.sling.api.resource.ResourceResolverFactory;
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;

	@Component(service=SchedulerReference.class)
	public class SchedulerReference {

		@Reference
		ResourceResolverFactory factory;
		
		public ResourceResolver getResourceResolver() {
			
			ResourceResolver resolver=null;
			try {
				Map<String, Object> prop=new HashMap();
				prop.put(ResourceResolverFactory.SUBSERVICE, "praveenaService");
				resolver=factory.getServiceResourceResolver(prop);
				
			} 
			
			catch (org.apache.sling.api.resource.LoginException e) {
	          e.printStackTrace();
			}
			
			return resolver;
			
		}
	}

