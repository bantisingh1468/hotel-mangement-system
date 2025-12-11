package com.example.hotel.controller;

import com.example.hotel.dto.BookingRequest;
import com.example.hotel.dto.GuestRequest;
import com.example.hotel.dto.RoomRequest;
import com.example.hotel.model.Booking;
import com.example.hotel.model.Guest;
import com.example.hotel.model.Room;
import com.example.hotel.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/rooms")
    public List<Room> listRooms() {
        return hotelService.listRooms();
    }

    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public Room addRoom(@Valid @RequestBody RoomRequest request) {
        return hotelService.addRoom(request);
    }

    @GetMapping("/guests")
    public List<Guest> listGuests() {
        return hotelService.listGuests();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addGuest(@Valid @RequestBody GuestRequest request) {
        return hotelService.addGuest(request);
    }

    @GetMapping("/bookings")
    public List<Booking> listBookings() {
        return hotelService.listBookings();
    }

    @PostMapping("/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@Valid @RequestBody BookingRequest request) {
        return hotelService.createBooking(request);
    }

    @PostMapping("/bookings/{bookingId}/cancel")
    public Booking cancelBooking(@PathVariable String bookingId) {
        return hotelService.cancelBooking(bookingId);
    }
}

