package io.github.jarryzhou.modelexplorer

import io.github.jarryzhou.modelexplorer.modelrecorder.ModelService
import io.github.jarryzhou.modelexplorer.modelrecorder.SELECT_LAST_DIAGRAM_SQL
import io.github.jarryzhou.modelexplorer.modelrecorder.TABLE_FIELD_DIAGRAM
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.springframework.jdbc.core.JdbcTemplate

class ModelServiceTest {

    @MockK
    private lateinit var jdbcTemplate: JdbcTemplate;
//    private val properties: ModelExplorerProperties = mockk {
//        every { scanPackages } returns listOf("test")
//        every { hideClasses } returns listOf("test")
//    }

    @InjectMockKs
    private lateinit var modelService: ModelService

    fun setUp() {
        service = ModelService(jdbcTemplate, properties)
    }

    fun `reload should generate diagram when no digrams existing`() {
        every { jdbcTemplate.queryForObject(any(),Boolean::class.java) } returns false
        every { jdbcTemplate.query(any(), any()) } returns null
        modelService.reload();
        verify(exactly = 1) { jdbcTemplate.execute(any<String>()) }
        verify(exactly = 1) { jdbcTemplate.execute(any<String>()) }
        verify(exactly = 1) { jdbcTemplate.execute(any<String>()) }
    }

    fun testLoadAllModelInfo() {
        val dto = ModelDto(1L, "test", "", "")
        every { jdbcTemplate.query(any<String>(), any<RowMapper<ModelDto>>()) } returns listOf(dto)
        val result = service.loadAllModelInfo()
        assertEquals(listOf(dto), result)
    }

    fun testLoadModelById() {
        val dto = ModelDto(1L, "test", "", "")
        every { jdbcTemplate.queryForObject(any<String>(), any<RowMapper<ModelDto>>(), any<Int>()) } returns dto
        val result = service.loadModelById(1L)
        assertEquals(dto, result)
    }

    fun testDeleteById() {
        every { jdbcTemplate.update(any<String>(), any<Int>()) } returns 1
        service.deleteById(1L)
        verify { jdbcTemplate.update(any<String>(), any<Int>()) }
    }
}