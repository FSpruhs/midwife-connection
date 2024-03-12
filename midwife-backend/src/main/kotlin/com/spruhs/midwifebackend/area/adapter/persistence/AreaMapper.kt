package com.spruhs.midwifebackend.area.adapter.persistence

import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import org.springframework.stereotype.Component

@Component
class AreaMapper {

    fun fromNode(node: AreaNode): Area {
        return Area(
            Postcode(node.postcode),
            node.district,
            node.city
        )
    }

    fun fromDomain(area: Area): AreaNode {
        return AreaNode(
            area.postcode.value,
            area.district,
            area.city
        )
    }

}