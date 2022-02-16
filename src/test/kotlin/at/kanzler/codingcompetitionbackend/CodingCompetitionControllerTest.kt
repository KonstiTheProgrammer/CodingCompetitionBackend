package at.kanzler.codingcompetitionbackend

import at.kanzler.codingcompetitionbackend.entity.CodingCompetition
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootTest
@AutoConfigureMockMvc
class CodingCompetitionControllerTest @Autowired constructor(
    val mockMvc: MockMvc, val objectMapper: ObjectMapper,
) {
    val codingCompetitionUrl = "/api/v1/codingCompetition"
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    fun parseDateStringToDateTime(date: String): LocalDateTime = LocalDateTime.parse("$date 00:00", formatter)

    fun insertCodingCompetition(codingCompetition: CodingCompetition) {
        mockMvc.post(codingCompetitionUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(codingCompetition)
        }
    }

    @Nested
    @DisplayName("GET /api/v1/codingCompetition")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetCodingCompetition {
        @Test
        fun `should return all CodingCompetitions`() {
            //given
            mockMvc.get(codingCompetitionUrl).andDo { print() }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            //then
        }

        @Test
        fun `should return the CodingCompetition with the given id`() {
            //given
            val codingCompetition = CodingCompetition(
                id = 1,
                title = "title1",
                description = "description1",
                startDate = parseDateStringToDateTime("2020-01-01"),
                endDate = parseDateStringToDateTime("2020-01-01")
            )

            insertCodingCompetition(codingCompetition)

            //when
            mockMvc.get("$codingCompetitionUrl/${codingCompetition.id}").andDo { print() }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }

            //then
        }

        @Test
        fun `should return BAD REQUEST if the id does not exists`() {
            //given
            val id = "should_not_exits";

            //when
            mockMvc.get("$codingCompetitionUrl/$id").andDo { print() }.andExpect {
                status { isBadRequest() }
            }

            //then
        }
    }

    @Nested
    @DisplayName("POST /api/v1/codingCompetition")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostCodingCompetitionTestDto() {
        @Test
        fun `should create a CodingCompetition`() {
            //given
            val codingCompetition = CodingCompetition(
                id = 1,
                title = "title1",
                description = "description1",
                startDate = parseDateStringToDateTime("2020-01-01"),
                endDate = parseDateStringToDateTime("2020-01-01")
            )

            //when
            val perfomPost = mockMvc.post(codingCompetitionUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(codingCompetition)
            }

            //then
            perfomPost.andDo { print() }.andExpect {
                status { isCreated() }
            }
        }
    }


    @Nested
    @DisplayName("PATCH /api/v1/codingCompetition")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchCodingCompetition {

        @Test
        fun `should update an existing CodingCompetition`() {
            //given
            val codingCompetition = CodingCompetition(
                id = 1,
                title = "title1",
                description = "description1",
                startDate = parseDateStringToDateTime("2020-01-01"),
                endDate = parseDateStringToDateTime("2020-01-01")
            )

            //when
            insertCodingCompetition(codingCompetition)

            codingCompetition.title = "title2"

            val patchRequest = mockMvc.patch("$codingCompetitionUrl/${codingCompetition.id}") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(codingCompetition)
            }

            //then
            patchRequest.andDo { print() }.andExpect {
                status { isOk() }
                content {
                    json(objectMapper.writeValueAsString(codingCompetition))
                }
            }

            mockMvc.get("$codingCompetitionUrl/${codingCompetition.id}")
                .andExpect { content { json(objectMapper.writeValueAsString(codingCompetition)) } }
        }

        @Test
        fun `should return BAD REQUEST if the CodingCompetition with the id doesnt exists`() {

            //given
            val codingCompetition = CodingCompetition(
                id = 100,
                title = "title1",
                description = "description1",
                startDate = parseDateStringToDateTime("2020-01-01"),
                endDate = parseDateStringToDateTime("2020-01-01")
            )

            //when
            mockMvc.patch("$codingCompetitionUrl/${codingCompetition.id}") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(codingCompetition)
            }.andExpect {
                status { isBadRequest() }
            }
        }
    }

    @Nested
    @DisplayName("DELETE /api/v1/codingCompetition")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteCodingCompetition {
        @Test
        fun `should delete a existing CodingCompetition`() {
            //given
            val codingCompetition = CodingCompetition(
                id = 1,
                title = "title1",
                description = "description1",
                startDate = parseDateStringToDateTime("2020-01-01"),
                endDate = parseDateStringToDateTime("2020-01-01")
            )

            insertCodingCompetition(codingCompetition);

            //when
            mockMvc.delete("$codingCompetitionUrl/${codingCompetition.id}").andDo { print() }.andExpect {
                status { isNoContent() }
            }
            //then
        }

        @Test
        fun `should return BAD REQUEST if the CodingCompetition doesnt exists`() {
            //given
            val invalidCodingCompetition = CodingCompetition(
                id = 100,
                title = "title1",
                description = "description1",
                startDate = parseDateStringToDateTime("2020-01-01"),
                endDate = parseDateStringToDateTime("2020-01-01")
            )

            //when
            val deleteRequest = mockMvc.delete("$codingCompetitionUrl/${invalidCodingCompetition.id}")

            //then
            deleteRequest.andDo { print() }.andExpect {
                status { isBadRequest() }
            }
        }
    }
}