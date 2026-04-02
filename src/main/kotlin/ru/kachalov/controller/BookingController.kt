package ru.kachalov.controller

import org.springframework.web.bind.annotation.*
import ru.kachalov.dto.request.BookingRequest
import ru.kachalov.dto.response.BookingResponse

@RequestMapping("/bookings")
interface BookingController {

    @PostMapping
    fun createBooking(@RequestBody request: BookingRequest): BookingResponse

    @GetMapping("/{bookingId}")
    fun getBookingById(@PathVariable bookingId: Long): BookingResponse

    @GetMapping
    fun getAllBookings(): List<BookingResponse>

    @GetMapping("/user/{userId}")
    fun getBookingsByUserId(@PathVariable userId: Long): List<BookingResponse>

    @GetMapping("/resource/{resourceId}")
    fun getBookingsByResourceId(@PathVariable resourceId: Long): List<BookingResponse>

    @GetMapping("/status/{status}")
    fun getBookingsByStatus(@PathVariable status: String): List<BookingResponse>

    @PatchMapping("/{bookingId}/confirm")
    fun confirmBooking(
        @PathVariable bookingId: Long,
        @RequestParam actingUserId: Long
    ): BookingResponse

    @PatchMapping("/{bookingId}/cancel")
    fun cancelBooking(
        @PathVariable bookingId: Long,
        @RequestParam actingUserId: Long
    ): BookingResponse
}