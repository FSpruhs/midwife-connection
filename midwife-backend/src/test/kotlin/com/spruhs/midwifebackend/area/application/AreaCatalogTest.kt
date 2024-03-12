package com.spruhs.midwifebackend.area.application

import com.spruhs.midwifebackend.area.application.ports.AreaRepository
import com.spruhs.midwifebackend.area.domain.Area
import com.spruhs.midwifebackend.area.domain.Postcode
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
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
    fun findAreaByPostcode() {

    }

    @Test
    fun addArea_shouldAddArea() {
        // Given
        val area = Area(Postcode(50674), "Köln")

        // When
        catalog.addArea(area)

        // Then
        assertThat(catalog.findAreaByPostcode(Postcode(50674))).isEqualTo(area)
    }

    @Test
    fun addArea_shouldThrowException_whenAreaAlreadyExists() {
        // Given
        val area = Area(Postcode(50674), "Köln")
        catalog.addArea(area)

        // When
        assertThatThrownBy { catalog.addArea(area) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Area with postcode 50674 already exists")
    }
}