package com.spruhs.midwifebackend.midwife.application

import com.spruhs.midwifebackend.area.application.AreaCatalog
import com.spruhs.midwifebackend.area.domain.Postcode
import com.spruhs.midwifebackend.midwife.application.ports.MidwifeRepository
import com.spruhs.midwifebackend.midwife.domain.Midwife
import org.springframework.stereotype.Component
import java.util.*

@Component
class RegisterMidwifeUseCase(
    val repository: MidwifeRepository,
    val areaCatalog: AreaCatalog
) {

    fun register(command: RegisterMidwifeCommand): Midwife {
        validateAreas(command.areas)
        return repository.save(Midwife(
            id = UUID.randomUUID(),
            firstName = command.firstName,
            lastName = command.lastName,
            areas = command.areas.toMutableSet()
        ))
    }

    private fun validateAreas(areas: Set<Postcode>) {
        areaCatalog.validate(areas)
    }

}

data class RegisterMidwifeCommand(
    val firstName: String,
    val lastName: String,
    val areas: Set<Postcode>
)