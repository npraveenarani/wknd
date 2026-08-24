package com.praveena.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=Resource.class)
public class RelatedArticleImpModel {

	@ValueMapValue
	public String vijaytexter;
	
	@ValueMapValue
	public String textdescr;
	
	@ValueMapValue
	public String number;

	public String getVijaytexter() {
		return vijaytexter;
	}

	public String getTextdescr() {
		return textdescr;
	}

	public String getNumber() {
		return number;
	}	
}
