package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.mapper.UserConverter
import at.kanzler.codingcompetitionbackend.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
) : UserService {
    private val userConverter = Mappers.getMapper(UserConverter::class.java)

    override fun registerUser(userDto: UserDto): User {
        val user = userConverter.convertToUser(userDto);
        user.role = "USER"
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user);
    }
}