package ru.kachalov.controller

import org.springframework.web.bind.annotation.RestController
import ru.kachalov.dto.request.UserRequest
import ru.kachalov.dto.response.UserResponse
import ru.kachalov.service.UserService

@RestController
class UserControllerImpl (private val userService: UserService) : UserController {

    override fun createUser(request: UserRequest): UserResponse {
        return userService.createUser(request)
    }

    override fun getUserById(id: Long): UserResponse {
        return userService.getUserById(id)
    }

    override fun getAllUsers(): List<UserResponse> {
        return userService.getAllUsers()
    }

    override fun updateUser(id: Long, request: UserRequest): UserResponse {
        return userService.updateUser(id, request)
    }

    override fun deleteUser(id: Long) {
        userService.deleteUser(id)
    }
}