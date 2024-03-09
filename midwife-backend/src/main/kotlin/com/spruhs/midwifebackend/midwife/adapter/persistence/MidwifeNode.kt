package com.spruhs.midwifebackend.midwife.adapter.persistence

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("MidwifeNode")
data class MidwifeNode(
    @Id
    val id: String,
    val firstName: String,
    val lastName: String
)