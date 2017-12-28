package com.java.ms.template.book.config.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Configuration
@Profile("!dev-local")
public class SqsConfigCloud extends AwsGeneralConfig  {
	
	private static final Logger LOG = LoggerFactory.getLogger(SqsConfigCloud.class);
    
    @Bean
    public AmazonSQS sqsClient() {
    	LOG.info("Creating the SQS client");
		return AmazonSQSClientBuilder.standard()
				.withRegion(amazonRegion)
			    .withCredentials(getAmazonAWSCredentials())
                .build();
    }

}
