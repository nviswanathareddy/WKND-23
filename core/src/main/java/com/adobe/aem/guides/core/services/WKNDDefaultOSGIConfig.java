package com.adobe.aem.guides.core.services;

public interface WKNDDefaultOSGIConfig {

    public String getServiceName();
    public int getServiceCount();
    public boolean getServiceLiveData();
    public String[] getServiceCountries();
    public String getServiceRunMode();

}
