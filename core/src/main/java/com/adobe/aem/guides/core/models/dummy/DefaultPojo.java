package com.adobe.aem.guides.core.models.dummy;

import com.adobe.cq.sightly.WCMUsePojo;

import javax.jcr.RepositoryException;

public class DefaultPojo extends WCMUsePojo {

    private String name;

    @Override
    public void activate() throws
            RepositoryException{
        name = getProperties().get("name", String.class).toUpperCase();
    }

    public String getName(){
        return name;
    }
}
