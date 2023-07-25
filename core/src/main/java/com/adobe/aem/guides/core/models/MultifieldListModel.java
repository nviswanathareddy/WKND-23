package com.adobe.aem.guides.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultifieldListModel {

    @Inject
    private String name;

    @Inject
    private String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Inject
    private List<MultifieldItemsModel> multifield;

    public List<MultifieldItemsModel> getMultifield() {
        return multifield;
    }
}
