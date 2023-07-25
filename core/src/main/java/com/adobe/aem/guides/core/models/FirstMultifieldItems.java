package com.adobe.aem.guides.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FirstMultifieldItems {

    @Inject
    private String continentName;

    @Inject
    private List<SecondMultifieldItems> secondMultifieldItems;

    public String getContinentName() {
        return continentName;
    }

    public List<SecondMultifieldItems> getSecondMultifieldItems() {
        return secondMultifieldItems;
    }

}
