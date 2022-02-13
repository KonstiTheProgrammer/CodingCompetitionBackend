package at.kanzler.codingcompetitionbackend.repository;

import at.kanzler.codingcompetitionbackend.entity.UserAttendsCodingCompetition
import at.kanzler.codingcompetitionbackend.entity.UserAttendsCodingCompetitionId
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface UserAttendsCodingCompetitionRepository :
    JpaRepository<UserAttendsCodingCompetition, UserAttendsCodingCompetitionId> {
}