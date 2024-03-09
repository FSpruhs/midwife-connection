package com.spruhs.midwifebackend.midwife.application.ports

import com.spruhs.midwifebackend.midwife.domain.Midwife
import java.util.UUID

interface MidwifeRepository {

    fun save(midwife: Midwife): Midwife
    fun findById(id: UUID): Midwife?
    fun listAll(): List<Midwife>
}