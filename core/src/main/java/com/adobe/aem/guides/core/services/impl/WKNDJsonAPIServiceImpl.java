package com.adobe.aem.guides.core.services.impl;

import com.adobe.aem.guides.core.beans.WKNDJsonAPIServiceBeans;
import com.adobe.aem.guides.core.services.WKNDJsonAPIService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WKNDJsonAPIService.class, immediate = true)
@Designate(ocd = WKNDJsonAPIServiceBeans.class)
public class WKNDJsonAPIServiceImpl implements WKNDJsonAPIService {

    private final Logger logger = LoggerFactory.getLogger(WKNDJsonAPIServiceImpl.class);

    private String endpointURLData;

    @Activate
    public void activate(WKNDJsonAPIServiceBeans wkndJsonAPIServiceBeans) {
        endpointURLData = wkndJsonAPIServiceBeans.getEndpointURL();
        logger.info("Endpoint URL Data: " + endpointURLData);
    }

    private String getJsonResponse(String endpointURL) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(endpointURL);
            request.addHeader("Accept", "application/json");

            try (CloseableHttpResponse response = client.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new RuntimeException("Failed! HTTP Error Code: " + statusCode);
                }

                String jsonResponse = EntityUtils.toString(response.getEntity());
                logger.info("Endpoint JSON Response: " + jsonResponse);
                return jsonResponse;
            }
        } catch (Exception exception) {
            logger.error("Error occurred while making the API request", exception);
            return null;
        }
    }

    @Override
    public String getJsonApiURLData() {
        return getJsonResponse(endpointURLData);
    }
}
