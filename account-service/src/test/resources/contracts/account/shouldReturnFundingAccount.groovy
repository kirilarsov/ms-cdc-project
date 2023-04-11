package contracts.account

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name"Get Account by userUuid"
    description """
        given:
            userUuid=a6d2c821-c730-43ae-af78-992c1e1e6bb2
        when:
            GET /api/accounts/userUuid=a6d2c821-c730-43ae-af78-992c1e1e6bb2 is invoked
        then:
            return account for user with uuid a6d2c821-c730-43ae-af78-992c1e1e6bb2
    """
    request {
        method'GET'
        url '/api/accounts?userUuid=a6d2c821-c730-43ae-af78-992c1e1e6bb2'
        headers {
            header 'Content-Type': 'application/json'
        }
    }
    response {
        status OK()
        body(file('accountResponseDto.json'))
        headers {
            contentType(applicationJson())
        }
    }
}