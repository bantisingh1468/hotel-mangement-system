package com.example.hotel.service;

import com.example.hotel.dto.BookingRequest;
import com.example.hotel.dto.GuestRequest;
import com.example.hotel.dto.RoomRequest;
import com.example.hotel.model.Booking;
import com.example.hotel.model.BookingStatus;
import com.example.hotel.model.Guest;
import com.example.hotel.model.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final Map<String, Room> rooms = new ConcurrentHashMap<>();
    private final Map<String, Guest> guests = new ConcurrentHashMap<>();
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    public List<Room> listRooms() {
        return new ArrayList<>(rooms.values());
    }

    public List<Guest> listGuests() {
        return new ArrayList<>(guests.values());
    }

    public List<Booking> listBookings() {
        return new ArrayList<>(bookings.values());
    }

    public Room addRoom(RoomRequest request) {
        Room room = new Room(UUID.randomUUID().toString(), request.getName(), request.getType(), request.getNightlyRate(), true);
        rooms.put(room.getId(), room);
        return room;
    }

    public Guest addGuest(GuestRequest request) {
        Guest guest = new Guest(UUID.randomUUID().toString(), request.getName(), request.getEmail(), request.getPhone());
        guests.put(guest.getId(), guest);
        return guest;
    }

    public Booking createBooking(BookingRequest request) {
        Room room = rooms.get(request.getRoomId());
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        Guest guest = guests.get(request.getGuestId());
        if (guest == null) {
            throw new IllegalArgumentException("Guest not found");
        }
        validateDates(request.getCheckIn(), request.getCheckOut());
        ensureRoomAvailable(room.getId(), request.getCheckIn(), request.getCheckOut());

        Booking booking = new Booking(UUID.randomUUID().toString(), room.getId(), guest.getId(), request.getCheckIn(), request.getCheckOut(), BookingStatus.CONFIRMED);
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public Booking cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        bookings.put(booking.getId(), booking);
        return booking;
    }

    private void validateDates(LocalDate checkIn, LocalDate checkOut) {
        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            throw new IllegalArgumentException("Check-out must be after check-in");
        }
    }

    private void ensureRoomAvailable(String roomId, LocalDate checkIn, LocalDate checkOut) {
        List<Booking> activeBookings = bookings.values().stream()
                .filter(b -> b.getRoomId().equals(roomId))
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED || b.getStatus() == BookingStatus.PENDING)
                .collect(Collectors.toList());

        boolean overlaps = activeBookings.stream().anyMatch(existing ->
                !checkOut.isBefore(existing.getCheckIn()) && !checkIn.isAfter(existing.getCheckOut().minusDays(1)));

        if (overlaps) {
            throw new IllegalArgumentException("Room is not available for the selected dates");
        }
    }
}

