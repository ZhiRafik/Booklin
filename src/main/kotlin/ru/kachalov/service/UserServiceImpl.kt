package ru.kachalov.service

import org.springframework.stereotype.Service
import ru.kachalov.dto.request.UserRequest
import ru.kachalov.dto.response.UserResponse
import ru.kachalov.mapper.toResponse
import ru.kachalov.model.User
import ru.kachalov.repository.UserRepository

@Service
class UserServiceImpl (
    private val userRepository: UserRepository
) : UserService {

    override fun createUser(request: UserRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("User with email=${request.email} already exists")
        }

        val user = User(
            email = request.email,
            name = request.name,
            role = request.role
        )
        val savedUser = userRepository.save(user)

        return savedUser.toResponse()
    }

    override fun getUserById(id: Long): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User with id=$id not found") }

        return user.toResponse()
    }

    override fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { it.toResponse() }
    }

    override fun updateUser(id: Long, request: UserRequest): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User with id={$id} not found") }

        user.email = request.email
        user.role = request.role
        user.name = request.name

        val updatedUser = userRepository.save(user)
        return updatedUser.toResponse()
    }

    override fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw NoSuchElementException("User with id=$id not found")
        }

        userRepository.deleteById(id)
    }
}