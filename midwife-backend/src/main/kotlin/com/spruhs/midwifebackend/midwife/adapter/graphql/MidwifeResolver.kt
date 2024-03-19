package com.spruhs.midwifebackend.midwife.adapter.graphql

import com.spruhs.midwifebackend.area.domain.Postcode
import com.spruhs.midwifebackend.midwife.application.*
import com.spruhs.midwifebackend.midwife.domain.Midwife
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class MidwifeResolver(
    private val findMidwifeByIdUseCase: FindMidwifeByIdUseCase,
    private val findAllMidwifesUseCase: FindAllMidwifesUseCase,
    private val registerMidwifeUseCase: RegisterMidwifeUseCase,
    private val deleteMidwifeUseCase: DeleteMidwifeUseCase
) {

    @QueryMapping
    fun getMidwifes(): List<MidwifeDto> {
        return findAllMidwifesUseCase.findAll().map(::toDto)
    }

    @QueryMapping
    fun getMidwife(@Argument id: UUID): MidwifeDto {
        return findMidwifeByIdUseCase.find(id).let(::toDto)
    }

    @MutationMapping
    fun createMidwife(
        @Argument firstName: String,
        @Argument lastName: String,
        @Argument areas: List<Int>
    ): MidwifeDto {
        return registerMidwifeUseCase
            .register(RegisterMidwifeCommand(
                firstName = firstName,
                lastName = lastName,
                areas = areas.map { Postcode(it) }.toSet()
            )).let(::toDto)
    }

    @MutationMapping
    fun deleteMidwife(@Argument id: UUID): Boolean {
        deleteMidwifeUseCase.delete(id)
        return true
    }
}

private fun toDto(midwife: Midwife): MidwifeDto {
    return MidwifeDto(
        id = midwife.id,
        firstName = midwife.firstName,
        lastName = midwife.lastName,
        areas = midwife.areas.map { it.value }.toSet()
    )
}

data class MidwifeDto(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val areas: Set<Int>
)