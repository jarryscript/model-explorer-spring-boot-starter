package io.github.jarryzhou.modelexplorer

import io.github.jarryzhou.modelexplorer.modelrecorder.DashboardService
import io.github.jarryzhou.modelexplorer.modelrecorder.ModelExplorerConfiguration
import io.mockk.every
import org.springframework.jdbc.core.JdbcTemplate

class DashboardServiceTest {
//
//    private lateinit var jdbcTemplate: JdbcTemplate
//    private lateinit var modelExplorerConfiguration: ModelExplorerConfiguration
//    private lateinit var dashboardService: DashboardService
//
//    fun setup() {
//        jdbcTemplate = mockk()
//        modelExplorerConfiguration = mockk()
//        dashboardService = DashboardService(jdbcTemplate, modelExplorerConfiguration)
//    }
//
//    @Test
//    fun testLoadAll() {
//        val expected = listOf(ModelDto(name = "Date", diagram = "SVG", info = "Info"))
//
//        every { jdbcTemplate.query(any(), any()) } returns expected
//
//        val result = dashboardService.loadAll()
//
//        assertEquals(expected, result)
//
//        verify { jdbcTemplate.query(DashboardService.SELECT_ALL_DIAGRAM_SQL, any<() -> ModelDto>()) }
//    }
//
//    @Test
//    fun testShouldCreateNewRecord() {
//        every { dashboardService.getLastDiagram() } returns "old diagram"
//
//        val result = dashboardService.shouldCreateNewRecord("new diagram")
//
//        assert(result)
//
//        every { dashboardService.getLastDiagram() } returns "new diagram"
//
//        val result2 = dashboardService.shouldCreateNewRecord("new diagram")
//
//        assertFalse(result2)
//    }


}