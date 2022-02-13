package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.NotificationEmail

interface MailService {
    fun sendEmail(notificationEmail: NotificationEmail)
    fun sendButtonEmail(notificationEmail: NotificationEmail)
}
