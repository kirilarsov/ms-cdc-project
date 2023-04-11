package edu.kirilarsov.cdc.transaction.listener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import edu.kirilarsov.cdc.transaction.AbstractEventIT;
import edu.kirilarsov.cdc.transaction.AbstractIT;
import edu.kirilarsov.cdc.transaction.TestAMPQConfiguration;
import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import edu.kirilarsov.cdc.transaction.config.AmqpConfiguration;
import edu.kirilarsov.cdc.transaction.config.RabbitMqConfiguration;
import edu.kirilarsov.cdc.transaction.helper.TransactionDataGenerator;
import edu.kirilarsov.cdc.transaction.model.Transaction;
import edu.kirilarsov.cdc.transaction.model.TransactionQueueMessage;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "message-rabbit-client"})
@Import({TestAMPQConfiguration.class})
@SpringBootTest
class TransactionEventHandlerIT extends AbstractEventIT {

    private static final String EXCHANGE = "processor.topic";

    public static final String EVENT_NOTIFICATION = "processor.event.notification";

    public static final String TRANSACTION_QUEUE = "transactionQueue";

    public static final String ERROR_QUEUE = "errorNotification";
    public static final String ERROR_EVENT_NOTIFICATION = "processor.event.error.notification";


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitTemplate senderRabbitTemplate;


    @BeforeEach
    public void setUp() throws Exception {
        rabbitTemplate.setExchange(EXCHANGE);
        senderRabbitTemplate.setExchange(EXCHANGE);
    }

    /**
     * Test contract.message processing
     */
    @DisplayName("Process card transaction contract.message")
    @Test
    void testHandleProcessMessage() throws Exception {

        rabbitTemplate.convertAndSend(EXCHANGE, EVENT_NOTIFICATION,
            TransactionDataGenerator.mockTransactionQueueMessage());

        awaitForFilledQueue(TRANSACTION_QUEUE);
        ParameterizedTypeReference<TransactionResponseDto.TransactionDto> parameterizedTypeReference =
            new ParameterizedTypeReference<>() {
            };
        TransactionResponseDto.TransactionDto transaction =
            senderRabbitTemplate.receiveAndConvert(TRANSACTION_QUEUE, 5000, parameterizedTypeReference);

        assertThat(transaction.accountUuid()).isEqualTo("account-uuid-1");
        assertThat(transaction.cardUuid()).isEqualTo("card-uuid-1");
        assertThat(transaction.amount()).isEqualTo("251.52");
        assertThat(transaction.currency()).isEqualTo("EUR");

    }

    /**
     * Test contract.message processing with errors
     */
    @DisplayName("Process card transaction contract.message with errors")
    @Test
    void testHandleProcessErrorMessage() throws Exception {
        rabbitTemplate.convertAndSend(EXCHANGE, ERROR_EVENT_NOTIFICATION,
            TransactionDataGenerator.mockTransactionQueueMessage());

        senderRabbitTemplate.setExchange(EXCHANGE);
        awaitForFilledQueue(ERROR_QUEUE);

        ParameterizedTypeReference<TransactionQueueMessage> parameterizedTypeReference =
            new ParameterizedTypeReference<>() {
            };
        TransactionQueueMessage transactionQueueMessage =
            senderRabbitTemplate.receiveAndConvert(ERROR_QUEUE, 5000, parameterizedTypeReference);
        Assertions.assertThat(transactionQueueMessage.transactions().get(0).accountUuid())
            .isEqualTo("account-uuid-1");
    }

    private void awaitForFilledQueue(String queueName) {
        await().atMost(55, TimeUnit.SECONDS)
            .until(isQueueEmpty(queueName), CoreMatchers.is((false)));
    }

    private Callable<Boolean> isQueueEmpty(String queueName) {
        return () -> {
            var messageCount = senderRabbitTemplate.execute(channel -> channel.queueDeclare(
                queueName,
                true,
                false,
                false,
                null
            )).getMessageCount();
            return messageCount == 0;
        };
    }
}
