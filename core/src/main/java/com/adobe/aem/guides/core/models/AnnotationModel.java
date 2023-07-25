package com.adobe.aem.guides.core.models;


import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Iterator;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AnnotationModel {

    private final Logger logger = LoggerFactory.getLogger(AnnotationModel.class);

    @Inject
    @Via("resource")
    private String text;

    @ValueMapValue
    private String name;

    @Inject
    @Default(values = "Default Text Added")
    @Required
    private String defaultText;


    @Inject
    @Named("sling:resourceType")
    @Via("resource")
    private String resourceType;

    @SlingObject
    private ResourceResolver resourceResolver;


    @PostConstruct
    public void init(){
        Resource resource = resourceResolver.getResource("/content/wknd/us/services");
        if (resource != null) {
            logResourceDetails(resource);
        } else {
            logger.warn("Resource is null. Unable to log resource details.");
        }

    }

    private void logResourceDetails(Resource resource) {
        logger.info("Name: {}", resource.getName());
        logger.info("Path: {}", resource.getPath());
        logger.info("Resource Type: {}", resource.getResourceType());
        logger.info("Resource Super Type: {}", resource.getResourceSuperType());
    }


    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public String getResourceType() {
        return resourceType;
    }
}
