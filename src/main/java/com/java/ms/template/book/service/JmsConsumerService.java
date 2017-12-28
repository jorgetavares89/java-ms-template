package com.java.ms.template.book.service;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.ms.template.book.domain.TopicMessage;

@Service
public class JmsConsumerService {
    
    private BookCategoryService service;
    
    @Autowired
    public JmsConsumerService(BookCategoryService service){
        this.service = service;
    }

	private static final Logger LOG = LoggerFactory.getLogger(JmsConsumerService.class);

    @JmsListener(destination = "${amazon.aws.sqs.some.event.queue.name}")
    public void receiveResourceDeletedEvent(TextMessage requestJSON) throws JMSException, IOException {
        String jsonText = requestJSON.getText();
        TopicMessage topicMessage = new ObjectMapper().readValue(jsonText, TopicMessage.class);
        String message = topicMessage.getMessage();
        LOG.info("Message received from SQS Queue: " + message);
    }
}