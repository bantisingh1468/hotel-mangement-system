package com.example.hotel.config;

import com.example.hotel.dto.GuestRequest;
import com.example.hotel.dto.RoomRequest;
import com.example.hotel.service.HotelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedData(HotelService hotelService) {
        return args -> {
            if (hotelService.listRooms().isEmpty()) {
                hotelService.addRoom(buildRoom("Deluxe 101", "Deluxe", BigDecimal.valueOf(180)));
                hotelService.addRoom(buildRoom("Suite 201", "Suite", BigDecimal.valueOf(250)));
            }
            if (hotelService.listGuests().isEmpty()) {
                hotelService.addGuest(buildGuest("Alex Rivers", "alex@example.com", "+1-555-0100"));
                hotelService.addGuest(buildGuest("Jamie Stone", "jamie@example.com", "+1-555-0101"));
            }
        };
    }

    private RoomRequest buildRoom(String name, String type, BigDecimal rate) {
        RoomRequest request = new RoomRequest();
        request.setName(name);
        request.setType(type);
        request.setNightlyRate(rate);
        return request;
    }

    private GuestRequest buildGuest(String name, String email, String phone) {
        GuestRequest request = new GuestRequest();
        request.setName(name);
        request.setEmail(email);
        request.setPhone(phone);
        return request;
    }
}

