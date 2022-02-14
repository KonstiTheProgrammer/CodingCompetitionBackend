package at.kanzler.codingcompetitionbackend.event.listener

import at.kanzler.codingcompetitionbackend.dto.NotificationEmail
import at.kanzler.codingcompetitionbackend.event.SendTokenEmailEvent
import at.kanzler.codingcompetitionbackend.service.MailService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class SendTokenEmailListener(
    @Autowired private val emailService: MailService,
) : ApplicationListener<SendTokenEmailEvent> {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(SendTokenEmailListener::class.java);
    }

    override fun onApplicationEvent(event: SendTokenEmailEvent) {
        val user = event.getUser();
        //send email
        emailService.sendButtonEmail(NotificationEmail(user.email ?: throw Exception("User not found"),
            "Verify your account",
            event.getApplicationUrl()));

        LOGGER.info("Sent email to ${user.email}");
    }
}