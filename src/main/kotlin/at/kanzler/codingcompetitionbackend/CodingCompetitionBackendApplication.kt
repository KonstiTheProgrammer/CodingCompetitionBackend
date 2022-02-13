package at.kanzler.codingcompetitionbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class CodingCompetitionBackendApplication

fun main(args: Array<String>) {
    runApplication<CodingCompetitionBackendApplication>(*args)
}
