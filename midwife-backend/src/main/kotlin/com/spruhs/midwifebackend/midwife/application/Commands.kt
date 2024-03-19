package com.spruhs.midwifebackend.midwife.application

import com.spruhs.midwifebackend.area.domain.Postcode

data class RegisterMidwifeCommand(
    val firstName: String,
    val lastName: String,
    val areas: Set<Postcode>
)