package com.spruhs.midwifebackend.midwife.application

import com.spruhs.midwifebackend.area.application.AreaCatalog
import com.spruhs.midwifebackend.area.domain.Postcode
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

@Component
class FindAllMidwifesUseCase(
    val repository: MidwifeRepository
) {
    fun findAll(): List<Midwife> {
        return repository.listAll()
    }
}

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

@Component
class DeleteMidwifeUseCase(
    val repository: MidwifeRepository
) {
    fun delete(id: UUID) {
        repository.deleteById(id)
    }
}
