package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.NotificationEmail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory;
import javax.mail.internet.MimeMessage

@Service
class MailServiceImpl @Autowired constructor(
    private val mailSender: JavaMailSender,
    private val mailContentBuilder: MailContentBuilder,
) : MailService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(MailServiceImpl::class.java)
    }

    @Async
    override fun sendEmail(notificationEmail: NotificationEmail) {
        val messageHelper = MimeMessagePreparator { mimeMessage: MimeMessage ->
            val mimeMessageHelper = MimeMessageHelper(mimeMessage)
            mimeMessageHelper.setTo(notificationEmail.to)
            mimeMessageHelper.setSubject(notificationEmail.subject)
            mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.body), true)
            mimeMessageHelper.setFrom("cc@kotlin.com")
        }
        try {
            mailSender.send(messageHelper)
            LOGGER.info("Email was sent to " + notificationEmail.to)
        } catch (e: java.lang.Exception) {
            throw Exception("Email was not sent to " + notificationEmail.to)
        }
    }

    @Async
    override fun sendButtonEmail(notificationEmail: NotificationEmail) {
        val messageHelper = MimeMessagePreparator { mimeMessage: MimeMessage ->
            val mimeMessageHelper = MimeMessageHelper(mimeMessage)
            mimeMessageHelper.setTo(notificationEmail.to)
            mimeMessageHelper.setSubject(notificationEmail.subject)
            mimeMessageHelper.setText(mailContentBuilder.buildLink(notificationEmail.body), true)
            mimeMessageHelper.setFrom("cc@kotlin.com")
        }
        try {
            mailSender.send(messageHelper)
            LOGGER.info("Email was sent to " + notificationEmail.to)
        } catch (e: java.lang.Exception) {
            throw Exception("Email was not sent to " + notificationEmail.to)
        }
    }
}