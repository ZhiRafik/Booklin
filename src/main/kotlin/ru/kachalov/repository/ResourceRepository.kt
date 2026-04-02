package ru.kachalov.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.kachalov.model.Resource

interface ResourceRepository : JpaRepository<Resource, Long>