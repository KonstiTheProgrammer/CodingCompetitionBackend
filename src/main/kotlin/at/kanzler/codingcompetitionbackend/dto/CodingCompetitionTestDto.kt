package at.kanzler.codingcompetitionbackend.dto

import java.io.Serializable
import java.time.LocalDateTime

data class CodingCompetitionTestDto(
    val id: Long? = null,
    val startDate: LocalDateTime? = null,
    val endDate: LocalDateTime? = null,
    val title: String? = null,
    val description: String? = null,
) : Serializable
