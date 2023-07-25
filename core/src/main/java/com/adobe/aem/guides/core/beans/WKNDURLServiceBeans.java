package com.adobe.aem.guides.core.beans;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "WKND URL Service Configuration", description = "WKND URL Service Configuration")
public @interface WKNDURLServiceBeans {

    @AttributeDefinition(
            name = "Service ID",
            description = "WKND Service ID",
            type = AttributeType.INTEGER
    )
    int ServiceID();

    @AttributeDefinition(
            name = "Service Name",
            description = "WKND Service Name",
            type = AttributeType.STRING
    )
    String ServiceName() default "WKND Service Name";

    @AttributeDefinition(
            name = "Service URL",
            description = "WKND Service URL",
            type = AttributeType.STRING
    )
    String ServiceURL() default "localhost";

}
