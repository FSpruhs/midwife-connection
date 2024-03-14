package com.spruhs.midwifebackend.midwife.adapter.persistence

import com.spruhs.midwifebackend.area.adapter.persistence.AreaNode
import com.spruhs.midwifebackend.area.domain.Postcode
import com.spruhs.midwifebackend.midwife.domain.Midwife
import org.neo4j.driver.internal.InternalNode
import org.springframework.data.neo4j.core.Neo4jClient
import org.springframework.data.neo4j.core.Neo4jTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class MidwifeMapper(
    val neo4jTemplate: Neo4jTemplate,
    val neo4jClient: Neo4jClient,
) {

    fun findAreasByIds(areaIds: List<Int>): List<AreaNode> {
        val query = """
                MATCH (a:AreaNode)
                WHERE a.postcode IN $areaIds
                RETURN a
        """.trimIndent()

        return neo4jClient.query(query)
            .fetch()
            .all()
            .map { record ->
                val node = record["a"] as InternalNode
                AreaNode(
                    postcode = node["postcode"].asInt(),
                    district = node["district"].toString(),
                    city = node["city"].toString()
                )
            }
    }

    fun toNode(midwife: Midwife): MidwifeNode {
        return MidwifeNode(
            id = midwife.id.toString(),
            firstName = midwife.firstName,
            lastName = midwife.lastName,
            areas = findAreasByIds(midwife.areas.map { it.value }.toList())
        )
    }

    fun toDomain(midwifeNode: MidwifeNode): Midwife {
        return Midwife(
            id = UUID.fromString(midwifeNode.id),
            firstName = midwifeNode.firstName,
            lastName = midwifeNode.lastName,
            areas = midwifeNode.areas.map { Postcode(it.postcode) }.toMutableSet()
        )
    }

}