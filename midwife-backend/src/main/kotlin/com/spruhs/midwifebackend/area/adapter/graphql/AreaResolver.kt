package com.spruhs.midwifebackend.area.adapter.graphql

import com.spruhs.midwifebackend.area.application.AreaCatalog
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class AreaResolver(val areaCatalog: AreaCatalog) {

    @QueryMapping
    fun getAreas(): List<AreaDto> {
        return areaCatalog.getAreas().map(::toDto)
    }

    @MutationMapping
    fun createArea(
        @Argument postcode: Int,
        @Argument district: String,
        @Argument city: String
    ): AreaDto {
        return areaCatalog.addArea(
            Area(
                postcode = Postcode(postcode),
                district = district,
                city = city)
        ).let(::toDto)
    }

    @MutationMapping
    fun deleteArea(
        @Argument postcode: Int
    ): Boolean {
        return areaCatalog.deleteArea(
            Postcode(postcode)
        )
    }

    @MutationMapping
    fun updateArea(
        @Argument postcode: Int,
        @Argument district: String,
        @Argument city: String
    ): AreaDto {
        return areaCatalog.updateArea(
            Area(
                postcode = Postcode(postcode),
                district = district,
                city = city)
        ).let(::toDto)
    }

}

private fun toDto(area: Area): AreaDto {
    return AreaDto(
        postcode = area.postcode.value,
        district = area.district,
        city = area.city
    )
}

data class AreaDto(
    val postcode: Int,
    val district: String,
    val city: String
)