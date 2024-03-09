package com.spruhs.midwifebackend.midwife.adapter.persistence

import com.spruhs.midwifebackend.midwife.application.ports.MidwifeRepository
import com.spruhs.midwifebackend.midwife.domain.Midwife

class MidwifePersistenceAdapter(
    private val repository: Neo4jMidwifeRepository,
    private val mapper: MidwifeMapper
) : MidwifeRepository {
    override fun save(midwife: Midwife): Midwife {
        return mapper.toDomain(
            repository.save(mapper.toNode(midwife))
        )
    }

    override fun listById(id: String): Midwife? {
        return repository.findById(id)
            .map { mapper.toDomain(it) }
            .orElse(null)
    }

    override fun listAll(): List<Midwife> {
        return repository.findAll()
            .map { midwifeNode -> mapper.toDomain(midwifeNode) }
    }
}