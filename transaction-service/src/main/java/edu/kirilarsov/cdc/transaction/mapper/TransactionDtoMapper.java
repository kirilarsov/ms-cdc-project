package edu.kirilarsov.cdc.transaction.mapper;

import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import edu.kirilarsov.cdc.transaction.model.Transaction;
import edu.kirilarsov.cdc.transaction.model.TransactionQueueMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * TransactionDtoMapper for mapping transaction related objects.
 */
@Mapper(componentModel = "spring")
public interface TransactionDtoMapper {

    @Mapping(source = "type", target = "type", qualifiedByName = "mapTransactionType")
    TransactionResponseDto.TransactionDto mapToTransactionDto(Transaction transaction);

    @Named("mapTransactionType")
    static String mapTransactionType(Transaction.Type status) {
        return status.name();
    }

    @Mapping(source = "type", target = "type", qualifiedByName = "mapTransactionDtoType")
    Transaction mapToTransaction(TransactionQueueMessage.TransactionDto transaction);

    @Named("mapTransactionDtoType")
    static String mapTransactionDtoType(TransactionQueueMessage.TransactionDto.Type status) {
        return status.name();
    }
}
