package com.spruhs.midwifebackend.area.application

import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.AreaNotFoundException
import com.spruhs.midwifebackend.area.domain.Postcode
import com.spruhs.midwifebackend.area.domain.events.AreaDeleted
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class AreaCatalog(
    val repository: AreaRepository,
    val eventPublisher: ApplicationEventPublisher
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

    fun validate(areas: Set<Postcode>) {
        areas.firstOrNull { postcode ->
            this.areas.none { it.postcode == postcode }
        }?.let { invalidPostcode ->
            throw AreaNotFoundException("Area with postcode ${invalidPostcode.value} not found.")
        }
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
        findArea(postcode).run {
            areas.remove(this)
            repository.delete(postcode).also { isDeleted ->
                if (isDeleted) {
                    eventPublisher.publishEvent(AreaDeleted(postcode))
                }
                return isDeleted
            }
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
