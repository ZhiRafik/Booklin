package ru.kachalov.service

import org.springframework.stereotype.Service
import ru.kachalov.dto.request.UserRequest
import ru.kachalov.dto.response.UserResponse
import ru.kachalov.mapper.toResponse
import ru.kachalov.model.User

@Service
class UserServiceImpl: UserService {
    private var users = mutableListOf<User>()
    private var nextId = 1L

    override fun createUser(request: UserRequest): UserResponse {
        val user = User(
            id = nextId++,
            email = request.email,
            name = request.name,
            role = request.role
        )
        users.add(user)
        return user.toResponse()
    }

    override fun getUserById(id: Long): UserResponse {
        val user = users.find { it.id == id }
            ?: throw NoSuchElementException("User with id=$id not found")
        return user.toResponse()
    }

    override fun getAllUsers(): List<UserResponse> {
        return users.map { it.toResponse() }
    }

    override fun updateUser(id: Long, request: UserRequest): UserResponse {
        val index = users.indexOfFirst { it.id == id }
        if (index == -1) {
            throw NoSuchElementException("User with id=$id not found")
        }

        val updatedUser = User(
            id = id,
            email = request.email,
            name = request.name,
            role = request.role
        )

        users[index] = updatedUser
        return updatedUser.toResponse()
    }

    override fun deleteUser(id: Long) {
        val removed = users.removeIf { it.id == id }
        if (!removed) {
            throw NoSuchElementException("User with id=$id not found")
        }
    }
}