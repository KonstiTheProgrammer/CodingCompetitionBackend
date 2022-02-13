package at.kanzler.codingcompetitionbackend

import at.kanzler.codingcompetitionbackend.entity.File
import at.kanzler.codingcompetitionbackend.entity.Task
import at.kanzler.codingcompetitionbackend.repository.FileRepository
import at.kanzler.codingcompetitionbackend.repository.TaskRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import javax.transaction.Transactional


@SpringBootTest
@DisplayName("CodingCompetitionRepositoryTest")
class FileAndTaskRepositoryTest(
    @Autowired val fileRepository: FileRepository,
    @Autowired val taskRepository: TaskRepository,
) {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(FileAndTaskRepositoryTest::class.java)
    }

    private val testTitle = "Test Task"

    val task1 = Task().apply {
        title = testTitle
        description = "TestTask"
        level = 1
    };

    val file1 = File().apply {
        fileUrl = "/static/files/test1.txt"
        task = task1
    }

    val file2 = File().apply {
        fileUrl = "/static/files/test2.txt"
        task = task1
    }

    val file3 = File().apply {
        fileUrl = "/static/files/test3.txt"
        task = task1
    }

    val file4 = File().apply {
        fileUrl = "/static/files/test4.txt"
        task = task1
    }


    @Transactional
    fun insertTestData() {
        fileRepository.save(file1)
        fileRepository.save(file2)
        fileRepository.save(file3)
        fileRepository.save(file4)
    }

    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    @Transactional
    @Test
    fun `test if cascading is activated`() {
        val shouldNotExistTask = taskRepository.findByTitle(testTitle);
        Assertions.assertNull(shouldNotExistTask)

        insertTestData()

        val task = taskRepository.findByTitle(testTitle)
        Assertions.assertNotNull(task)
    }


    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    @Transactional
    @Test
    internal fun `check if lazy load is enabled`() {
        insertTestData()

        val validfile = fileRepository.findByFileUrl("/static/files/test1.txt")
            .also { LOGGER.info { "file: $it" } }
        val task = validfile?.task
        Assertions.assertNotNull(task)
    }
}