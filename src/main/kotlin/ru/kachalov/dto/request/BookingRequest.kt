package ru.kachalov.dto.request

import java.time.LocalDateTime

data class BookingRequest(
    val resourceId: Long,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)