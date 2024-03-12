package com.spruhs.midwifebackend.area.adapter.persistence

import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import org.springframework.stereotype.Component

@Component
class AreaPersistenceAdapter : AreaRepository {
    override fun saveAll(areas: Set<Area>) {
        TODO("Not yet implemented")
    }

    override fun findAll(): Set<Area> {
        TODO("Not yet implemented")
    }

    override fun findByPostcode(postcode: Postcode): Area? {
        TODO("Not yet implemented")
    }
}