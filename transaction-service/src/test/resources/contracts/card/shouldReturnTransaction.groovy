package contracts.transaction

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name"Get Transactions by cardUuid"
    description """
        given:
            cardUuid=card-uuid-1
        when:
            GET /api/transactions/card/card-uuid-1 is invoked
        then:
            return transactions for user with uuid card-uuid-1
    """
    request {
        method'GET'
        url '/api/transactions/card/card-uuid-1'
        headers {
            header 'Content-Type': 'application/json'
        }
    }
    response {
        status OK()
        body(file('transactionResponseDto.json'))
        headers {
            contentType(applicationJson())
        }
    }
}