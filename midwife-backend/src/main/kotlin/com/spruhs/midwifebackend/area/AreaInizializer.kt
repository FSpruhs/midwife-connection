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
        if (repository.findAll().isEmpty()) fillWithDefaultAreas() else catalog.loadAreas()
    }

    private fun fillWithDefaultAreas() {
        catalog.addAllAreas(createDefaultAreas())
    }

    private fun createDefaultAreas(): Set<Area> {
        return setOf(
            Area(Postcode(50667), "Innenstadt", "Köln"),
            Area(Postcode(50823), "Ehrenfeld", "Köln"),
            Area(Postcode(51103), "Deutz", "Köln"),
            Area(Postcode(50668), "Neustadt-Nord", "Köln"),
            Area(Postcode(50678), "Altstadt-Süd", "Köln"),
            Area(Postcode(50825), "Nippes", "Köln"),
            Area(Postcode(51109), "Porz", "Köln"),
            Area(Postcode(50968), "Sülz", "Köln"),
            Area(Postcode(50674), "Rodenkirchen", "Köln"),
        )
    }
}