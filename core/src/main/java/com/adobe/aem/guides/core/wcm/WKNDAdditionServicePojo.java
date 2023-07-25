package com.adobe.aem.guides.core.wcm;

import com.adobe.aem.guides.core.services.WKNDAdditionService;
import com.adobe.cq.sightly.WCMUsePojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WKNDAdditionServicePojo extends WCMUsePojo {

    private final Logger logger = LoggerFactory.getLogger(WKNDAdditionServicePojo.class);

    private int totalValue;


    @Override
    public void activate() throws Exception {
        WKNDAdditionService wkndAdditionService = getSlingScriptHelper().getService(WKNDAdditionService.class);
        totalValue = wkndAdditionService.additionService();
        logger.info("Total Value from POJO: " + totalValue);

    }

    public int getTotalValue() {
        return totalValue;
    }
}
