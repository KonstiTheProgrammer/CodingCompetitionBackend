package at.kanzler.codingcompetitionbackend

import at.kanzler.codingcompetitionbackend.entity.CodingCompetition
import at.kanzler.codingcompetitionbackend.repository.CodingCompetitionRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime


@DataJpaTest
@RunWith(SpringRunner::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("CodingCompetitionRepositoryTest")
class CodingCompetitionRepositoryTest(@Autowired val codingCompetitionRepository: CodingCompetitionRepository) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CodingCompetitionRepositoryTest::class.java)
    }

    @Test
    internal fun `should add a new CodingCompetition to the table`() {
        val count = codingCompetitionRepository.findAll().count();

        //given
        val codingCompetition = CodingCompetition();
        codingCompetition.description = "description";
        codingCompetition.title = "title";
        codingCompetition.startDate = LocalDateTime.of(2020, 1, 1);
        codingCompetition.endDate = LocalDateTime.of(2021, 1, 1);
        //when
        codingCompetitionRepository.save(codingCompetition);

        //then
        val comp = codingCompetitionRepository.findAll();
        Assertions.assertTrue(comp.size > count)
    }

    @Test
    internal fun `should find the entity per title`() {
        //given
        val codingCompetition = CodingCompetition().apply {
            description = "description2";
            title = "title2";
            startDate = LocalDateTime.of(2020, 1, 1);
            endDate = LocalDateTime.of(2021, 1, 1);
        };
        //when
        codingCompetitionRepository.save(codingCompetition);

        val ccList = codingCompetitionRepository.findByTitle(codingCompetition.title);
        Assertions.assertTrue(ccList.isNotEmpty());
        Assertions.assertEquals(codingCompetition.title, ccList.first().title);
    }
}