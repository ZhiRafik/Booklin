package ru.kachalov.model

import ru.kachalov.model.enums.ResourceType

data class Resource(
    val id: Long? = null,
    val name: String,
    val description: String?,
    val type: ResourceType
)
