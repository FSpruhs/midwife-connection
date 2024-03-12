package com.spruhs.midwifebackend.area.application

import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.AreaNotFoundException
import com.spruhs.midwifebackend.area.domain.Postcode
import org.springframework.stereotype.Component

@Component
class AreaCatalog(
    val repository: AreaRepository
) {
    private val areas = mutableSetOf<Area>()

    fun addArea(area: Area): Area {
        require(areas.contains(area).not()) { "Area with postcode ${area.postcode.value} already exists." }
        areas.add(area)
        return repository.save(area)
    }

    fun addAllAreas(areas: Set<Area>) {
        this.areas.addAll(areas)
        repository.saveAll(areas)
    }

    fun findArea(postcode: Postcode): Area {
        return areas.find { it.postcode == postcode }
            ?: repository.findByPostcode(postcode)?.also { areas.plus(it) }
            ?: throw AreaNotFoundException("Area with postcode ${postcode.value} not found.")
    }

    fun clearAreas() {
        areas.clear()
    }

    fun getAreas(): Set<Area> {
        return areas
    }

    fun loadAreas() {
        areas.addAll(repository.findAll())
    }

    fun deleteArea(postcode: Postcode): Boolean {
        findArea(postcode).let { area: Area ->
            areas.remove(area)
            return repository.delete(postcode)
        }
    }

    fun updateArea(area: Area): Area {
        findArea(area.postcode).let {
            areas.remove(it)
            areas.add(area)
            return repository.save(area)
        }
    }

}
