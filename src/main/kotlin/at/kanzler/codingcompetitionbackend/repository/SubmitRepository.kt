package at.kanzler.codingcompetitionbackend.repository;

import at.kanzler.codingcompetitionbackend.entity.Submit
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface SubmitRepository : JpaRepository<Submit, Long> {
}