package com.spruhs.midwifebackend.midwife.application

import com.spruhs.midwifebackend.midwife.application.ports.MidwifeRepository
import com.spruhs.midwifebackend.midwife.domain.Midwife
import org.springframework.stereotype.Component

@Component
class FindAllMidwifesUseCase(
    val repository: MidwifeRepository
) {

    fun findAll(): List<Midwife> {
        return repository.listAll();
    }

}