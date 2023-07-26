package com.adobe.aem.guides.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class,
        immediate = true,
        property = {
                "event.topics=org/apache/sling/api/resource/Resource/ADDED",
                "service.description=Simple DAM Asset Upload Event Listener",
                "service.vendor=Your Company Name"
        })
public class DamAssetUploadListener implements EventHandler {
    private static final Logger log = LoggerFactory.getLogger(DamAssetUploadListener.class);

    @Override
    public void handleEvent(Event event) {
        String resourceType = (String) event.getProperty("resourceType");
        String path = (String) event.getProperty("path");

        if (resourceType != null && resourceType.equals("dam:Asset")) {
            log.info("New asset uploaded to DAM: {}", path);
            // You can add your custom logic here, such as processing the uploaded asset.
        }
    }
}
