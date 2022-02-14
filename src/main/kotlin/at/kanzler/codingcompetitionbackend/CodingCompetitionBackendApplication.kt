package at.kanzler.codingcompetitionbackend

import at.kanzler.codingcompetitionbackend.entity.User
import at.kanzler.codingcompetitionbackend.enum.Role
import at.kanzler.codingcompetitionbackend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.security.crypto.password.PasswordEncoder

@EnableAsync
@SpringBootApplication
class CodingCompetitionBackendApplication
@Bean
fun commandLineRunner(): CommandLineRunner? {
    return CommandLineRunner { args: Array<String?>? -> println("Hello World") }
}


fun main(args: Array<String>) {
    runApplication<CodingCompetitionBackendApplication>(*args)
}
