package com.spruhs.midwifebackend.midwife.adapter.listener

import com.spruhs.midwifebackend.area.domain.events.AreaDeleted
import com.spruhs.midwifebackend.midwife.application.DeleteAreaUseCase
import org.springframework.context.event.EventListener

class AreaDeletedListener(
    private val deleteAreaUseCase: DeleteAreaUseCase,
) {

    @EventListener(AreaDeleted::class)
    fun onEvent(event: AreaDeleted) {
        deleteAreaUseCase.delete(event.postcode)
    }

}