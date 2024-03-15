package com.spruhs.midwifebackend.area.application

import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.AreaNotFoundException
import com.spruhs.midwifebackend.area.domain.Postcode
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationEventPublisher

@ExtendWith(MockKExtension::class)
class AreaCatalogTest {

    @MockK
    lateinit var repository: AreaRepository

    @MockK
    lateinit var eventPublisher: ApplicationEventPublisher

    @InjectMockKs
    lateinit var catalog: AreaCatalog

    @AfterEach
    fun tearDown() {
        catalog.clearAreas()
    }

    @Test
    fun `find area by postcode should find in list`() {
        // Given
        val area = Area(Postcode(50674), "Innenstadt", "Köln")
        every { repository.save(any()) } returns  area
        catalog.addArea(area)

        // When
        val foundArea = catalog.findArea(Postcode(50674))

        // Then
        assertThat(foundArea).isEqualTo(area)
    }

    @Test
    fun `find area by postcode should find in repository`() {
        // Given
        val area = Area(Postcode(50674), "Innenstadt", "Köln")
        every { repository.findByPostcode(Postcode(50674)) } returns area

        // When
        val foundArea = catalog.findArea(Postcode(50674))

        // Then
        assertThat(foundArea).isEqualTo(area)
    }

    @Test
    fun `find area by postcode should throw exception when not found`() {
        // Given
        every { repository.findByPostcode(Postcode(50674)) } returns null

        // When
        assertThatThrownBy { catalog.findArea(Postcode(50674)) }
            // Then
            .isInstanceOf(AreaNotFoundException::class.java)
            .hasMessage("Area with postcode 50674 not found.")
    }

    @Test
    fun `add area should add area`() {
        // Given
        val area = Area(Postcode(50674), "Innenstadt", "Köln")
        every { repository.save(any()) } returns  area

        // When
        catalog.addArea(area)

        // Then
        assertThat(catalog.findArea(Postcode(50674))).isEqualTo(area)
        verify { repository.save(area) }
    }

    @Test
    fun `add area should throw exception when area already exists`() {
        // Given
        val area = Area(Postcode(50674), "Innenstadt", "Köln")
        every { repository.save(any()) } returns  area
        catalog.addArea(area)

        // When
        assertThatThrownBy { catalog.addArea(area) }
            // Then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Area with postcode 50674 already exists.")
    }

    @Test
    fun `add all areas should add all areas`() {
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