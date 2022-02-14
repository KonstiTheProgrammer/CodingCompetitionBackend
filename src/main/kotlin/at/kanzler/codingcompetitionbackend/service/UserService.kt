package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.ResetPasswordDTO
import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.entity.VerificationToken

interface UserService {
    fun registerUser(userDto: UserDto)
    fun validateVerificationToken(token: String)
    fun generateNewVerificationToken(oldToken: String)
    fun forgotPassword(passwordDto: ResetPasswordDTO)
}