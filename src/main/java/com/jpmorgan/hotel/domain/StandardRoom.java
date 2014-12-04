package com.jpmorgan.hotel.domain;

import com.jpmorgan.hotel.service.PricingCalculator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * STandardRoovm
 */
public class StandardRoom implements Room{
    /**
     * roomNumber
     */
    private Integer roomNumber;
    private BigDecimal basePrice = new BigDecimal(0);

    public StandardRoom() {
    }

    /**
     *
     * @param roomNumber
     */
    public StandardRoom(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public StandardRoom(Integer roomNumber, BigDecimal basePrice) {
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getRoomNumber(){
        return roomNumber;
    }

    @Override
    public BigDecimal calculatePrice(PricingCalculator visitor, Set<Facilities> bookedFacilities) {
        return visitor.visit(this,bookedFacilities);
    }

    @Override
    public BigDecimal getBasePrice() {
        return basePrice;
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
        final StandardRoom other = (StandardRoom) obj;
        return Objects.equals(this.roomNumber, other.roomNumber);
    }

}
