package ru.kachalov.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.kachalov.model.User

interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
}