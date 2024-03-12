package com.spruhs.midwifebackend.midwife.adapter.persistence

import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface Neo4jMidwifeRepository : Neo4jRepository<MidwifeNode, String>