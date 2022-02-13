package at.kanzler.codingcompetitionbackend.repository;

import at.kanzler.codingcompetitionbackend.entity.File
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface FileRepository : JpaRepository<File, Long> {
    fun findByFileUrl(fileUrl: String): File?
}