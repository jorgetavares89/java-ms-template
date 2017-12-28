package com.java.ms.template.book.config.local;

import org.springframework.beans.factory.annotation.Value;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

public class AwsLocalGeneralConfig {

    @Value("${amazon.aws.local.region}")
    protected String amazonRegion;
    @Value("${amazon.aws.local.accesskey}")
    protected String amazonAWSAccessKey;
    @Value("${amazon.aws.local.secretkey}")
    protected String amazonAWSSecretKey;

    protected AWSStaticCredentialsProvider getAmazonAWSCredentials() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }
}
