package ru.kachalov.model

import ru.kachalov.model.enums.BookingStatus
import java.time.LocalDateTime

data class Booking(
    val id: Long? = null,
    val userId: Long,
    val resourceId: Long,
    val startTime: LocalDateTime,
    val endtime: LocalDateTime,
    val status: BookingStatus
)
