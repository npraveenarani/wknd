package com.praveena.wknd.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.event.EventConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;


	@Component (service= EventHandler.class, immediate = true,
			property = { EventConstants.EVENT_TOPIC+"="+ ReplicationAction.EVENT_TOPIC ,
				
	}) 
			public class EventHandlerClass implements EventHandler{
			 private static final Logger log=LoggerFactory.getLogger(EventHandlerClass.class); 
			 @Override public void handleEvent(Event event) {
				 log.info("Inside the Handle Event Method.. Praveena");
				 String[] properties = event.getPropertyNames(); 
				 
			for (String property: properties) {
			log.info("Replicated Page - {} ",event.getProperty(property));
			}
			}
			}
	
	
