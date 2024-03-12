package com.spruhs.midwifebackend.area.application.ports

import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode

interface AreaRepository {

    fun saveAll(areas: Set<Area>)
    fun save(area: Area): Area
    fun findAll(): Set<Area>
    fun findByPostcode(postcode: Postcode): Area?
    fun delete(postcode: Postcode): Boolean

}