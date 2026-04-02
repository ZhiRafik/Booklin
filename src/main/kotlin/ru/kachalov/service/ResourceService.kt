package ru.kachalov.service

import ru.kachalov.dto.request.ResourceRequest
import ru.kachalov.dto.response.ResourceResponse

interface ResourceService {
    fun createResource(request: ResourceRequest, userId: Long): ResourceResponse
    fun getResourceById(id: Long): ResourceResponse
    fun getAllResources(): List<ResourceResponse>
    fun updateResource(id: Long, request: ResourceRequest): ResourceResponse
    fun deleteResource(id: Long)
}