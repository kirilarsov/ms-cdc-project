package edu.kirilarsov.cdc.transaction.contract;

import edu.kirilarsov.cdc.transaction.TestAMQPContractConfiguration;
import edu.kirilarsov.cdc.transaction.listener.TransactionEventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureMessageVerifier
@SpringBootTest
@Import(TestAMQPContractConfiguration.class)
@ActiveProfiles({"test", "message-rabbit-client"})
public class MessageBase {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TransactionEventHandler transactionEventHandler;

    @BeforeEach
    public void setup() {

    }

}
