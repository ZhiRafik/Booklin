package ru.kachalov.service.impl

import org.springframework.stereotype.Service
import ru.kachalov.dto.request.ResourceRequest
import ru.kachalov.dto.response.ResourceResponse
import ru.kachalov.mapper.toResponse
import ru.kachalov.model.Resource
import ru.kachalov.model.enums.Role
import ru.kachalov.repository.ResourceRepository
import ru.kachalov.repository.UserRepository
import ru.kachalov.service.ResourceService

@Service
class ResourceServiceImpl(
    private val resourceRepository: ResourceRepository,
    private val userRepository: UserRepository
) : ResourceService {

    override fun createResource(request: ResourceRequest, userId: Long): ResourceResponse {
        val user = userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User with id=$userId not found") }

        if (user.role != Role.ADMIN) {
            throw IllegalStateException("Only admin can perform this action")
        }

        val resource = Resource(
            name = request.name,
            description = request.description,
            type = request.type
        )
        val savedResource = resourceRepository.save(resource)

        return savedResource.toResponse()
    }

    override fun getResourceById(id: Long): ResourceResponse {
        val resource = resourceRepository.findById(id)
            .orElseThrow { NoSuchElementException("Resource with id=$id not found") }

        return resource.toResponse()
    }

    override fun getAllResources(): List<ResourceResponse> {
        return resourceRepository.findAll().map { it.toResponse() }
    }

    override fun updateResource(id: Long, request: ResourceRequest): ResourceResponse {
        val resource = resourceRepository.findById(id)
            .orElseThrow { NoSuchElementException("Resource with id=$id not found") }

        resource.name = request.name
        resource.description = request.description
        resource.type = request.type

        val updatedResource = resourceRepository.save(resource)
        return updatedResource.toResponse()
    }

    override fun deleteResource(id: Long) {
        if (!resourceRepository.existsById(id)) {
            throw NoSuchElementException("Resource with id=$id not found")
        }

        resourceRepository.deleteById(id)
    }
}