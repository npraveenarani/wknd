package com.praveena.wknd.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition
public @interface PraveenaConfiguration {
	
		@AttributeDefinition(name = "Praveena URL PATH", description = "This is a sample URL path")
		public String restAPIurl() default "https://gorest.co.in/public/v2/posts"; 
		
		@AttributeDefinition(name = "Praveena ID", description = "Praveena Access")
		public String ID() default "123456";
	
}
