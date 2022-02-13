package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.User

interface UserService {
    fun registerUser(userDto: UserDto): User;
    fun createVerificationToken(user: User, token: String)
}