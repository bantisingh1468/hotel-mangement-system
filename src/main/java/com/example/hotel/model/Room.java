package com.example.hotel.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Room {
    private String id;
    private String name;
    private String type;
    private BigDecimal nightlyRate;
    private boolean available;

    public Room() {
    }

    public Room(String id, String name, String type, BigDecimal nightlyRate, boolean available) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.nightlyRate = nightlyRate;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getNightlyRate() {
        return nightlyRate;
    }

    public void setNightlyRate(BigDecimal nightlyRate) {
        this.nightlyRate = nightlyRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

