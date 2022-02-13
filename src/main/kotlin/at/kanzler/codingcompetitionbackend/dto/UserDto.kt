package at.kanzler.codingcompetitionbackend.dto

import at.kanzler.codingcompetitionbackend.entity.User
import java.io.Serializable

data class UserDto(
    val username: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val password: String? = null,
) : Serializable