package ru.kachalov.dto.response

import ru.kachalov.model.enums.Role

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val role: Role
)
