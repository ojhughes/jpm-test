package com.jpmorgan.hotel.service;

import com.jpmorgan.hotel.domain.Facilities;
import com.jpmorgan.hotel.domain.GreatViewRoom;
import com.jpmorgan.hotel.domain.StandardRoom;
import com.jpmorgan.hotel.domain.SuiteRoom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * Class is responsible for calculating the price of a room
 * based on the type of room, and facilities booked along side
 * the room. A visitor pattern is employed to separate price calculation
 * logic from the encapsulation of a Room and a Booking
 */
public class PricingVisitorImpl implements PricingVisitor {
    @Override
    public BigDecimal visit(StandardRoom room, Set<Facilities> facilities) {
        BigDecimal price = new BigDecimal(room.getBasePrice());
        List<Facilities> facilitiesList = new ArrayList<Facilities>(facilities);
        for(ListIterator<Facilities> iter = facilitiesList.listIterator(); iter.hasNext();){
            if(iter.nextIndex() < 3){
                price =  price.add(new BigDecimal(3.00));
            }
            else {
                price = price.add(new BigDecimal(6.00));
            }
            iter.next();
        }

        return price;
    }

    @Override
    public BigDecimal visit(SuiteRoom room, Set<Facilities> facilities) {
        BigDecimal price = new BigDecimal(0.00);

        for (Facilities facility : facilities) {
            price = price.add(facility.getBasePrice());
        }
        return price;
    }

    @Override
    public BigDecimal visit(GreatViewRoom room, Set<Facilities> facilities) {
        BigDecimal price = new BigDecimal(0.00);
        for (Facilities facility : facilities) {
            price = price.add(facility.getBasePrice());
        }
        return price.multiply(new BigDecimal(2.00));
    }
}
