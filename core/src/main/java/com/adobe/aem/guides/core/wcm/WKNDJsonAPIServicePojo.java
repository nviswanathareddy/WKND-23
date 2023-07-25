package com.adobe.aem.guides.core.wcm;

import com.adobe.aem.guides.core.services.WKNDJsonAPIService;
import com.adobe.cq.sightly.WCMUsePojo;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;


import java.util.HashMap;
import java.util.Map;

public class WKNDJsonAPIServicePojo extends WCMUsePojo {

    private Map<Integer, Object> jsonResponseMap = new HashMap<Integer, Object>();

    @Override
    public void activate() throws Exception {
        WKNDJsonAPIService wkndJsonAPIService = getSlingScriptHelper().getService(WKNDJsonAPIService.class);

        Gson gson = new Gson();
        TypeToken<Map<Integer, Object>> typeToken = new TypeToken<Map<Integer, Object>>() {
        };
        jsonResponseMap = gson.fromJson(wkndJsonAPIService.getJsonApiURLData(),typeToken.getType());



    }
}
