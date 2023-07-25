package com.adobe.aem.guides.core.services.impl;

import com.adobe.aem.guides.core.services.MyOSGIService;
import com.adobe.aem.guides.core.services.ServiceCall;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class, adapters = ServiceCall.class)
public class ServiceCallImpl implements ServiceCall {

    /*
    * @OSGiService(filter = "(component.name=com.adobe.aem.guides.core.services.impl.MyOSGIServiceTwo)")
    * is used to filter the service to be implemented
    * */
    @OSGiService
    MyOSGIService myOSGIService;

    @Override
    public String getResponse(){
        return myOSGIService.getData();
    }

}
