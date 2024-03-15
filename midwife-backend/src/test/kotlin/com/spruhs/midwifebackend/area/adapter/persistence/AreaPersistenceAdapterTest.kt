package com.spruhs.midwifebackend.area.adapter.persistence

import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.neo4j.harness.Neo4j
import org.neo4j.harness.Neo4jBuilders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.data.neo4j.core.Neo4jClient
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource



@DataNeo4jTest(includeFilters = [ComponentScan.Filter(classes = [
    AreaPersistenceAdapter::class,
    AreaMapper::class,
    Neo4jAreaRepository::class], type = FilterType.ASSIGNABLE_TYPE)])
class AreaPersistenceAdapterTest {

    @Autowired
    lateinit var areaPersistenceAdapter: AreaPersistenceAdapter

    companion object {
        private lateinit var neo4jContainer: Neo4j

        @BeforeAll
        @JvmStatic
        fun initializeNeo4j() {
            neo4jContainer = Neo4jBuilders.newInProcessBuilder()
                .withDisabledServer()
                .build()
        }

        @AfterAll
        @JvmStatic
        fun stopNeo4j() {
            neo4jContainer.close()
        }

        @DynamicPropertySource
        @JvmStatic
        fun neo4jProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.neo4j.uri", neo4jContainer::boltURI)
            registry.add("spring.neo4j.authentication.username") { "neo4j" }
            registry.add("spring.neo4j.authentication.password") { "null" }
        }
    }

    @Test
    fun `should return all areas`(@Autowired client: Neo4jClient) {
        //Given
        val area1 = Area(Postcode(50733), "Nippes", "Köln")
        val area2 = Area(Postcode(50674), "Innenstadt", "Köln")

        areaPersistenceAdapter.save(area1)
        areaPersistenceAdapter.save(area2)

        //When
        val areas = areaPersistenceAdapter.findAll()

        //Then
        assertThat(areas).isEqualTo(setOf(area1, area2))
    }

    @Test
    fun `should save an area`(@Autowired client: Neo4jClient) {
        //Given
        val area = Area(Postcode(50733), "Nippes", "Köln")

        //When
        val savedArea = areaPersistenceAdapter.save(area)

        //Then
        assertThat(savedArea).isEqualTo(area)
    }

}