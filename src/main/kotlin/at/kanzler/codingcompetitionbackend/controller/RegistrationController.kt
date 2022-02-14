package at.kanzler.codingcompetitionbackend.controller

import at.kanzler.codingcompetitionbackend.dto.ChangePasswordDto
import at.kanzler.codingcompetitionbackend.dto.ForgotPasswordDto
import at.kanzler.codingcompetitionbackend.dto.ResetPasswordDto
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
    fun forgotPassword(@RequestBody passwordDto: ForgotPasswordDto): String {
        userService.forgotPassword(passwordDto);
        return "Password reset email sent";
    }

    @PostMapping("/savePassword")
    @ResponseStatus(HttpStatus.OK)
    fun savePassword(@RequestParam("token") token: String, @RequestBody passwordDto: ResetPasswordDto): String {
        userService.savePassword(token, passwordDto);
        return "Password saved";
    }

    @PostMapping("/changePassword")
    @ResponseStatus(HttpStatus.OK)
    fun changePassword(@RequestBody passwordDto: ChangePasswordDto): String {
        userService.changePassword(passwordDto);
        return "Password changed";
    }
}