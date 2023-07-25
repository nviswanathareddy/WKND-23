package com.adobe.aem.guides.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultifieldIteratorModel {

    private final Logger logger = LoggerFactory.getLogger(MultifieldIteratorModel.class);

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

    @ChildResource (name = "multifield")
    Resource multifieldObject;

    List<MultifieldItemsModel> multifieldItemsList= new ArrayList<MultifieldItemsModel>();

    @PostConstruct
    public void init() throws RepositoryException{
        Node node = multifieldObject.adaptTo(Node.class);
        assert node != null;
        logger.info("Multifield Node Name: " + node.getName());
        NodeIterator nodeIterator = node.getNodes();
        while (nodeIterator.hasNext()){
            Node childNode = nodeIterator.nextNode();
            logger.info("Multifield Child Node Name: " + childNode.getName());
            logger.info("Multifield Child Name Path: " + childNode.getPath());
            MultifieldItemsModel multifieldItems = new MultifieldItemsModel();
            multifieldItems.setInstitute(childNode.getProperty("institute").getString().toUpperCase());
            multifieldItems.setPassingyear(Integer.parseInt(childNode.getProperty("passingyear").getString()));

            multifieldItemsList.add(multifieldItems);
        }
    }

    public List<MultifieldItemsModel> getMultifieldItemsList() {
        return multifieldItemsList;
    }
}
