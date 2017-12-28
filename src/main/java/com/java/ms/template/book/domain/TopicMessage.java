package com.java.ms.template.book.domain;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TopicMessage {

    private String message;
    private String messageId;
    private String type;
    private String topicArn;
    private String timestamp;
    private String signatureVersion;
    private String signature;
    private String signingCertURL;
    private String unsubscribeURL;

    @JsonProperty
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @JsonProperty
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty
    public String getSignatureVersion() {
        return signatureVersion;
    }

    public void setSignatureVersion(String signatureVersion) {
        this.signatureVersion = signatureVersion;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @JsonProperty
    public String getSigningCertURL() {
        return signingCertURL;
    }

    public void setSigningCertURL(String signingCertURL) {
        this.signingCertURL = signingCertURL;
    }

    @JsonProperty
    public String getUnsubscribeURL() {
        return unsubscribeURL;
    }

    
    public void setUnsubscribeURL(String unsubscribeURL) {
        this.unsubscribeURL = unsubscribeURL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getTopicArn() {
        return topicArn;
    }

    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }

    public TopicMessage fromJson(String jsonString) throws IOException {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true).readValue(jsonString, TopicMessage.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static class Builder {

        private TopicMessage topicMessage = new TopicMessage();

        public Builder withMessage (String message) {
            this.topicMessage.setMessage(message);
            return this;
        }

        public Builder withType (String type) {
            this.topicMessage.setType(type);
            return this;
        }

        public Builder withTopicArn (String topicArn) {
            this.topicMessage.setTopicArn(topicArn);
            return this;
        }

        public TopicMessage build () {
            return this.topicMessage;
        }
    }
}
