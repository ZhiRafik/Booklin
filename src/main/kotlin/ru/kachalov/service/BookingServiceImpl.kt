package ru.kachalov.service

import org.springframework.stereotype.Service
import ru.kachalov.dto.request.BookingRequest
import ru.kachalov.dto.response.BookingResponse
import ru.kachalov.mapper.toResponse
import ru.kachalov.model.Booking
import ru.kachalov.model.enums.BookingStatus
import ru.kachalov.model.enums.Role
import ru.kachalov.repository.BookingRepository
import ru.kachalov.repository.ResourceRepository
import ru.kachalov.repository.UserRepository

@Service
class BookingServiceImpl(
    private val bookingRepository: BookingRepository,
    private val userRepository: UserRepository,
    private val resourceRepository: ResourceRepository
) : BookingService {

    override fun createBooking(request: BookingRequest): BookingResponse {
        if (request.endTime <= request.startTime) {
            throw IllegalArgumentException("End time must be after start time")
        }

        val user = userRepository.findById(request.userId)
            .orElseThrow { NoSuchElementException("User with id=${request.userId} not found") }

        val resource = resourceRepository.findById(request.resourceId)
            .orElseThrow { NoSuchElementException("Resource with id=${request.resourceId} not found") }

        val booking = Booking(
            user = user,
            resource = resource,
            startTime = request.startTime,
            endTime = request.endTime,
            status = BookingStatus.PENDING
        )

        return bookingRepository.save(booking).toResponse()
    }

    override fun getBookingById(bookingId: Long): BookingResponse {
        val booking = bookingRepository.findById(bookingId)
            .orElseThrow { NoSuchElementException("Booking with id=$bookingId not found") }

        return booking.toResponse()
    }

    override fun getAllBookings(): List<BookingResponse> {
        return bookingRepository.findAll().map { it.toResponse() }
    }

    override fun getBookingsByUserId(userId: Long): List<BookingResponse> {
        if (!userRepository.existsById(userId)) {
            throw NoSuchElementException("User with id=$userId not found")
        }

        return bookingRepository.findAllByUser_Id(userId).map { it.toResponse() }
    }

    override fun getBookingsByResourceId(resourceId: Long): List<BookingResponse> {
        if (!resourceRepository.existsById(resourceId)) {
            throw NoSuchElementException("Resource with id=$resourceId not found")
        }

        return bookingRepository.findAllByResource_Id(resourceId).map { it.toResponse() }
    }

    override fun getBookingsByStatus(status: String): List<BookingResponse> {
        val bookingStatus = try {
            BookingStatus.valueOf(status.uppercase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Unknown booking status: $status")
        }

        return bookingRepository.findAllByStatus(bookingStatus).map { it.toResponse() }
    }

    override fun confirmBooking(bookingId: Long, actingUserId: Long): BookingResponse {
        val booking = bookingRepository.findById(bookingId)
            .orElseThrow { NoSuchElementException("Booking with id=$bookingId not found") }

        val actingUser = userRepository.findById(actingUserId)
            .orElseThrow { NoSuchElementException("User with id=$actingUserId not found") }

        if (actingUser.role != Role.ADMIN) {
            throw IllegalStateException("Only admin can confirm booking")
        }

        if (booking.status != BookingStatus.PENDING) {
            throw IllegalStateException("Only pending booking can be confirmed")
        }

        val resourceId = booking.resource.id
            ?: throw IllegalStateException("Resource id must not be null")

        val hasConflict =
            bookingRepository.existsByResource_IdAndStatusInAndStartTimeLessThanAndEndTimeGreaterThan(
                resourceId = resourceId,
                statuses = listOf(BookingStatus.CONFIRMED),
                endTime = booking.endTime,
                startTime = booking.startTime
            )

        if (hasConflict) {
            throw IllegalStateException("Resource is already booked for this time")
        }

        booking.status = BookingStatus.CONFIRMED

        return bookingRepository.save(booking).toResponse()
    }

    override fun cancelBooking(bookingId: Long, actingUserId: Long): BookingResponse {
        val booking = bookingRepository.findById(bookingId)
            .orElseThrow { NoSuchElementException("Booking with id=$bookingId not found") }

        val actingUser = userRepository.findById(actingUserId)
            .orElseThrow { NoSuchElementException("User with id=$actingUserId not found") }

        val bookingOwnerId = booking.user.id
            ?: throw IllegalStateException("Booking user id must not be null")

        val isOwner = bookingOwnerId == actingUserId
        val isAdmin = actingUser.role == Role.ADMIN

        if (!isOwner && !isAdmin) {
            throw IllegalStateException("Only booking owner or admin can cancel booking")
        }

        if (booking.status == BookingStatus.CANCELLED) {
            throw IllegalStateException("Booking is already cancelled")
        }

        booking.status = BookingStatus.CANCELLED

        return bookingRepository.save(booking).toResponse()
    }
}