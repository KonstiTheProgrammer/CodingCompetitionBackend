package at.kanzler.codingcompetitionbackend.controller

import at.kanzler.codingcompetitionbackend.dto.ResetPasswordDTO
import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class RegistrationController(
    @Autowired private val userService: UserService,
) {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(RegistrationController::class.java);
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNotFound(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody userDto: UserDto): String {
        userService.registerUser(userDto);
        return "${userDto.username} registered successfully";
    }

    @GetMapping("/verifyRegistration")
    @ResponseStatus(HttpStatus.OK)
    fun verifyRegistration(@RequestParam("token") token: String): String {
        userService.validateVerificationToken(token);
        return "Registration successful";
    }

    @GetMapping("/resendVerificationToken")
    @ResponseStatus(HttpStatus.OK)
    fun resendVerificationToken(@RequestParam("token") token: String): String {
        userService.generateNewVerificationToken(token);
        return "Verification token resent";
    }

    @PostMapping("/forgotPassword")
    @ResponseStatus(HttpStatus.OK)
    fun forgotPassword(@RequestBody passwordDto: ResetPasswordDTO) = userService.forgotPassword(passwordDto);
}