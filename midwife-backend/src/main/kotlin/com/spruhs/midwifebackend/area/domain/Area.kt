package com.spruhs.midwifebackend.area.domain

data class Area(
    val postcode: Postcode,
    val district: String,
    val city: String

) {
    init {
        require(district.isNotBlank()) { "District cannot be blank" }
        require(city.isNotBlank()) { "City cannot be blank" }
    }
}