package com.java.ms.template.book.config.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;

@Configuration
@Profile("!dev-local")
public class SnsConfigCloud extends AwsGeneralConfig {

    @Value("${amazon.aws.sns.topic.created.arn}")
    private String topicArnCreated;
    
    @Value("${amazon.aws.sns.topic.updated.arn}")
    private String topicArnUpdated;
    
    @Value("${amazon.aws.sns.topic.deleted.arn}")
    private String topicArnDeleted;

    @Bean
    public AmazonSNS snsClient() {
        return AmazonSNSClientBuilder.standard()
                .withRegion(amazonRegion)
                .withCredentials(getAmazonAWSCredentials())
                .build();
    }

    @Bean(name="created")
    public Topic snsTopicCreated(AmazonSNS amazonSNS) {
        return new Topic().withTopicArn(topicArnCreated);
    }
    
    @Bean(name="updated")
    public Topic snsTopicUpdated(AmazonSNS amazonSNS) {
        return new Topic().withTopicArn(topicArnUpdated);
    }
    
    @Bean(name="deleted")
    public Topic snsTopicDeleted(AmazonSNS amazonSNS) {
        return new Topic().withTopicArn(topicArnDeleted);
    }

}
