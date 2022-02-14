package at.kanzler.codingcompetitionbackend.controller

import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.event.RegistrationCompleteEvent
import at.kanzler.codingcompetitionbackend.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
@RequestMapping("/api/v1/auth")
class RegistrationController(
    @Autowired private val userService: UserService,
    @Autowired private val publisher: ApplicationEventPublisher,
    @Autowired private val webServerAppContext: ServletWebServerApplicationContext,
) {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(RegistrationController::class.java);
    }

    @PostMapping("/register")
    fun register(@RequestBody userDto: UserDto): ResponseEntity<User> {
        val user = userService.registerUser(userDto);
        val uri = getServerHostnameAndPort()
        LOGGER.info("uri created $uri")
        publisher.publishEvent(RegistrationCompleteEvent(user, uri));
        LOGGER.info("User registered: $user");
        return ResponseEntity(user, org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping("/verifyRegistration")
    fun verifyRegistration(@RequestParam("token") token: String): ResponseEntity<String> {
        val success = userService.validateVerificationToken(token);
        return if (success) ResponseEntity("Registration successful", org.springframework.http.HttpStatus.OK);
        else ResponseEntity("Registration failed", org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    fun getServerHostnameAndPort(): String = "https://${InetAddress.getLoopbackAddress().getHostName()}:${
        webServerAppContext.environment.getProperty("local.server.port") ?: throw Exception("local.server.port not found")
    }"
}