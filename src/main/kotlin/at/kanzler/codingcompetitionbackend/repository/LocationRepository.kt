package at.kanzler.codingcompetitionbackend.repository;

import at.kanzler.codingcompetitionbackend.model.Location
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, Long> {
}