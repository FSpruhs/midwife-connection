package com.spruhs.midwifebackend.area

import com.spruhs.midwifebackend.area.application.AreaCatalog
import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class AreaInizializer(
    val repository: AreaRepository,
    val catalog: AreaCatalog
) : ApplicationRunner{
    override fun run(args: ApplicationArguments?) {
        if (repository.findAll().isEmpty()) fillWithDefaultAreas()
    }

    fun fillWithDefaultAreas() {
        catalog.addAllAreas(createDefaultAreas())
    }

    fun createDefaultAreas(): Set<Area> {
        return setOf(
            Area(Postcode(1000), "Brussels"),
            Area(Postcode(2000), "Antwerp"),
            Area(Postcode(3000), "Leuven"),
            Area(Postcode(4000), "Liège"),
            Area(Postcode(5000), "Namur"),
            Area(Postcode(6000), "Charleroi"),
            Area(Postcode(7000), "Mons"),
            Area(Postcode(8000), "Bruges"),
            Area(Postcode(9000), "Ghent")
        )
    }
}