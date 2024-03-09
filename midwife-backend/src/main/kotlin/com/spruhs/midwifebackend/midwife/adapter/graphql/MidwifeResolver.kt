package com.spruhs.midwifebackend.midwife.adapter.graphql

import com.spruhs.midwifebackend.midwife.adapter.persistence.MidwifeNode
import com.spruhs.midwifebackend.midwife.adapter.persistence.Neo4jMidwifeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class MidwifeResolver(@Autowired val neo4jMidwifeRepository: Neo4jMidwifeRepository) {

    @QueryMapping
    fun getMidwifes(): List<MidwifeDto> {
        return listOf(
            MidwifeDto(
                id = UUID.randomUUID(),
                firstName = "Rose",
                lastName = "Gneist"
            )
        )
    }

    @QueryMapping
    fun getMidwife(@Argument id: UUID): MidwifeDto {
        return MidwifeDto(
            id = UUID.randomUUID(),
            firstName = "Rose",
            lastName = "Gneist"
        )
    }

    @MutationMapping
    fun createMidwife(@Argument firstName: String, @Argument lastName: String): MidwifeDto {
        neo4jMidwifeRepository.save(
            MidwifeNode(
                id = UUID.randomUUID().toString(),
                firstName = firstName,
                lastName = lastName
            )
        )
        return MidwifeDto(
            id = UUID.randomUUID(),
            firstName = firstName,
            lastName = lastName
        )
    }
}

data class MidwifeDto(
    val id: UUID,
    val firstName: String,
    val lastName: String
)