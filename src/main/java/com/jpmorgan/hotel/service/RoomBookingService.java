package com.jpmorgan.hotel.service;

import com.jpmorgan.hotel.domain.*;

import java.math.BigDecimal;
import java.util.*;

import static java.lang.String.format;

/**
 * Public interface to room booking service
 */
public class RoomBookingService {

    private BookingSchedule schedule = BookingSchedule.INSTANCE;

    /**
     *
     * @param room
     * @return quote
     */
    public <T extends Room> BigDecimal quoteRoom(T room, Set<Facilities> requestedFacilities) {
        Objects.requireNonNull(room, "A room must be provided");

        Quotation quote = new Quotation(room, requestedFacilities, new DefaultPricingCalculator() );
        return quote.getQuote();
    }

    /**
     *
     * @param room
     * @param <T>
     */
    public <T extends Room> void addRoom(T room){
        if(isRoomAvailable(room)){
            throw new IllegalArgumentException("Room has already been added");
        }
        boolean addedSuccessfully = schedule.addRoom(room);
        if(!addedSuccessfully){
            throw new IllegalStateException("Failed to add room");
        }
    }

    /**
     *
     * @param room
     * @param <T>
     * @return
     */
    public  <T extends Room > boolean isRoomAvailable(T room){
        return schedule.isRoomAvailable(room);
    }

    /**
     *
     * @param room
     * @param <T>
     */
    public <T extends Room > void bookRoom(T room){
        Objects.requireNonNull(room, "A room must be provided");
        Objects.requireNonNull(room.getRoomNumber(), "A room number must be provided");

        boolean bookedSuccessfully = schedule.bookRoom(room);
        if(!bookedSuccessfully){
            throw new IllegalStateException("Failed to book room");
        }
    }
}
