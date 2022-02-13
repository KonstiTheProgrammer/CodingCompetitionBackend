package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.mapper.UserConverter
import at.kanzler.codingcompetitionbackend.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository) : UserService {
    private val userConverter = Mappers.getMapper(UserConverter::class.java)

    override fun registerUser(userDto: UserDto): User {
        return userRepository.save(userConverter.convertToUser(userDto))
    }
}