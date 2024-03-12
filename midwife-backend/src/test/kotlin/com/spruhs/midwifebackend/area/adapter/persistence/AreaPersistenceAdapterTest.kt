package com.spruhs.midwifebackend.area.adapter.persistence

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.neo4j.harness.Neo4j
import org.neo4j.harness.Neo4jBuilders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.neo4j.core.Neo4jClient
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource


@DataNeo4jTest
//@SpringBootTest(classes = [AreaPersistenceAdapter::class])
class AreaPersistenceAdapterTest {

    //@Autowired
    //private lateinit var areaPersistenceAdapter: AreaPersistenceAdapter

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
    fun findSomethingShouldWork(@Autowired client: Neo4jClient) {
        val result = client.query("MATCH (n) RETURN COUNT(n)")
            .fetchAs(Long::class.java)
            .one()
        assertThat(result).hasValue(0L)
    }

    //@Test
    //fun `should return all areas`() {
    //    val areas = areaPersistenceAdapter.findAll()
    //    assertThat(areas).isEmpty()
    //}


}