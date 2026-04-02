package ru.kachalov.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.kachalov.dto.request.ResourceRequest
import ru.kachalov.dto.response.ResourceResponse

@RequestMapping("/resources")
interface ResourceController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createResource(@RequestBody request: ResourceRequest, userId: Long): ResourceResponse

    @GetMapping("/{id}")
    fun getResourceById(@PathVariable id: Long): ResourceResponse

    @GetMapping
    fun getAllResources(): List<ResourceResponse>

    @PutMapping("/{id}")
    fun updateResource(
        @PathVariable id: Long,
        @RequestBody request: ResourceRequest
    ): ResourceResponse

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteResource(@PathVariable id: Long)
}