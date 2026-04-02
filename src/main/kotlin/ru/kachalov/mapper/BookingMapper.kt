package ru.kachalov.mapper

import ru.kachalov.dto.response.BookingResponse
import ru.kachalov.model.Booking

fun Booking.toResponse(): BookingResponse {
    return BookingResponse(
        id = this.id ?: throw IllegalStateException("Booking id must not be null"),
        userId = this.user.id ?: throw IllegalStateException("User id must not be null"),
        resourceId = this.resource.id ?: throw IllegalStateException("Resource id must not be null"),
        startTime = this.startTime,
        endTime = this.endTime,
        status = this.status
    )
}