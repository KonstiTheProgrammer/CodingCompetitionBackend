package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.ChangePasswordDto
import at.kanzler.codingcompetitionbackend.dto.ForgotPasswordDto
import at.kanzler.codingcompetitionbackend.dto.ResetPasswordDto
import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.PasswordResetToken
import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.entity.VerificationToken
import at.kanzler.codingcompetitionbackend.event.SendTokenEmailEvent
import at.kanzler.codingcompetitionbackend.mapper.UserConverter
import at.kanzler.codingcompetitionbackend.repository.PasswordResetTokenRepository
import at.kanzler.codingcompetitionbackend.repository.UserRepository
import at.kanzler.codingcompetitionbackend.repository.VerificationTokenRepository
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.net.InetAddress
import java.util.*
import kotlin.NoSuchElementException


@Service
class UserServiceImpl(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val verificationTokenRepository: VerificationTokenRepository,
    @Autowired private val passwordResetTokenRepository: PasswordResetTokenRepository,
    @Autowired private val webServerAppContext: ServletWebServerApplicationContext,
    @Autowired private val publisher: ApplicationEventPublisher,
) : UserService {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java);
    }

    private val userConverter = Mappers.getMapper(UserConverter::class.java)

    override fun registerUser(userDto: UserDto) {
        //set default values
        val user = userConverter.convertToUser(userDto);
        user.password = passwordEncoder.encode(user.password)

        if (userRepository.findByUsername(user.username!!) != null) {
            throw IllegalArgumentException("Username already exists")
        }

        //save verification token & user
        userRepository.save(user)
        val verificationToken = createVerificationToken(user)

        publishTokenEvent(verificationToken)
    }

    fun createVerificationToken(user: User): VerificationToken {
        val verificationToken = VerificationToken(user, UUID.randomUUID().toString())
        LOGGER.info("Saving verification token $verificationToken");
        return verificationTokenRepository.save(verificationToken);
    }

    override fun validateVerificationToken(token: String) {
        val verificationToken =
            verificationTokenRepository.findByToken(token) ?: throw NoSuchElementException("No token found for $token");

        if ((verificationToken.expirationTime!!).isBefore(java.time.LocalDateTime.now())) {
            throw IllegalArgumentException("Token expired");
        }

        val user = verificationToken.user ?: throw NoSuchElementException("No user found for $token");
        user.enabled = true;
        userRepository.save(user);
    }

    override fun generateNewVerificationToken(oldToken: String) {
        val verificationToken = verificationTokenRepository.findByToken(oldToken)
            ?: throw NoSuchElementException("No token found for $oldToken");

        verificationToken.expirationTime = VerificationToken.generateExpirationTime();
        verificationToken.token = UUID.randomUUID().toString();

        publishTokenEvent(verificationToken)
        verificationTokenRepository.save(verificationToken);
    }

    fun publishTokenEvent(token: VerificationToken) =
        publisher.publishEvent(SendTokenEmailEvent(token.user ?: throw NoSuchElementException("User not found"),
            "${getApplicationUrl()}/api/v1/auth/verifyRegistration?token=${token.token}"))


    override fun forgotPassword(passwordDto: ForgotPasswordDto) {
        val user = userRepository.findByEmail(passwordDto.email)
            ?: throw NoSuchElementException("No user found for email ${passwordDto.email}");

        val passwordResetToken = PasswordResetToken(user, UUID.randomUUID().toString())
        passwordResetTokenRepository.save(passwordResetToken)
        publisher.publishEvent(SendTokenEmailEvent(user,
            "${getApplicationUrl()}/api/v1/auth/savePassword?token=${passwordResetToken.token}"))
    }

    override fun savePassword(token: String, resetPassword: ResetPasswordDto) {
        val passwordToken = passwordResetTokenRepository.findByToken(token)
            ?: throw NoSuchElementException("No token found for $token");

        if ((passwordToken.expirationTime!!).isBefore(java.time.LocalDateTime.now())) {
            throw IllegalArgumentException("Token expired");
        }

        val user = passwordToken.user ?: throw NoSuchElementException("No user found for $token");
        user.password = passwordEncoder.encode(resetPassword.password)
        userRepository.save(user);
    }

    override fun changePassword(passwordDto: ChangePasswordDto) {
        val user = userRepository.findByUsername(passwordDto.username)
            ?: throw NoSuchElementException("No user found for username ${passwordDto.username}");

        if (!passwordEncoder.matches(passwordDto.oldPassword, user.password)) {
            throw IllegalArgumentException("Old password is incorrect");
        }

        user.password = passwordEncoder.encode(passwordDto.newPassword)
        userRepository.save(user);
    }

    fun getApplicationUrl(): String = "http://${InetAddress.getLoopbackAddress().getHostName()}:${
        webServerAppContext.environment.getProperty("local.server.port") ?: throw Exception("local.server.port not found")
    }"
}