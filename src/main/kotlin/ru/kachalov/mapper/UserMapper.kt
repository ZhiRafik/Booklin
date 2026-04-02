package ru.kachalov.mapper

import ru.kachalov.dto.response.UserResponse
import ru.kachalov.model.User

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = this.id ?: throw IllegalStateException("User id must not be null"),
        email = this.email,
        name = this.name,
        role = this.role
    )
}