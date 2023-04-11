package edu.kirilarsov.cdc.transaction.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import edu.kirilarsov.cdc.transaction.client.TransactionResponseDto;
import edu.kirilarsov.cdc.transaction.helper.TransactionDataGenerator;
import edu.kirilarsov.cdc.transaction.model.Transaction;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class TransactionDtoMapperTest {

    TransactionDtoMapper transactionDtoMapper = Mappers.getMapper(TransactionDtoMapper.class);

    @Test
    void testMapToTransactionDto() {
        Transaction transaction = TransactionDataGenerator.mockTransaction();
        assertThat(transactionDtoMapper.mapToTransactionDto(transaction))
            .isNotNull()
            .hasFieldOrPropertyWithValue("accountUuid", "account-uuid-1")
            .hasFieldOrPropertyWithValue("cardUuid", "card-uuid-1")
            .hasFieldOrPropertyWithValue("merchant", "IKEA")
            .hasFieldOrPropertyWithValue("amount", "251.52")
            .hasFieldOrPropertyWithValue("currency", "EUR")
            .hasFieldOrPropertyWithValue("type", TransactionResponseDto.TransactionDto.Type.SETTLEMENT);
    }

}
