package at.kanzler.codingcompetitionbackend

import at.kanzler.codingcompetitionbackend.dto.CodingCompetitionTestDto
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
import org.springframework.test.web.servlet.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SpringBootTest
@AutoConfigureMockMvc
internal class CodingCompetitionControllerTest @Autowired constructor(
    val mockMvc: MockMvc, val objectMapper: ObjectMapper,
) {
    val codingCompetitionUrl = "/api/v1/codingCompetitions"
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    fun parseDate(date: String): LocalDateTime = LocalDateTime.parse(date, formatter)

    @Nested
    @DisplayName("GET /api/CodingCompetitions")
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
            val id = 1L;

            //when
            mockMvc.get("$codingCompetitionUrl/$id").andDo { print() }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }

            //then
        }

        @Test
        fun `should return NOT FOUND if the id does not exists`() {
            //given
            val id = "should_not_exits";

            //when
            mockMvc.get("$codingCompetitionUrl/$id").andDo { print() }.andExpect {
                status { isNotFound() }
            }

            //then
        }
    }

    @Nested
    @DisplayName("POST /api/CodingCompetitions")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostCodingCompetitionTestDto() {
        @Test
        fun `should create a CodingCompetition`() {
            //given
            val codingCompetition = CodingCompetition(id = 1,
                title = "title1",
                description = "description1",
                startDate = parseDate("2020-01-01"),
                endDate = parseDate("2020-01-01"))

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


        @Test
        fun `should return BAD REQUEST BECAUSE THE CodingCompetition ALREADY EXISTS`() {
            //given
            val invalidCodingCompetition = CodingCompetition(id = 100,
                title = "title1",
                description = "description1",
                startDate = parseDate("2020-01-01"),
                endDate = parseDate("2020-01-01"))

            try {
                mockMvc.post(codingCompetitionUrl) {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(invalidCodingCompetition)
                }
            } catch (_: Exception) {
            }

            //when
            var perfomPost = mockMvc.post(codingCompetitionUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidCodingCompetition)
            }

            //then
            perfomPost.andDo { print() }.andExpect {
                status { isBadRequest() }
            }
        }
    }

    @Nested
    @DisplayName("PATCH /api/CodingCompetitions")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchCodingCompetition {

        @Test
        fun `should update an existing CodingCompetition`() {
            //given
            val codingCompetition = CodingCompetition(id = 1,
                title = "title1",
                description = "description1",
                startDate = parseDate("2020-01-01"),
                endDate = parseDate("2020-01-01"))

            try {
                mockMvc.post(codingCompetitionUrl) {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(codingCompetition)
                }
            } catch (_: Exception) {
            }

            val updatedCodingCompetition = CodingCompetition(id = 1,
                title = "title1",
                description = "description1",
                startDate = parseDate("2020-01-01"),
                endDate = parseDate("2020-01-01"))
            //when

            val patchRequest = mockMvc.patch(codingCompetitionUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedCodingCompetition)
            }

            //then
            patchRequest.andDo { print() }.andExpect {
                status { isOk() }
                content {
                    json(objectMapper.writeValueAsString(updatedCodingCompetition))
                }
            }

            mockMvc.get("$codingCompetitionUrl/${updatedCodingCompetition.id}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedCodingCompetition)) } }
        }

        @Test
        fun `should return BAD REQUEST if the CodingCompetition with the id doesnt exists`() {
            //given
            val codingCompetition = CodingCompetition(id = 1,
                title = "title1",
                description = "description1",
                startDate = parseDate("2020-01-01"),
                endDate = parseDate("2020-01-01"))

            //when
            mockMvc.patch(codingCompetitionUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(codingCompetition)
            }.andExpect {
                status { isBadRequest() }
            }
        }
    }

    @Nested
    @DisplayName("DELETE /api/CodingCompetitions")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteCodingCompetition {
        @Test
        fun `should delete a existing CodingCompetition`() {
            //given
            val codingCompetition = CodingCompetition(id = 1,
                title = "title1",
                description = "description1",
                startDate = parseDate("2020-01-01"),
                endDate = parseDate("2020-01-01"))

            try {
                mockMvc.post(codingCompetitionUrl) {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(codingCompetition)
                }
            } catch (_: Exception) {
            }

            //when
            mockMvc.delete("$codingCompetitionUrl/${codingCompetition.id}").andDo { print() }.andExpect {
                status { isNoContent() }
            }
            //then
        }

        @Test
        fun `should return BAD REQUEST if the CodingCompetition doesnt exists`() {
            //given
            val invalidCodingCompetition = CodingCompetition(id = 100,
                title = "title1",
                description = "description1",
                startDate = parseDate("2020-01-01"),
                endDate = parseDate("2020-01-01"))

            //when
            val deleteRequest = mockMvc.delete("$codingCompetitionUrl/${invalidCodingCompetition.id}")

            //then
            deleteRequest.andDo { print() }.andExpect {
                status { isNotFound() }
            }
        }
    }
}