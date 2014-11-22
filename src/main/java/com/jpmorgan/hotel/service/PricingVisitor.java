package com.jpmorgan.hotel.service;

import com.jpmorgan.hotel.domain.Facilities;
import com.jpmorgan.hotel.domain.GreatViewRoom;
import com.jpmorgan.hotel.domain.StandardRoom;
import com.jpmorgan.hotel.domain.SuiteRoom;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by oh on 22/11/14.
 */
public interface PricingVisitor {
    BigDecimal visit(StandardRoom room, Set<Facilities> facilities);
    BigDecimal visit(SuiteRoom room, Set<Facilities> facilities);
    BigDecimal visit(GreatViewRoom room, Set<Facilities> facilities);
}
