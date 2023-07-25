package com.adobe.aem.guides.core.services.impl;

import com.adobe.aem.guides.core.services.WKNDDefaultOSGIConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.*;

@Component(service = WKNDDefaultOSGIConfig.class, immediate = true)
@Designate(ocd = WKNDDefaultOSGIConfigImpl.ServiceConfig.class)
public class WKNDDefaultOSGIConfigImpl implements WKNDDefaultOSGIConfig{

    private String ServiceName;
    private int ServiceCount;
    private boolean ServiceLiveData;
    private String[] ServiceCountries;
    private String ServiceRunMode;

    @Activate
    protected void activate(ServiceConfig serviceConfig){
        ServiceName = serviceConfig.getServiceName();
        ServiceCount = serviceConfig.getServiceCount();
        ServiceLiveData = serviceConfig.getServiceLiveData();
        ServiceCountries = serviceConfig.getServiceCountries();
        ServiceRunMode = serviceConfig.getServiceRunMode();
    }

    @ObjectClassDefinition(
            name = "WKND Default OSGI Service Configuration",
            description = "WKND Default OSGI Service Configuration for Default Service"
    )

    public @interface ServiceConfig{

        @AttributeDefinition(
                name = "Service Name",
                description = "WKND Default Service Name",
                type = AttributeType.STRING
        )
        String getServiceName() default "WKND Default Service";

        @AttributeDefinition(
                name = "Service Count",
                description = "WKND Service Count",
                type = AttributeType.INTEGER
        )
        int getServiceCount() default 5;

        @AttributeDefinition(
                name = "Service Live Data",
                description = "WKND Service Live Data",
                type = AttributeType.BOOLEAN
        )
        boolean getServiceLiveData() default false;

        @AttributeDefinition(
                name = "Service Countries List",
                description = "WKND Service Countries List",
                type = AttributeType.STRING
        )
        String[] getServiceCountries() default {"in", "fr"};

        @AttributeDefinition(
                name = "Service Run Mode",
                description = "WKND Service Run Modes",
                options = {
                        @Option(label = "Author", value = "author"),
                        @Option(label = "Publish", value = "publish"),
                        @Option(label = "Both", value = "both")
                },
                type = AttributeType.STRING
        )
        String getServiceRunMode() default "both";

    }

    @Override
    public String getServiceName() {
        return ServiceName;
    }

    @Override
    public int getServiceCount() {
        return ServiceCount;
    }

    @Override
    public boolean getServiceLiveData() {
        return ServiceLiveData;
    }

    @Override
    public String[] getServiceCountries() {
        return ServiceCountries;
    }

    @Override
    public String getServiceRunMode() {
        return ServiceRunMode;
    }
}
