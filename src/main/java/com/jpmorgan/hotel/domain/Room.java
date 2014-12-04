package com.jpmorgan.hotel.domain;

import com.jpmorgan.hotel.service.PricingCalculator;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by oh on 22/11/14.
 */
public interface Room {
    Integer getRoomNumber();
    BigDecimal getBasePrice();
    BigDecimal calculatePrice(PricingCalculator visitor, Set<Facilities> facilities);
}
