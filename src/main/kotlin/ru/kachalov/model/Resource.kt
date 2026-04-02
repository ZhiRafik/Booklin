package ru.kachalov.model

import ru.kachalov.model.enums.ResourceType

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "resources")
class Resource(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column
    var description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: ResourceType
)
