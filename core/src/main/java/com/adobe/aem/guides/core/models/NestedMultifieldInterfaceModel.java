package com.adobe.aem.guides.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface NestedMultifieldInterfaceModel {

    @Inject
    List<Continents> getContinentMultifield();

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    interface Continents{
        @Inject
        String getContinentName();

        @Inject
        List<Countries> getCountryMultifield();

    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    interface Countries{
        @Inject
        String getCountryName();
        @Inject
        String getCountryCode();
    }
}
