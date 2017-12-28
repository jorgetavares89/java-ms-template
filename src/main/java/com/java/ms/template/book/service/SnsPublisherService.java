package com.java.ms.template.book.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.ms.template.book.domain.BookCategory;
import com.java.ms.template.book.domain.BookCategoryRequest;
import com.java.ms.template.book.domain.TopicEvent;
import com.java.ms.template.book.domain.factory.BookCategoryFactory;

@Service
public class SnsPublisherService {

    private static final Logger LOG = LoggerFactory.getLogger(SnsPublisherService.class);

    private final AmazonSNS snsClient;
    private final Topic snsTopicCreated;
    private final Topic snsTopicUpdated;
    private final Topic snsTopicDeleted;
    private final BookCategoryFactory bookCategoryFactory;

    @Autowired
    public SnsPublisherService(AmazonSNS sns, 
    		@Qualifier("created") Topic snsTopicCreated, 
    		@Qualifier("updated") Topic snsTopicUpdated, 
    		@Qualifier("deleted") Topic snsTopicDeleted, 
    		BookCategoryFactory accessPolicyFactory) {
        this.snsClient = sns;
        this.snsTopicCreated = snsTopicCreated;
        this.snsTopicUpdated = snsTopicUpdated;
        this.snsTopicDeleted = snsTopicDeleted;
        this.bookCategoryFactory = accessPolicyFactory;
    }

    public void publish(BookCategoryRequest bookCategoryRequest, TopicEvent eventType) {
        try {
        	BookCategory bookCategory = bookCategoryFactory.create(bookCategoryRequest);
        	String topicArn = getTopicArn(eventType);
        	LOG.debug("Publishing a message on ["+topicArn+"] SNS topic...");
        	snsClient.publish(createPublishRequest(topicArn, bookCategory));
        	LOG.debug("Message published!");
        } catch (JsonProcessingException e) {
            LOG.error("Failed to publish the message to SNS topic", e);
        }
    	
    }

	private String getTopicArn(TopicEvent eventType) {
		String topicArn = null;
		switch (eventType) {
			case CREATED:
				topicArn = snsTopicCreated.getTopicArn();
				break;
			case UPDATED:
				topicArn = snsTopicUpdated.getTopicArn();
				break;
			case DELETED:
				topicArn = snsTopicDeleted.getTopicArn();
				break;
			default:
				break;
		}
		return topicArn;
	}

    private PublishRequest createPublishRequest(String topicArn, final BookCategory accessPolicy) throws JsonProcessingException {
        return new PublishRequest()
        .withTopicArn(topicArn)
        .withMessage(accessPolicy.toJson());
    }
}
