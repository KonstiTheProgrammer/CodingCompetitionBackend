package at.kanzler.codingcompetitionbackend.mapper

import at.kanzler.codingcompetitionbackend.dto.CodingCompetitionDto
import at.kanzler.codingcompetitionbackend.entity.CodingCompetition
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper

@Mapper
interface CodingContestConverter {
    fun convertToCodingCompetitionDto(codingCompetition: CodingCompetition): CodingCompetitionDto

    @InheritInverseConfiguration
    fun convertToCodingCompetition(codingContestDto: CodingCompetitionDto): CodingCompetition
}