package com.spruhs.midwifebackend.area.application

import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import org.springframework.stereotype.Component

@Component
class AreaCatalog(
    val repository: AreaRepository
) {
    val areas = mutableSetOf<Area>()

    fun addArea(area: Area) {
        require(areas.contains(area).not()) { "Area with postcode ${area.postcode.value} already exists" }
        areas.add(area)
    }

    fun findAreaByPostcode(postcode: Postcode): Area {
        return areas.find { it.postcode == postcode }
            ?: repository.findByPostcode(postcode)?.also { areas.plus(it) }
            ?: throw IllegalArgumentException("Area with postcode $postcode not found")
    }

    fun findAreaByName(name: String): Area? {
        return areas.find { it.name == name }
    }

    fun saveAreas() {
        repository.saveAll(areas);
    }

    fun clearAreas() {
        areas.clear()
    }


}
