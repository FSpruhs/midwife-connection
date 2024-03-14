package com.spruhs.midwifebackend.midwife.adapter.persistence

import com.spruhs.midwifebackend.area.domain.Postcode
import com.spruhs.midwifebackend.midwife.application.ports.MidwifeRepository
import com.spruhs.midwifebackend.midwife.domain.Midwife
import org.springframework.data.neo4j.core.Neo4jClient
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MidwifePersistenceAdapter(
    private val repository: Neo4jMidwifeRepository,
    private val mapper: MidwifeMapper,
    private val neo4jClient: Neo4jClient
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

    override fun findByArea(postcode: Postcode): List<Midwife> {
        return neo4jClient.query("MATCH (m:MidwifeNode)-[:SERVES]->(a:AreaNode {postcode: $0}) RETURN m")
            .bind(postcode.value).to("0")
            .fetchAs(MidwifeNode::class.java)
            .all()
            .map { midwifeNode -> mapper.toDomain(midwifeNode) }
            .toList()
    }
}