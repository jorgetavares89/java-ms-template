package com.java.ms.template.book.config.cloud;

import org.springframework.beans.factory.annotation.Value;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

public class AwsGeneralConfig {

    @Value("${amazon.aws.region}")
    protected String amazonRegion;

    protected DefaultAWSCredentialsProviderChain getAmazonAWSCredentials() {
        return DefaultAWSCredentialsProviderChain.getInstance();
    }
}
