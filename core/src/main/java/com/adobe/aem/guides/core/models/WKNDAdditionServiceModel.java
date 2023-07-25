package com.adobe.aem.guides.core.models;

import com.adobe.aem.guides.core.services.WKNDAdditionService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class WKNDAdditionServiceModel {

    private final Logger logger = LoggerFactory.getLogger(WKNDAdditionServiceModel.class);

    @Inject
    WKNDAdditionService wkndAdditionService;

    public int getTotalValue() {
        int totalValue = wkndAdditionService.additionService();
        logger.info("Total Value from Model: " + totalValue);
        return totalValue;
    }

}
