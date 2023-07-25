package com.adobe.aem.guides.core.models.impl;

import com.adobe.aem.guides.core.models.WKNDDefaultOSGIConfigModel;
import com.adobe.aem.guides.core.services.WKNDDefaultOSGIConfig;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class, adapters = WKNDDefaultOSGIConfigModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WKNDDefaultOSGIConfigModelImpl implements WKNDDefaultOSGIConfigModel {

    @OSGiService
    WKNDDefaultOSGIConfig wkndDefaultOSGIConfig;

    @Override
    public String getServiceName() {
        return wkndDefaultOSGIConfig.getServiceName();
    }

    @Override
    public int getServiceCount() {
        return wkndDefaultOSGIConfig.getServiceCount();
    }

    @Override
    public boolean getServiceLiveData() {
        return wkndDefaultOSGIConfig.getServiceLiveData();
    }

    @Override
    public String[] getServiceCountries() {
        return wkndDefaultOSGIConfig.getServiceCountries();
    }

    @Override
    public String getServiceRunMode() {
        return wkndDefaultOSGIConfig.getServiceRunMode();
    }
}
