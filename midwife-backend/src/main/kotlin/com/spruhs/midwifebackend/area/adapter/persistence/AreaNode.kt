package com.spruhs.midwifebackend.area.adapter.persistence

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("AreaNode")
data class AreaNode(
  @Id
  val postcode: Int,
  val district: String,
  val city: String,
)