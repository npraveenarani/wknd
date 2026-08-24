package com.praveena.wknd.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;


@Component(service = EventHandler.class,
          property = {EventConstants.EVENT_TOPIC+"="+ReplicationAction.EVENT_TOPIC,
          EventConstants.EVENT_FILTER+"= (& (type = ACTIVATE)(paths = /content/wknd/us/en/praveena-article/*))"
}
		
		)
public class DemoSlingEventHandler implements EventHandler{

	private static final Logger LOG = LoggerFactory.getLogger(DemoSlingEventHandler.class);
	@Override
	public void handleEvent(Event event) {
		LOG.info("Inside the ArticleEventHandlerPraveena........");
		String[] Properties = event.getPropertyNames();
		for(String Property : Properties) {
			LOG.info("PropertyName -{} , PropertyValue - {}", Property,event.getProperty(Property));
		}
		
	}
}

//This one i didnt get Result



