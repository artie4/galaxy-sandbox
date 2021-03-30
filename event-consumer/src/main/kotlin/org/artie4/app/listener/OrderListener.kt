package org.artie4.app.listener

import org.artie4.app.model.Order
import org.bouncycastle.util.Strings
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class OrderListener {

    @RabbitListener(queues = ["galaxy.orders"])
    fun listenOrder(order: ByteArray) {
        val tokens = Strings.fromByteArray(order)
        println("[OrderListener /listenOrder] Order got $tokens")
    }

}