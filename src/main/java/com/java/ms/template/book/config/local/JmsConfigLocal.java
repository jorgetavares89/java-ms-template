package com.java.ms.template.book.config.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.java.ms.template.book.config.AbstractJmsConfig;

@Configuration
@Profile("dev-local")
@EnableJms
public class JmsConfigLocal extends AbstractJmsConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(JmsConfigLocal.class);
	
	private AmazonSQS sqsClient;
	
	@Autowired
	public JmsConfigLocal(AmazonSQS sqsClient) {
		LOG.info("Creating the JMS Config...");
		this.sqsClient = sqsClient;
	}

	@Override
	public SQSConnectionFactory createConnectionFactory() {
		LOG.info("Creating the JMS Connection Factory...");
		return new SQSConnectionFactory(new ProviderConfiguration(), sqsClient);
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		return super.jmsListenerContainerFactory();
	}

}
