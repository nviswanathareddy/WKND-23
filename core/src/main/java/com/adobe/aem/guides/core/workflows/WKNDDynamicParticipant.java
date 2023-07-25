package com.adobe.aem.guides.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;

import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ParticipantStepChooser.class,
        property = {"chooser.label=" + "WKND Dynamic Participant - workflow-user"
        }
)
public class WKNDDynamicParticipant implements ParticipantStepChooser{

        private final Logger logger = LoggerFactory.getLogger(WKNDDynamicParticipant.class);
        @Override
        public String getParticipant(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
                logger.info("Entering Dynamic Participant Workflow Process Step");
                Workflow workflow = workItem.getWorkflow();
                return "workflow-user";
        }
}
