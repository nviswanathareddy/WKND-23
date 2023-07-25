package com.adobe.aem.guides.core.models.impl;

import com.adobe.aem.guides.core.models.WKNDURLServiceModel;
import com.adobe.aem.guides.core.services.WKNDURLService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;


@Model(adaptables = SlingHttpServletRequest.class, adapters = WKNDURLServiceModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WKNDURLServiceModelImpl implements WKNDURLServiceModel {

    @OSGiService
    WKNDURLService wkndurlService;
    @Override
    public int getServiceID()
    {
        return wkndurlService.getServiceID();
    }

    @Override
    public String getServiceName() {
        return wkndurlService.getServiceName();
    }

    @Override
    public String getServiceURL() {
        return wkndurlService.getServiceURL();
    }
}
