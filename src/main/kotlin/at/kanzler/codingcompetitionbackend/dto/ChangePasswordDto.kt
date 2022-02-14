package at.kanzler.codingcompetitionbackend.dto

data class ChangePasswordDto(var oldPassword: String, var newPassword: String, var username: String)