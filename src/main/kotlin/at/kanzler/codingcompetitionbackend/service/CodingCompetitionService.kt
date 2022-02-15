package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.CodingCompetitionDto
import at.kanzler.codingcompetitionbackend.entity.CodingCompetition

interface CodingCompetitionService{
    fun getAllCodingCompetitions(): List<CodingCompetition>
    fun getCodingCompetitionById(id: Long): CodingCompetition
    fun createCodingCompetition(codingCompetitionDto: CodingCompetitionDto): CodingCompetition
    fun updateCodingCompetition(id : Long,codingCompetitionDto: CodingCompetitionDto): CodingCompetition
    fun deleteCodingCompetition(id: Long)
}