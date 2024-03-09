package com.spruhs.midwifebackend.midwife.adapter.persistence

import com.spruhs.midwifebackend.midwife.domain.Midwife
import org.springframework.stereotype.Component
import java.util.*

@Component
class MidwifeMapper {

    fun toNode(midwife: Midwife): MidwifeNode {
        return MidwifeNode(
            id = midwife.id.toString(),
            firstName = midwife.firstName,
            lastName = midwife.lastName
        )
    }

    fun toDomain(midwifeNode: MidwifeNode): Midwife {
        return Midwife(
            id = UUID.fromString(midwifeNode.id),
            firstName = midwifeNode.firstName,
            lastName = midwifeNode.lastName
        )
    }

}