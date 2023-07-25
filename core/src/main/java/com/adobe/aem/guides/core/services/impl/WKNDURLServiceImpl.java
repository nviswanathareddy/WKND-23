package com.adobe.aem.guides.core.services.impl;

import com.adobe.aem.guides.core.beans.WKNDURLServiceBeans;
import com.adobe.aem.guides.core.services.WKNDURLService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = WKNDURLService.class, immediate = true)
@Designate(ocd = WKNDURLServiceBeans.class)
public class WKNDURLServiceImpl implements WKNDURLService {

    private int ServiceID;
    private String ServiceName;
    private String ServiceURL;


    @Activate
    protected void activate(WKNDURLServiceBeans wkndurlServiceBeans){
        ServiceID = wkndurlServiceBeans.ServiceID();
        ServiceName = wkndurlServiceBeans.ServiceName();
        ServiceURL = wkndurlServiceBeans.ServiceURL();

    }
    @Override
    public int getServiceID() {
        return ServiceID;
    }

    @Override
    public String getServiceName() {
        return ServiceName;
    }

    @Override
    public String getServiceURL() {
        return ServiceURL;
    }
}
