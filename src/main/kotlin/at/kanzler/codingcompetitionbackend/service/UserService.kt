package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.ChangePasswordDto
import at.kanzler.codingcompetitionbackend.dto.ForgotPasswordDto
import at.kanzler.codingcompetitionbackend.dto.ResetPasswordDto
import at.kanzler.codingcompetitionbackend.dto.UserDto

interface UserService {
    fun registerUser(userDto: UserDto)
    fun validateVerificationToken(token: String)
    fun generateNewVerificationToken(oldToken: String)
    fun forgotPassword(passwordDto: ForgotPasswordDto)
    fun savePassword(token: String, resetPassword: ResetPasswordDto)
    fun changePassword(passwordDto: ChangePasswordDto)
}