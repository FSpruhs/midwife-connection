package com.spruhs.midwifebackend.area.application

import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AreaCatalogTest {

    @MockK
    lateinit var repository: AreaRepository

    @InjectMockKs
    lateinit var catalog: AreaCatalog

    @AfterEach
    fun tearDown() {
        catalog.clearAreas()
    }

    @Test
    fun findAreaByPostcode_shouldFindInList() {
        // Given
        val area = Area(Postcode(50674), "Innenstadt", "Köln")
        catalog.addArea(area)

        // When
        val foundArea = catalog.findArea(Postcode(50674))

        // Then
        assertThat(foundArea).isEqualTo(area)
    }

    @Test
    fun findAreaByPostcode_shouldFindInRepository() {
        // Given
        val area = Area(Postcode(50674), "Innenstadt", "Köln")
        every { repository.findByPostcode(Postcode(50674)) } returns area

        // When
        val foundArea = catalog.findArea(Postcode(50674))

        // Then
        assertThat(foundArea).isEqualTo(area)
    }

    @Test
    fun findAreaByPostcode_shouldThrowException_whenNotFound() {
        // Given
        every { repository.findByPostcode(Postcode(50674)) } returns null

        // When
        assertThatThrownBy { catalog.findArea(Postcode(50674)) }
            // Then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Area with postcode 50674 not found.")
    }

    @Test
    fun addArea_shouldAddArea() {
        // Given
        val area = Area(Postcode(50674), "Innenstadt", "Köln")

        // When
        catalog.addArea(area)

        // Then
        assertThat(catalog.findArea(Postcode(50674))).isEqualTo(area)
    }

    @Test
    fun addArea_shouldThrowException_whenAreaAlreadyExists() {
        // Given
        val area = Area(Postcode(50674), "Innenstadt", "Köln")
        catalog.addArea(area)

        // When
        assertThatThrownBy { catalog.addArea(area) }
            // Then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Area with postcode 50674 already exists.")
    }

    @Test
    fun addAllAreas_shouldAddAllAreas() {
        // Given
        val areas = setOf(
            Area(Postcode(50674), "Innenstadt", "Köln"),
            Area(Postcode(50676), "Innenstadt", "Köln")
        )
        every { repository.saveAll(any()) } just Runs

        // When
        catalog.addAllAreas(areas)

        // Then
        assertThat(catalog.findArea(Postcode(50674))).isEqualTo(areas.first())
        assertThat(catalog.findArea(Postcode(50676))).isEqualTo(areas.last())
        verify { repository.saveAll(areas) }
    }

}