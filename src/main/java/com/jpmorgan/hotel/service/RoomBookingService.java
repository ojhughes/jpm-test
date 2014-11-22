package com.jpmorgan.hotel.service;

import com.jpmorgan.hotel.domain.Facilities;
import com.jpmorgan.hotel.domain.Room;

import java.math.BigDecimal;
import java.util.*;

/**
 * Public interface to room booking service
 */
public class RoomBookingService {
    /**
     * availableRooms
     */
    private Set<Room> availableRooms = new HashSet<Room>();

    /**
     *
     * @param room
     * @return
     */
    public <T extends Room> BigDecimal quoteRoom(T room) {
        Objects.requireNonNull(room, "A room must be provided");

        PricingVisitor priceCalculator = new PricingVisitorImpl();
        return room.calculatePrice(priceCalculator);
    }

    /**
     *
     * @return
     */
    public Set<Room> getAvailableRooms() {
        return availableRooms;
    }

    /**
     *
     * @param room
     * @param <T>
     */
    public <T extends Room> void addRoom(T room){
        if(availableRooms.contains(room)){
            throw new IllegalArgumentException("Room has already been added");
        }
        availableRooms.add(room);
    }

    /**
     *
     * @param room
     * @param <T>
     * @return
     */
    public  <T extends Room > boolean roomIsAvailable(T room){
        return availableRooms.contains(room);
    }

    /**
     *
     * @param room
     * @param <T>
     */
    public <T extends Room > void bookRoom(T room){
        Objects.requireNonNull(room, "A room must be provided");
        if(!roomIsAvailable(room)){
            throw new IllegalArgumentException("Room has been booked");
        };
        availableRooms.remove(room);
    }

}
