package at.kanzler.codingcompetitionbackend.repository;

import at.kanzler.codingcompetitionbackend.entity.CodingCompetition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CodingCompetitionRepository : JpaRepository<CodingCompetition, Long> {
    fun findByStartDateBetween(startDate: LocalDateTime, startDate2: LocalDateTime): List<CodingCompetition>
    fun findByTitle(title: String?): List<CodingCompetition>
}