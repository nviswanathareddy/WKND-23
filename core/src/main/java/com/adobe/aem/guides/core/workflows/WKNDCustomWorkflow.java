package com.adobe.aem.guides.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

@Component(property = {
        Constants.SERVICE_DESCRIPTION + "=Custom Workflow Process Step for WKND Project",
        Constants.SERVICE_VENDOR + "=Adobe Systems",
        "process.label" + "=WKND Custom Workflow Process"
})
public class WKNDCustomWorkflow implements WorkflowProcess {

    private final Logger logger = LoggerFactory.getLogger(WKNDCustomWorkflow.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        logger.info("Custom Workflow Process for WKND Project");

        String payloadPath = workItem.getWorkflowData().getPayload().toString();
        logger.info("Custom Workflow Path" + payloadPath);
        ResourceResolver resourceResolver = workflowSession.adaptTo(ResourceResolver.class);
        Resource resource = resourceResolver.getResource(payloadPath);
        Page page = resource.adaptTo(Page.class);
        Node node = page.getContentResource().adaptTo(Node.class);

        try {
            node.setProperty("testValue", "Test Value");
            node.getSession().save();
        }catch (RepositoryException repositoryException){
            repositoryException.printStackTrace();
        }
    }
}
