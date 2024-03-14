package com.spruhs.midwifebackend.midwife.application

import com.spruhs.midwifebackend.area.domain.Postcode
import com.spruhs.midwifebackend.midwife.application.ports.MidwifeRepository
import org.springframework.stereotype.Component

@Component
class DeleteAreaUseCase(
    private val repository: MidwifeRepository,
) {

    fun delete(postcode: Postcode) {
        repository.findByArea(postcode).forEach { midwife ->
            midwife.areas.remove(postcode)
            repository.save(midwife)
        }
    }
}