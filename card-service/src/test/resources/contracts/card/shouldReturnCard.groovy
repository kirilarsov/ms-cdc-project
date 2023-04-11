package contracts.card

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name"Get Cards by accountUuid"
    description """
        given:
            accountUuid=account-uuid-1
        when:
            GET /api/cards/accountUuid=account-uuid-1 is invoked
        then:
            return cards for user with uuid account-uuid-1
    """
    request {
        method'GET'
        url '/api/cards?accountUuid=account-uuid-1'
        headers {
            header 'Content-Type': 'application/json'
        }
    }
    response {
        status OK()
        body(file('cardResponseDto.json'))
        headers {
            contentType(applicationJson())
        }
    }
}