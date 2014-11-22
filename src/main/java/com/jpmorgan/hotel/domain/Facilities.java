package com.jpmorgan.hotel.domain;

import java.math.BigDecimal;

/**
 * Created by oh on 22/11/14.
 */
public enum Facilities {
    SWIMMING_POOL(new BigDecimal(1.00)),
    ENSUITE_BATHROOM(new BigDecimal(4.00)),
    LATE_CHECKOUT(new BigDecimal(1.00)),
    INTERNET(new BigDecimal(2.00)),
    ROOM_BREAKFAST(new BigDecimal(5.00));

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    private BigDecimal basePrice;

    private Facilities(BigDecimal basePrice){
        this.basePrice = basePrice;
    }

}
