package com.jpmorgan.hotel.domain;

import com.jpmorgan.hotel.service.PricingVisitor;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by oh on 22/11/14.
 */
public interface Room {
    String getRoomNumber();
    Set<Facilities> getBookedFacilities();
    void addFacility(Facilities facility);
    BigDecimal calculatePrice(PricingVisitor visitor);
}
