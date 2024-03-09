package com.spruhs.midwifebackend.midwife.domain

import java.util.*

class MidwifeNotFoundException(id: UUID) : RuntimeException("Midwife with id $id not found.")