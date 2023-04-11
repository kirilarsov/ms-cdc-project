package contracts.message

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'Receive contract.message and send transaction list to destination queue'
    label 'process card transaction message'
    input {
        messageFrom('processor.topic')
        messageHeaders {
            header('contentType': 'application/json')
            header('amqp_receivedRoutingKey': 'processor.event.notification')
        }
        messageBody '''\
                    {
                      "transactions":[
                        {
                          "accountUuid":"account-uuid-1",
                          "cardUuid":"card-uuid-1",
                          "merchant":"IKEA",
                          "amount":"251.52",
                          "currency":"EUR",
                          "type":"SETTLEMENT"
                        }
                      ]
                    }
        '''
    }
    outputMessage {
        sentTo('processor.topic')
        headers {
            header('contentType': 'application/json')
            header('amqp_receivedRoutingKey': 'processor.event.created')
        }
        body(file('transactionListResponse.json'))
    }
}