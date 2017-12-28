package com.java.ms.template.book.config.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;

@Configuration
@Profile("dev-local")
public class SubscriberConfigLocal {
	
	private static final Logger LOG = LoggerFactory.getLogger(SubscriberConfigLocal.class);

	private AmazonSNS snsClient;
	private Topic snsTopicCreated;
	private Topic snsTopicUpdated;
	private Topic snsTopicDeleted;
    private AmazonSQS sqsClient;
    private String queueUrl;
    
    @Autowired
    public SubscriberConfigLocal(AmazonSNS snsClient, 
    		@Qualifier("created") Topic snsTopicCreated, 
    		@Qualifier("updated") Topic snsTopicUpdated, 
    		@Qualifier("deleted") Topic snsTopicDeleted, 
    		AmazonSQS sqsClient,
			String queueUrl) {
		this.snsClient = snsClient;
		this.snsTopicCreated = snsTopicCreated;
		this.snsTopicUpdated = snsTopicUpdated;
		this.snsTopicDeleted = snsTopicDeleted;
		this.sqsClient = sqsClient; 
		this.queueUrl = queueUrl;
	}

	@Bean
    public String subscribeQueueOnTopic () {
    	String subscriptionsId = null;
    	try {
    		 subscriptionsId = Topics.subscribeQueue(snsClient, sqsClient, snsTopicCreated.getTopicArn(), queueUrl);
    		 //subscriptionsId = Topics.subscribeQueue(snsClient, sqsClient, snsTopicUpdated.getTopicArn(), queueUrl);
    		 //subscriptionsId = Topics.subscribeQueue(snsClient, sqsClient, snsTopicDeleted.getTopicArn(), queueUrl);
    		LOG.debug("Subscription ID: "+ subscriptionsId);
		} catch (Exception e) {
			LOG.error("Fail to run the subscrition configuration.", e);
		}
    	return subscriptionsId;
    }

}
