package ru.kachalov.service

import ru.kachalov.dto.request.UserRequest
import ru.kachalov.dto.response.UserResponse

interface UserService {
    fun createUser(request: UserRequest): UserResponse
    fun getUserById(id: Long): UserResponse
    fun getAllUsers(): List<UserResponse>
    fun updateUser(id: Long, request: UserRequest): UserResponse
    fun deleteUser(id: Long)
}