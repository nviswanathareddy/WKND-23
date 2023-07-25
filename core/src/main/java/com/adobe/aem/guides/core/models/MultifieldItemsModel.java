package com.adobe.aem.guides.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultifieldItemsModel {

    @Inject
    private String institute;
    @Inject
    private int passingyear;

    public String getInstitute() {
        return institute;
    }
    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public int getPassingyear() {
        return passingyear;
    }
    public void setPassingyear(int passingyear) {
        this.passingyear = passingyear;
    }


}
