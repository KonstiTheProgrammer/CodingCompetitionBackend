package at.kanzler.codingcompetitionbackend.event.listener

import at.kanzler.codingcompetitionbackend.dto.NotificationEmail
import at.kanzler.codingcompetitionbackend.event.RegistrationCompleteEvent
import at.kanzler.codingcompetitionbackend.service.MailService
import at.kanzler.codingcompetitionbackend.service.MailServiceImpl
import at.kanzler.codingcompetitionbackend.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class RegistrationCompleteEventListener(
    @Autowired private val userService: UserService,
    @Autowired private val emailService: MailService,
) : ApplicationListener<RegistrationCompleteEvent> {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(RegistrationCompleteEventListener::class.java);
    }

    override fun onApplicationEvent(event: RegistrationCompleteEvent) {
        val user = event.getUser();
        val token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        //send email
        emailService.sendButtonEmail(NotificationEmail(user.email ?: throw Exception("User not found"),
            "Verify your account",
            "${event.getApplicationUrl()}/api/v1/auth/verifyRegistration?token=$token"));

        LOGGER.info("Sent verification email to ${user.email}");
    }
}