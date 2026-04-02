package ru.kachalov.dto.request

import ru.kachalov.model.enums.Role

data class UserRequest(
    val email: String,
    val name: String,
    val role: Role = Role.USER
)
