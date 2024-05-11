package com.sejong.bucketmanager.domain.common.aws.sqs;

import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Component
public class SqsMessageSender {
    private final SqsTemplate template;

    @Value("${spring.cloud.aws.sqs.queue-name}")
    private String QUEUE_NAME;

    public SqsMessageSender(SqsAsyncClient sqsAsyncClient) {
        this.template = SqsTemplate.newTemplate(sqsAsyncClient);
    }

    public SendResult<String> sendMessage(/*String groupId,*/ String message) {
        System.out.println("Sender: " + message);
        return template.send(
                to -> to.queue(QUEUE_NAME)
                        .payload(message)
        );

        /*
        to -> to
                .queue(queueName)
                .messageGroupId(groupId)
                .messageDeduplicationId(groupId)
                .payload(message)
         */
    }

}
