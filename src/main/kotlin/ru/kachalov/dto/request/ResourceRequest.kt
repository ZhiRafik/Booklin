package ru.kachalov.dto.request

import ru.kachalov.model.enums.ResourceType

data class ResourceRequest(
    val name: String,
    val description: String?,
    val type: ResourceType
)
