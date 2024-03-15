package com.spruhs.midwifebackend.area.adapter.graphql

import com.spruhs.midwifebackend.area.application.AreaCatalog
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.mock.mockito.MockBean
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