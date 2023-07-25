package com.adobe.aem.guides.core.models.dummy;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DefaultModel {

    @Inject
    private String name;

    public String getName(){
        return name;
    }

}
