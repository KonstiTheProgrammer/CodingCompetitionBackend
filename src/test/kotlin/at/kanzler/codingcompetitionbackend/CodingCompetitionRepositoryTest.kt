package at.kanzler.codingcompetitionbackend

import at.kanzler.codingcompetitionbackend.model.CodingCompetition
import at.kanzler.codingcompetitionbackend.repository.CodingCompetitionRepository
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class CodingCompetitionRepositoryTest(@Autowired codingCompetitionRepository: CodingCompetitionRepository) {

    @Test
    fun `should add a new CodingCompetition to the table`() {
        //given
        var codingCompetition = CodingCompetition();

        //when

        //then
    }
}