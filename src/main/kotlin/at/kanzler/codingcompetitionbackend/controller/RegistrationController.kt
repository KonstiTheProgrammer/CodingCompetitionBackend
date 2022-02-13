package at.kanzler.codingcompetitionbackend.controller

import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.event.RegistrationCompleteEvent
import at.kanzler.codingcompetitionbackend.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationController(
    @Autowired private val userService: UserService,
    @Autowired private val publisher: ApplicationEventPublisher,
) {
    @PostMapping("/register")
    fun register(@RequestBody userDto: UserDto): User {
        val user = userService.registerUser(userDto);
        publisher.publishEvent(RegistrationCompleteEvent(user, "url"))
        return user;
    }
}