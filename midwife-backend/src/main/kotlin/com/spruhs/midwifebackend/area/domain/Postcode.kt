package com.spruhs.midwifebackend.area.domain

data class Postcode(val value: Int) {
    init {
        require(value in 10000..99999) { "Invalid postcode: $value" }
    }
}