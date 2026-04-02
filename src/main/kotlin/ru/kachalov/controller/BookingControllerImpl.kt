package ru.kachalov.controller

import org.springframework.web.bind.annotation.RestController
import ru.kachalov.dto.request.BookingRequest
import ru.kachalov.dto.response.BookingResponse
import ru.kachalov.service.BookingService

@RestController
class BookingControllerImpl(
    private val bookingService: BookingService
) : BookingController {

    override fun createBooking(request: BookingRequest): BookingResponse {
        return bookingService.createBooking(request)
    }

    override fun getBookingById(bookingId: Long): BookingResponse {
        return bookingService.getBookingById(bookingId)
    }

    override fun getAllBookings(): List<BookingResponse> {
        return bookingService.getAllBookings()
    }

    override fun getBookingsByUserId(userId: Long): List<BookingResponse> {
        return bookingService.getBookingsByUserId(userId)
    }

    override fun getBookingsByResourceId(resourceId: Long): List<BookingResponse> {
        return bookingService.getBookingsByResourceId(resourceId)
    }

    override fun getBookingsByStatus(status: String): List<BookingResponse> {
        return bookingService.getBookingsByStatus(status)
    }

    override fun confirmBooking(bookingId: Long, actingUserId: Long): BookingResponse {
        return bookingService.confirmBooking(bookingId, actingUserId)
    }

    override fun cancelBooking(bookingId: Long, actingUserId: Long): BookingResponse {
        return bookingService.cancelBooking(bookingId, actingUserId)
    }
}