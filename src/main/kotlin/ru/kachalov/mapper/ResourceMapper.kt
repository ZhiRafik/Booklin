package ru.kachalov.mapper

import ru.kachalov.dto.response.ResourceResponse
import ru.kachalov.model.Resource

fun Resource.toResponse(): ResourceResponse {
    return ResourceResponse(
        id = id ?: throw IllegalStateException("Resource id must not be null"),
        name = name,
        description = description,
        type = type
    )
}