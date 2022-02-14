package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.entity.VerificationToken
import at.kanzler.codingcompetitionbackend.mapper.UserConverter
import at.kanzler.codingcompetitionbackend.repository.UserRepository
import at.kanzler.codingcompetitionbackend.repository.VerificationTokenRepository
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val verificationTokenRepository: VerificationTokenRepository,
) : UserService {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java);
    }

    private val userConverter = Mappers.getMapper(UserConverter::class.java)

    override fun registerUser(userDto: UserDto): User {
        val user = userConverter.convertToUser(userDto);
        user.role = "USER"
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user);
    }

    override fun createVerificationToken(user: User, token: String) {
        val verificationToken = VerificationToken(user, token);
        LOGGER.info("Saving verification token $verificationToken");
        verificationTokenRepository.save(verificationToken);
    }

    override fun validateVerificationToken(token: String): Boolean {
        val verificationToken = verificationTokenRepository.findByToken(token) ?: return false;

        if ((verificationToken.expirationTime ?: return false).isBefore(java.time.LocalDateTime.now())) {
            return false;
        }

        val user = verificationToken.user ?: return false;
        user.enabled = true;
        userRepository.save(user);
        return true;
    }
}