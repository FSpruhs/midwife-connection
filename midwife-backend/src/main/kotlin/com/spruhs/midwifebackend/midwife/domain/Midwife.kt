package com.spruhs.midwifebackend.midwife.domain

import com.spruhs.midwifebackend.area.domain.Postcode
import java.util.UUID

class Midwife(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val areas: MutableSet<Postcode>
)