package ru.kachalov.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.kachalov.model.Booking
import ru.kachalov.model.enums.BookingStatus
import java.time.LocalDateTime

interface BookingRepository: JpaRepository<Booking, Long> {
    fun findAllByUser_Id(userId: Long): List<Booking>

    fun findAllByResource_Id(resourceId: Long): List<Booking>

    fun findAllByStatus(status: BookingStatus): List<Booking>

    fun existsByResource_IdAndStatusInAndStartTimeLessThanAndEndTimeGreaterThan(
        resourceId: Long,
        statuses: List<BookingStatus>,
        endTime: LocalDateTime,
        startTime: LocalDateTime
    ): Boolean
}