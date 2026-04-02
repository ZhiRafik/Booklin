package ru.kachalov.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import ru.kachalov.dto.request.UserRequest
import ru.kachalov.dto.response.UserResponse

@RequestMapping("/users")
interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody request: UserRequest): UserResponse

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserResponse

    @GetMapping
    fun getAllUsers(): List<UserResponse>

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UserRequest
    ): UserResponse

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long)
}