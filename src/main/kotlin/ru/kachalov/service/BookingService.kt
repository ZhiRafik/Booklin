package ru.kachalov.service

import ru.kachalov.dto.request.BookingRequest
import ru.kachalov.dto.response.BookingResponse

interface BookingService {
    fun createBooking(request: BookingRequest): BookingResponse
    fun getBookingById(bookingId: Long): BookingResponse
    fun getAllBookings(): List<BookingResponse>
    fun getBookingsByUserId(userId: Long): List<BookingResponse>
    fun getBookingsByResourceId(resourceId: Long): List<BookingResponse>
    fun getBookingsByStatus(status: String): List<BookingResponse>
    fun confirmBooking(bookingId: Long, actingUserId: Long): BookingResponse
    fun cancelBooking(bookingId: Long, actingUserId: Long): BookingResponse
}