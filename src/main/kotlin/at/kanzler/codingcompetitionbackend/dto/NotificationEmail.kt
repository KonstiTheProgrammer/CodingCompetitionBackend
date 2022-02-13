package at.kanzler.codingcompetitionbackend.dto

data class NotificationEmail(
    val to: String,
    val subject: String,
    val body: String,
)