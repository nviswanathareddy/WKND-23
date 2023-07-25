package com.adobe.aem.guides.core.services.impl;


import com.adobe.aem.guides.core.services.MyOSGIService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component(service = MyOSGIService.class)
/*
* @ServiceRanking() is used to prioritize the Service
* Higher the value Higher the priority
* */
@ServiceRanking(1000)
public class MyOSGIServiceOne implements MyOSGIService{

    @Override
    public String getData() {
        String result = "ONE: MyOSGIService One is implemented" ;
        return result;
    }
}
