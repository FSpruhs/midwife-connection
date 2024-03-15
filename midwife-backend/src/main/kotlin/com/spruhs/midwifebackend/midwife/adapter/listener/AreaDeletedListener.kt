package com.spruhs.midwifebackend.midwife.adapter.listener

import com.spruhs.midwifebackend.area.domain.events.AreaDeleted
import com.spruhs.midwifebackend.midwife.application.DeleteAreaUseCase
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class AreaDeletedListener(
    private val deleteAreaUseCase: DeleteAreaUseCase,
) {

    private val logger = KotlinLogging.logger { }

    @EventListener(AreaDeleted::class)
    fun onEvent(event: AreaDeleted) {
        logger.debug { "AreaDeletedListener postcode: ${event.postcode}" }
        deleteAreaUseCase.delete(event.postcode)
    }
}