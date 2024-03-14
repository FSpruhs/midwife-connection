package com.spruhs.midwifebackend.midwife.adapter.persistence

import com.spruhs.midwifebackend.area.adapter.persistence.AreaNode
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node("MidwifeNode")
data class MidwifeNode(
    @Id
    val id: String,
    val firstName: String,
    val lastName: String,
    @Relationship(type = "WORKS_IN")
    val areas: List<AreaNode>
)