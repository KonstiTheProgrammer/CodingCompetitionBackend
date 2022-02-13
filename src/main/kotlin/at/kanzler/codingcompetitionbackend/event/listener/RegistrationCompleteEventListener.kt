package at.kanzler.codingcompetitionbackend.event.listener

import at.kanzler.codingcompetitionbackend.event.RegistrationCompleteEvent
import org.springframework.context.ApplicationListener
import java.util.*

class RegistrationCompleteEventListener : ApplicationListener<RegistrationCompleteEvent> {
    override fun onApplicationEvent(event: RegistrationCompleteEvent) {
        val user = event.getUser();
        val token = UUID.randomUUID().toString();
    }
}