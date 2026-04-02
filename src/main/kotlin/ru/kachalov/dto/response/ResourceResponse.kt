package ru.kachalov.dto.response

import ru.kachalov.model.enums.ResourceType

data class ResourceResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val type: ResourceType
)
