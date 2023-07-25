package com.adobe.aem.guides.core.services.impl;

import com.adobe.aem.guides.core.services.WKNDAdditionService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WKNDAdditionService.class, immediate = true)
@Designate(ocd = WKNDAdditionServiceImpl.Config.class)
public class WKNDAdditionServiceImpl implements WKNDAdditionService {

    private final Logger logger = LoggerFactory.getLogger(WKNDAdditionServiceImpl.class);

    private int firstValue;
    private int secondValue;

    @ObjectClassDefinition(name = "WKND Addition Service Configuration")
    public @interface Config {

        @AttributeDefinition(name = "First Value", description = "Enter first value to add in Addition Service")
        int firstValue() default 10;

        @AttributeDefinition(name = "Second Value", description = "Enter second value to add in Addition Service")
        int secondValue() default 15;

    }

    @Activate
    protected void activate(final Config config) {
        firstValue = config.firstValue();
        secondValue = config.secondValue();
        logger.info("Values: " + "First Value: " + firstValue + " | " + "Second Value: " + secondValue);
    }

    @Override
    public int additionService() {
        return firstValue + secondValue;
    }
}

