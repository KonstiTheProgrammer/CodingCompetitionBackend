package at.kanzler.codingcompetitionbackend.controller

import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.event.RegistrationCompleteEvent
import at.kanzler.codingcompetitionbackend.service.UserService
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1")
class RegistrationController(
    @Autowired private val userService: UserService,
    @Autowired private val publisher: ApplicationEventPublisher,
) {

    companion object {
        val LOGGER: Logger = org.slf4j.LoggerFactory.getLogger(RegistrationController::class.java);
    }

    @PostMapping("/register")
    fun register(@RequestBody userDto: UserDto): ResponseEntity<User> {
        LOGGER.info("Registering user: {}", userDto);
        val user = userService.registerUser(userDto);
        val uri = "${ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString()} "
        publisher.publishEvent(RegistrationCompleteEvent(user, uri));
        return ResponseEntity(user, org.springframework.http.HttpStatus.CREATED);
    }
}