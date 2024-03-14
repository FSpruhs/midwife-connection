package com.spruhs.midwifebackend.area.domain.events

import com.spruhs.midwifebackend.area.domain.Postcode

data class AreaDeleted(val postcode: Postcode)