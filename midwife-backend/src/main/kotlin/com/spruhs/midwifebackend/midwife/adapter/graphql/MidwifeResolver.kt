package com.spruhs.midwifebackend.midwife.adapter.graphql

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class MidwifeResolver {

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

}

data class MidwifeDto(
    val id: UUID,
    val firstName: String,
    val lastName: String
)