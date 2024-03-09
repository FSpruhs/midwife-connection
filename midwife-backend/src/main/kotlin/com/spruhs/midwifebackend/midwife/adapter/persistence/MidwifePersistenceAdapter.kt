package com.spruhs.midwifebackend.midwife.adapter.persistence

import com.spruhs.midwifebackend.midwife.application.ports.MidwifeRepository
import com.spruhs.midwifebackend.midwife.domain.Midwife
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MidwifePersistenceAdapter(
    private val repository: Neo4jMidwifeRepository,
    private val mapper: MidwifeMapper
) : MidwifeRepository {
    override fun save(midwife: Midwife): Midwife {
        return mapper.toDomain(
            repository.save(mapper.toNode(midwife))
        )
    }

    override fun findById(id: UUID): Midwife? {
        return repository.findById(id.toString())
            .map { mapper.toDomain(it) }
            .orElse(null)
    }

    override fun listAll(): List<Midwife> {
        return repository.findAll()
            .map { midwifeNode -> mapper.toDomain(midwifeNode) }
    }
}