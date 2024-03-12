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

    private fun fillWithDefaultAreas() {
        catalog.addAllAreas(createDefaultAreas())
    }

    private fun createDefaultAreas(): Set<Area> {
        return setOf(
            Area(Postcode(1000), "Innenstadt", "Brussels"),
            Area(Postcode(2000), "Innenstadt", "Antwerp"),
            Area(Postcode(3000), "Innenstadt", "Leuven"),
            Area(Postcode(4000), "Innenstadt", "Liège"),
            Area(Postcode(5000), "Innenstadt", "Namur"),
            Area(Postcode(6000), "Innenstadt", "Charleroi"),
            Area(Postcode(7000), "Innenstadt", "Mons"),
            Area(Postcode(8000), "Innenstadt", "Bruges"),
            Area(Postcode(9000), "Innenstadt", "Ghent")
        )
    }
}