package at.kanzler.codingcompetitionbackend.event

import at.kanzler.codingcompetitionbackend.entity.User
import org.springframework.context.ApplicationEvent

class SendTokenEmailEvent(private val user: User, private val applicationUrl: String) : ApplicationEvent(user) {
    fun getUser(): User {
        return user
    }

    fun getApplicationUrl(): String {
        return applicationUrl
    }
}