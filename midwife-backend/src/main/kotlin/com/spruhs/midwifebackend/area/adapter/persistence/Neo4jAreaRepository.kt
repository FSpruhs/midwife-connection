package com.spruhs.midwifebackend.area.adapter.persistence

import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface Neo4jAreaRepository : Neo4jRepository<AreaNode, String> {
    fun findByPostcode(postcode: Int): AreaNode?
}