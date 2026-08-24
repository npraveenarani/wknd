package com.praveena.wknd.core.schedulers;

import java.util.Date;
import java.util.Iterator;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.praveena.wknd.core.services.SchedulerReference;

@Component(service=Runnable.class,immediate=true, 
property= {"scheduler.expression =*/30 * * ? * *"}
)
public class PraveenaSlingSchedulers implements Runnable{
	
	private static final Logger log=LoggerFactory.getLogger(PraveenaSlingSchedulers.class);
	
	@Reference
	SchedulerReference praveenaService;
	
	@Reference
	Replicator replicator;
	
	@Override
	public void run() {
		log.info("This is Sling Scheduler method");
		try(ResourceResolver resolver = praveenaService.getResourceResolver()) {
			PageManager pagemanager= resolver.adaptTo(PageManager.class);
			Page page=pagemanager.getPage("/content/wknd/us/en/praveena-article/*");
			Iterator<Page> childpages=page.listChildren();
			while(childpages.hasNext()) {
				Resource contentResource=page.getContentResource();
				ValueMap properties=contentResource.getValueMap();
				Date Expiry=new Date();
				if(Expiry!=null && Expiry.compareTo(Expiry)<0) {
					Session session=resolver.adaptTo(Session.class);
					replicator.replicate(session, ReplicationActionType.DEACTIVATE, page.getPath());
				}
			}
		}
		catch(ReplicationException e) {
			e.printStackTrace();
		}
	}
	

}
