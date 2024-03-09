package com.spruhs.midwifebackend.midwife.application.ports

import com.spruhs.midwifebackend.midwife.domain.Midwife

interface MidwifeRepository {

    fun save(midwife: Midwife): Midwife
    fun listById(id: String): Midwife?
    fun listAll(): List<Midwife>
}