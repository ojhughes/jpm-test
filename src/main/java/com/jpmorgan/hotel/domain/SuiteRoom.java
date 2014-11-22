package com.jpmorgan.hotel.domain;

import com.jpmorgan.hotel.service.PricingVisitor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * SuiteRoom
 */
public class SuiteRoom implements Room{

    /**
     * roomNumber
     */
    private String roomNumber;
    private Set<Facilities> bookedFacilities = new HashSet<Facilities>();

    /**
     *
     * @param id
     */
    public SuiteRoom(String id) {
        this.roomNumber = id;
    }

    /**
     *
     * @return
     */
    @Override
    public String getRoomNumber() {
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
        final SuiteRoom other = (SuiteRoom) obj;
        return Objects.equals(this.roomNumber, other.roomNumber);
    }
}
