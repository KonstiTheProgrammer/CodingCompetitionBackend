package at.kanzler.codingcompetitionbackend.controller

import org.slf4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class TestController {

    companion object {
        val LOGGER: Logger = org.slf4j.LoggerFactory.getLogger(RegistrationController::class.java);
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        LOGGER.info("test");
        return ResponseEntity("test", org.springframework.http.HttpStatus.OK);
    }
}