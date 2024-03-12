package com.spruhs.midwifebackend.area.application

import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
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
            ?: throw IllegalArgumentException("Area with postcode ${postcode.value} not found.")
    }

    fun clearAreas() {
        areas.clear()
    }

    fun getAreas(): Set<Area> {
        return areas
    }

}
