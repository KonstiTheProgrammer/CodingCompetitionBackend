package at.kanzler.codingcompetitionbackend.mapper

import at.kanzler.codingcompetitionbackend.dto.UserDto
import at.kanzler.codingcompetitionbackend.entity.User
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper

@Mapper
interface UserConverter {
    fun convertToUserDto(user: User): UserDto

    @InheritInverseConfiguration
    fun convertToUser(userDto: UserDto): User
}