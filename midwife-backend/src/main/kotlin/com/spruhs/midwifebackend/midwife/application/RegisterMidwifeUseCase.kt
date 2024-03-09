package com.spruhs.midwifebackend.midwife.application

import com.spruhs.midwifebackend.midwife.application.ports.MidwifeRepository
import com.spruhs.midwifebackend.midwife.domain.Midwife
import org.springframework.stereotype.Component
import java.util.*

@Component
class RegisterMidwifeUseCase(
    val repository: MidwifeRepository
) {

    fun register(registerMidwifeCommand: RegisterMidwifeCommand): Midwife {
        return repository.save(Midwife(
            id = UUID.randomUUID(),
            firstName = registerMidwifeCommand.firstName,
            lastName = registerMidwifeCommand.lastName
        ))
    }

}

data class RegisterMidwifeCommand(
    val firstName: String,
    val lastName: String
)