package com.java.ms.template.book.config.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.Topic;

@Configuration
@Profile("dev-local")
public class SnsConfigLocal extends AwsLocalGeneralConfig {

	private static final Logger LOG = LoggerFactory.getLogger(SnsConfigLocal.class);
	
    @Value("${amazon.aws.sns.endpoint}")
    private String amazonSnsEndpoint;
    @Value("${amazon.aws.sns.topic.created.name}")
    private String topicCreatedName;
    @Value("${amazon.aws.sns.topic.updated.name}")
    private String topicUpdatedName;
    @Value("${amazon.aws.sns.topic.deleted.name}")
    private String topicDeletedName;
    
    @Bean
    public AmazonSNS snsClient() {
    	LOG.info("Creating the SNS client...");
        return AmazonSNSClientBuilder.standard()
                .withEndpointConfiguration(getSNSEndpointConfiguration())
                .withCredentials(getAmazonAWSCredentials())
                .build();
    }
    
    @Bean(name="created")   
    public Topic snsTopicCreated() {
        return new Topic().withTopicArn(createTopic(topicCreatedName).getTopicArn());
    }
    
    @Bean(name="updated")
    public Topic snsTopicUpdated() {
        return new Topic().withTopicArn(createTopic(topicUpdatedName).getTopicArn());
    }
    
    @Bean(name="deleted")
    public Topic snsTopicDeleted() {
        return new Topic().withTopicArn(createTopic(topicDeletedName).getTopicArn());
    }

    private CreateTopicResult createTopic(String topicName) {
    	LOG.debug("Creating the Topic ["+topicName+"]");
        return snsClient().createTopic(new CreateTopicRequest(topicName));
    }
            
    private EndpointConfiguration getSNSEndpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(amazonSnsEndpoint, amazonRegion);
    }
    
}
