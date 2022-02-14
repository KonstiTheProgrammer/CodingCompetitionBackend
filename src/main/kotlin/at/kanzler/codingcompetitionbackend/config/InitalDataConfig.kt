package at.kanzler.codingcompetitionbackend.config

import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.enum.Role
import at.kanzler.codingcompetitionbackend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import javax.annotation.PostConstruct


@Configuration
class InitalDataConfig(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
) {
    @PostConstruct
    fun insertDate() {
        userRepository.save(User().apply {
            username = "admin"
            role = Role.ADMIN
            password = passwordEncoder.encode("admin")
            firstName = "Admin"
            lastName = "Admin"
            email = "admin@mail.com"
        })
    }
}