package com.adobe.aem.guides.core.beans;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "WKND Json API Service Configuration", description = "WKND API Service Configuration")
public @interface WKNDJsonAPIServiceBeans {

    @AttributeDefinition(name = "Endpoint URL", description = "Enter your JSON Endpoint URL to get response", type = AttributeType.STRING)
    String getEndpointURL() default "https://run.mocky.io/v3/4c78c904-00b6-49c4-8174-efd559e638eb";
}
