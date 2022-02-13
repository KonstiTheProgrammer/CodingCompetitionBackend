package at.kanzler.codingcompetitionbackend.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(11)
    }
}