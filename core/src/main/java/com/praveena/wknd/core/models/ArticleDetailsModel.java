package com.praveena.wknd.core.models;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables= {Resource.class , SlingHttpServletRequest.class},
resourceType= "/apps/wknd/components/praveena-Article",
defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
@Exporter(extensions ="json" ,name="jackson" )
public class ArticleDetailsModel {
	
	//slingmodelAnnotations
	@ValueMapValue
	public String articleText;
	
	@ValueMapValue
	public String articleTextDescr;
	
	@ValueMapValue
	public String articleimg;
	
	@ValueMapValue
	public Date articleExpiryDate;
	
    //GetMethod
	public String getarticleText() {
		return articleText;
	}

	public String getarticleTextDescr() {
		return articleTextDescr;
	}

	public String getarticleimg() {
		return articleimg;
	}

	public Date getarticleExpiryDate() {
		return articleExpiryDate;
	}
	
	private boolean articleExpired=false;
	
	public boolean isArticleExpired() {
		return articleExpired;
	}
	
	public List<RelatedArticleImpModel> getMultifield() {
		return multifield;
	}

	@ChildResource
	List<RelatedArticleImpModel> multifield;
	
	@PostConstruct
	//method
	public void init() {
		Date today=new Date();
		if(articleExpiryDate!=null && articleExpiryDate.compareTo(today)<0) {
			articleExpired=true;
		}
	}		
}
