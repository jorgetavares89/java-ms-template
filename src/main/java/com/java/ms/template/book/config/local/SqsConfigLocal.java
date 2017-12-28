package com.java.ms.template.book.config.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;

@Configuration
@Profile("dev-local")
public class SqsConfigLocal extends AwsLocalGeneralConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(SqsConfigLocal.class);

    @Value("${amazon.aws.sqs.endpoint}")
    private String amazonSqsEndpoint;
    @Value("${amazon.aws.sqs.some.event.queue.name}")
    private String queueName;
    
    @Bean
    public AmazonSQS sqsClient() {
    	LOG.debug("Creating the SQS Client...");
		AmazonSQS client = AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(getSqsEndpointConfiguration())
                .withCredentials(getAmazonAWSCredentials())
                .build();
		return client;
    }
        
    @Bean
    public String queueUrl() {
    	return createQueue().getQueueUrl();
    }

	private CreateQueueResult createQueue() {
    	LOG.debug("Creating the Queue ["+queueName+"]");
		return sqsClient().createQueue(new CreateQueueRequest(queueName));
	}
        
    private EndpointConfiguration getSqsEndpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(amazonSqsEndpoint, amazonRegion);
    }
}
