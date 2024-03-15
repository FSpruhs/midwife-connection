package com.spruhs.midwifebackend.area.adapter.graphql

import com.spruhs.midwifebackend.area.adapter.persistence.AreaMapper
import com.spruhs.midwifebackend.area.adapter.persistence.AreaPersistenceAdapter
import com.spruhs.midwifebackend.area.adapter.persistence.Neo4jAreaRepository
import com.spruhs.midwifebackend.area.application.AreaCatalog
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.graphql.test.tester.GraphQlTester



@AutoConfigureGraphQlTester
@GraphQlTest(value = [AreaResolver::class])
class AreaResolverTest {

    @Autowired
    lateinit var graphQlTester: GraphQlTester

    @MockBean
    lateinit var areaCatalog: AreaCatalog

    @Test
    fun `should return all areas`() {
        val query = """
        query {
           getAreas {
               postcode
               district
               city
           }
        }
        """.trimIndent()

        val area = Area(
            postcode = Postcode(12345),
            district = "District",
            city = "City"
        )

        every { areaCatalog.getAreas() } returns setOf(area)

        graphQlTester
            .document(query)
            .executeAndVerify()

    }

}