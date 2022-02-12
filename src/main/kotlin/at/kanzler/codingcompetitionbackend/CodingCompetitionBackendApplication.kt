package at.kanzler.codingcompetitionbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CodingCompetitionBackendApplication

fun main(args: Array<String>) {
    runApplication<CodingCompetitionBackendApplication>(*args)
}
