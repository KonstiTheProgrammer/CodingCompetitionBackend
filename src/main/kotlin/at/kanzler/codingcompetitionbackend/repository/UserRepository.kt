package at.kanzler.codingcompetitionbackend.repository;

import at.kanzler.codingcompetitionbackend.model.User
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
}