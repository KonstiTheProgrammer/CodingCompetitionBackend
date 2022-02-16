package at.kanzler.codingcompetitionbackend.service

import at.kanzler.codingcompetitionbackend.dto.CodingCompetitionDto
import at.kanzler.codingcompetitionbackend.entity.CodingCompetition
import at.kanzler.codingcompetitionbackend.mapper.CodingContestConverter
import at.kanzler.codingcompetitionbackend.repository.CodingCompetitionRepository
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CodingCompetitionServiceImpl(@Autowired private val codingCompetitionRepository: CodingCompetitionRepository) :
    CodingCompetitionService {

    private val ccConverter = Mappers.getMapper(CodingContestConverter::class.java)

    override fun getAllCodingCompetitions(): List<CodingCompetition> {
        return codingCompetitionRepository.findAll()
    }

    override fun getCodingCompetitionById(id: Long): CodingCompetition {
        return codingCompetitionRepository.findById(id).get()
    }

    override fun createCodingCompetition(codingCompetitionDto: CodingCompetitionDto): CodingCompetition {
        return codingCompetitionRepository.save(ccConverter.convertToCodingCompetition(codingCompetitionDto))
    }

    override fun updateCodingCompetition(id: Long, codingCompetitionDto: CodingCompetitionDto): CodingCompetition {
        val codingCompetitionToUpdate = codingCompetitionRepository.findById(id).orElse(null)
            ?: throw IllegalArgumentException("CodingCompetition with id $id not found")

        codingCompetitionToUpdate.title = codingCompetitionDto.title
        codingCompetitionToUpdate.description = codingCompetitionDto.description
        codingCompetitionToUpdate.startDate = codingCompetitionDto.startDate
        codingCompetitionToUpdate.endDate = codingCompetitionDto.endDate

        return codingCompetitionRepository.save(codingCompetitionToUpdate)
    }

    override fun deleteCodingCompetition(id: Long) {
        (codingCompetitionRepository.findById(id)).orElseThrow { IllegalArgumentException("CodingCompetition with id $id not found") }
        codingCompetitionRepository.deleteById(id)
    }
}