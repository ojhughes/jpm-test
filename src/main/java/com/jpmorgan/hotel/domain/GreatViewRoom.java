package com.jpmorgan.hotel.domain;

import com.jpmorgan.hotel.service.PricingVisitor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * GVR
 */
public class GreatViewRoom implements Room{
    /**
     * roomNumber
     */
    private String roomNumber;
    private int basePrice = 0;
    private Set<Facilities> bookedFacilities = new HashSet<Facilities>();

    public GreatViewRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    /**
     *
     * @param roomNumber
     */
    public GreatViewRoom(String roomNumber, int basePrice) {
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
    }

    /**
     *
     * @return
     */
    @Override
    public String getRoomNumber(){
        return roomNumber;
    }

    @Override
    public Set<Facilities> getBookedFacilities() {
        return bookedFacilities;
    }

    @Override
    public void addFacility(Facilities facility) {
        bookedFacilities.add(facility);
    }

    @Override
    public int getBasePrice() {
        return basePrice;
    }

    @Override
    public BigDecimal calculatePrice(PricingVisitor visitor) {
        return visitor.visit(this,bookedFacilities);
    }
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final GreatViewRoom other = (GreatViewRoom) obj;
        return Objects.equals(this.roomNumber, other.roomNumber);
    }
}
