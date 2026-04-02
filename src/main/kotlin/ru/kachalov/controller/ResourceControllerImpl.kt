package ru.kachalov.controller

import org.springframework.web.bind.annotation.RestController
import ru.kachalov.dto.request.ResourceRequest
import ru.kachalov.dto.response.ResourceResponse
import ru.kachalov.service.ResourceService

@RestController
open class ResourceControllerImpl(
    private val resourceService: ResourceService
) : ResourceController {

    override fun createResource(request: ResourceRequest, userId: Long): ResourceResponse {
        return resourceService.createResource(request, userId)
    }

    override fun getResourceById(id: Long): ResourceResponse {
        return resourceService.getResourceById(id)
    }

    override fun getAllResources(): List<ResourceResponse> {
        return resourceService.getAllResources()
    }

    override fun updateResource(id: Long, request: ResourceRequest): ResourceResponse {
        return resourceService.updateResource(id, request)
    }

    override fun deleteResource(id: Long) {
        resourceService.deleteResource(id)
    }
}