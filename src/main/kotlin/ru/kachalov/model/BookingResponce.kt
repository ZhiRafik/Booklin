package ru.kachalov.model

import ru.kachalov.model.enums.BookingStatus
import java.time.LocalDateTime

data class BookingResponse(
    val id: Long,
    val resourceId: Long,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val status: BookingStatus
)
