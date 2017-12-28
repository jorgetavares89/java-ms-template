#!/bin/bash

echo "================== Container environment variables =================="
if [ "$AWS_ENV" = "dev-local" ]
then
    echo "AWS_ENV = $AWS_ENV"
    echo "DB_URL = $DB_URL"
    echo "DB_USER = $DB_USER"
    echo "DB_PASSWORD = $DB_PASSWORD"
    echo "HIBERNATE_DDL_AUTO = $HIBERNATE_DDL_AUTO"
    echo "SNS_ENDPOINT = $SNS_ENDPOINT"
    echo "SNS_TOPIC_CREATED = $SNS_TOPIC_CREATED"
    echo "SNS_TOPIC_UPDATED = $SNS_TOPIC_UPDATED"
    echo "SNS_TOPIC_DELETED = $SNS_TOPIC_DELETED"
    echo "SQS_ENDPOINT = $SQS_ENDPOINT"
    echo "SQS_SOME_EVENT_QUEUE_NAME = $SQS_SOME_EVENT_QUEUE_NAME"
    java -jar $1 --spring.datasource.url=$DB_URL \
                 --spring.datasource.username=$DB_USER \
                 --spring.datasource.password=$DB_PASSWORD \
		         --spring.jpa.hibernate.ddl-auto=$HIBERNATE_DDL_AUTO \
                 --amazon.aws.sns.endpoint=$SNS_ENDPOINT \
                 --amazon.aws.sns.topic.created.name=$SNS_TOPIC_CREATED \
                 --amazon.aws.sns.topic.updated.name=$SNS_TOPIC_UPDATED \
                 --amazon.aws.sns.topic.deleted.name=$SNS_TOPIC_DELETED \
                 --amazon.aws.sqs.endpoint=$SQS_ENDPOINT \
                 --amazon.aws.sqs.some.event.queue.name=$SQS_SOME_EVENT_QUEUE_NAME
else
    echo "AWS_ENV = $AWS_ENV"
    echo "DB_URL = $DB_URL"
    echo "DB_USER = $DB_USER"
    echo "DB_PASSWORD = $DB_PASSWORD"
    echo "HIBERNATE_DDL_AUTO = $HIBERNATE_DDL_AUTO"
    echo "SNS_TOPIC_CREATED = $SNS_TOPIC_CREATED"
    echo "SNS_TOPIC_UPDATED = $SNS_TOPIC_UPDATED"
    echo "SNS_TOPIC_DELETED = $SNS_TOPIC_DELETED"
    echo "SQS_SOME_EVENT_QUEUE_NAME = $SQS_SOME_EVENT_QUEUE_NAME"
    java -jar $1 --spring.datasource.url=$DB_URL \
                 --spring.datasource.username=$DB_USER \
                 --spring.datasource.password=$DB_PASSWORD \
		         --spring.jpa.hibernate.ddl-auto=$HIBERNATE_DDL_AUTO \
                 --amazon.aws.sns.topic.created.name=$SNS_TOPIC_CREATED \
                 --amazon.aws.sns.topic.updated.name=$SNS_TOPIC_UPDATED \
                 --amazon.aws.sns.topic.deleted.name=$SNS_TOPIC_DELETED \
                 --amazon.aws.sqs.some.event.queue.name=$SQS_SOME_EVENT_QUEUE_NAME
fi
