package com.praveena.wknd.core.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service=EventHandlerUtileServiceReference.class)
public class EventHandlerUtileServiceReference {

	@Reference
	ResourceResolverFactory factoryEvent;
	
	public ResourceResolver getResourceReslover() {
		
		ResourceResolver resolver=null;
		try{
			Map<String,Object> props=new HashMap();
			props.put(ResourceResolverFactory.SUBSERVICE, "praveenaService");
			resolver=factoryEvent.getServiceResourceResolver(props);
		}
		catch(org.apache.sling.api.resource.LoginException e){
			e.printStackTrace();
			
		}
		return resolver;
		
	}
	
}
