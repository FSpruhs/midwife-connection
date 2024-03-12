package com.spruhs.midwifebackend.area.domain

data class Area(val postcode: Postcode, val name: String) {
    init {
        require(name.isNotBlank()) { "Name cannot be blank" }
    }
}