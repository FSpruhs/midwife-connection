package com.spruhs.midwifebackend.midwife.application

import com.spruhs.midwifebackend.midwife.application.ports.MidwifeRepository
import com.spruhs.midwifebackend.midwife.domain.Midwife
import com.spruhs.midwifebackend.midwife.domain.MidwifeNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class FindMidwifeByIdUseCase(
    val repository: MidwifeRepository
) {

    fun find(id: UUID): Midwife {
        return repository.findById(id) ?: throw MidwifeNotFoundException(id)
    }
}