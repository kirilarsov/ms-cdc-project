package edu.kirilarsov.cdc.transaction.listener;

import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import edu.kirilarsov.cdc.transaction.config.RabbitMqConfiguration;
import edu.kirilarsov.cdc.transaction.mapper.TransactionDtoMapper;
import edu.kirilarsov.cdc.transaction.model.Transaction;
import edu.kirilarsov.cdc.transaction.model.TransactionQueueMessage;
import edu.kirilarsov.cdc.transaction.queue.MessageService;
import edu.kirilarsov.cdc.transaction.repository.TransactionRepository;
import java.util.List;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * File Processor Event Handler handles any new messages on the queue.
 */
@Profile("message-rabbit-client")
@Service
public class TransactionEventHandler {

    private final MessageService messageService;
    private final RabbitMqConfiguration configuration;
    private final TransactionDtoMapper transactionDtoMapper;
    private final TransactionRepository transactionRepository;

    /**
     * TransactionEventHandler constructor.
     *
     * @param messageService        MessageService
     * @param configuration         RabbitMqConfiguration
     * @param transactionDtoMapper  TransactionDtoMapper
     * @param transactionRepository TransactionRepository
     */
    public TransactionEventHandler(
        MessageService messageService,
        RabbitMqConfiguration configuration,
        TransactionDtoMapper transactionDtoMapper,
        TransactionRepository transactionRepository
    ) {
        this.messageService = messageService;
        this.configuration = configuration;
        this.transactionDtoMapper = transactionDtoMapper;
        this.transactionRepository = transactionRepository;
    }

    /**
     * processTransactionMessage.
     *
     * @param transactionQueueMessage TransactionQueueMessage
     */
    @RabbitListener(queues = "${transaction-service.rabbitmq.inputs.process-file.queue}")
    public void processTransactionMessage(final TransactionQueueMessage transactionQueueMessage) {
        try {
            List<Transaction> transactions =
                transactionQueueMessage.transactions().stream().map(transactionDtoMapper::mapToTransaction).toList();

            List<Transaction> savedTransactions =
                transactions.stream().map(transactionRepository::save).toList();

            sendProcessingSucceededMessage(
                savedTransactions.stream().map(transactionDtoMapper::mapToTransactionDto).toList());
        } catch (Exception e) {
            sendProcessingFailedMessage(transactionQueueMessage);
        }
    }


    private void sendProcessingFailedMessage(TransactionQueueMessage transactionQueueMessage) {
        var output = configuration.outputs();

        messageService.send(output.transactionError(), transactionQueueMessage);
    }

    private void sendProcessingSucceededMessage(List<TransactionResponseDto.TransactionDto> transactions) {
        var destination = configuration.outputs().fileProcessed();
        for (TransactionResponseDto.TransactionDto message : transactions) {
            messageService.send(destination, message);
        }
    }
}
