package org.artie4.app.event

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.artie4.app.client.EventConsumerClient
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import kotlin.coroutines.CoroutineContext

@Component
class OnApplicationStartedEvent(
    private val eventConsumerClient: EventConsumerClient
) : ApplicationListener<ApplicationStartedEvent>, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        launch { eventConsumerClient.pushEvent() }
    }
}