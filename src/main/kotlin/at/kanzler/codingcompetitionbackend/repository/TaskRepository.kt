package at.kanzler.codingcompetitionbackend.repository;

import at.kanzler.codingcompetitionbackend.model.Task
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
}