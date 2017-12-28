package com.java.ms.template.book.config;

import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ListQueuesResult;

public abstract class AbstractJmsConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(AbstractJmsConfig.class);
	
	public abstract SQSConnectionFactory createConnectionFactory();
	
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(createConnectionFactory());
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("3-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }
    
	public void listExistingQueues(AmazonSQS sqsClient) {
		ListQueuesResult queueResults = sqsClient.listQueues();
		queueResults.getQueueUrls().forEach(url -> LOG.debug("Queue ["+url+"] founded"));
	}

}
