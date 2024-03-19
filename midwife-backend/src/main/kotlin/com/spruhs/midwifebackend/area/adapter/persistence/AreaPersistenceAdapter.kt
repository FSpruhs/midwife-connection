package com.spruhs.midwifebackend.area.adapter.persistence

import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import org.springframework.stereotype.Component

@Component
class AreaPersistenceAdapter(
    private val repository: Neo4jAreaRepository,
    private val mapper: AreaMapper
) : AreaRepository {
    override fun saveAll(areas: Set<Area>) {
        repository.saveAll(areas.map { mapper.fromDomain(it) })
    }

    override fun save(area: Area): Area {
        repository.save(mapper.fromDomain(area)).let { return mapper.fromNode(it) }
    }

    override fun findAll(): Set<Area> {
        return repository.findAll().map { mapper.fromNode(it) }.toSet()
    }

    override fun findByPostcode(postcode: Postcode): Area? {
        return repository.findByPostcode(postcode.value)?.let { mapper.fromNode(it) }
    }

    override fun delete(postcode: Postcode): Boolean {
        repository.deleteById(postcode.value)
        return true
    }
}