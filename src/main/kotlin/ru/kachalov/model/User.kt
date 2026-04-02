package ru.kachalov.model

import ru.kachalov.model.enums.Role

data class User(
    val id: Long? = null,
    val email: String,
    val name: String,
    val role: Role = Role.USER
)
