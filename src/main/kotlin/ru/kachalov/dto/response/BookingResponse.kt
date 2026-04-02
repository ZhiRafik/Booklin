package ru.kachalov.dto.response

import ru.kachalov.model.enums.BookingStatus
import java.time.LocalDateTime

data class BookingResponse(
    val id: Long,
    val userId: Long,
    val resourceId: Long,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val status: BookingStatus
)