package at.kanzler.codingcompetitionbackend.controller

import at.kanzler.codingcompetitionbackend.dto.CodingCompetitionDto
import at.kanzler.codingcompetitionbackend.entity.CodingCompetition
import at.kanzler.codingcompetitionbackend.service.CodingCompetitionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/codingCompetition")
class CodingCompetitionController(@Autowired private val service: CodingCompetitionService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNotFound(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    fun getCodingCompetitions(): Collection<CodingCompetition> = service.getAllCodingCompetitions()

    @GetMapping("/{id}")
    fun getCodingCompetitionById(@PathVariable id: Long): CodingCompetition =
        service.getCodingCompetitionById(id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCodingCompetition(@RequestBody CodingCompetition: CodingCompetitionDto) =
        service.createCodingCompetition(CodingCompetition)


    @PatchMapping("/{id}")
    fun updateCodingCompetition(@PathVariable("id") id : Long, @RequestBody CodingCompetition: CodingCompetitionDto): CodingCompetition =
        service.updateCodingCompetition(id, CodingCompetition)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCodingCompetition(@PathVariable id: Long) = service.deleteCodingCompetition(id);
}