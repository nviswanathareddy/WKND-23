package com.adobe.aem.guides.core.models;

import com.adobe.aem.guides.core.services.WKNDJsonAPIService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WKNDJsonAPIServiceModel {

    @Inject
    WKNDJsonAPIService wkndJsonAPIService;



}
