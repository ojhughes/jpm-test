package com.jpmorgan.hotel.service;

import com.jpmorgan.hotel.domain.GreatViewRoom;
import com.jpmorgan.hotel.domain.StandardRoom;
import static com.jpmorgan.hotel.domain.Facilities.*;

import com.jpmorgan.hotel.domain.SuiteRoom;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RoomBookingServiceTest {

    @Test
    public void testQuoteStandardThreeItemsRoom() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(9.00);
        StandardRoom stdRoom = new StandardRoom("101");
        RoomBookingService bookingService = new RoomBookingService();
        bookingService.addRoom(stdRoom);

        stdRoom.addFacility(SWIMMING_POOL);
        stdRoom.addFacility(INTERNET);
        stdRoom.addFacility(ROOM_BREAKFAST);

        assertThat(bookingService.quoteRoom(stdRoom),is(equalTo(expectedPrice)));

    }
    @Test
    public void testQuoteStandardFiveItemsRoom() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(21.00);
        StandardRoom stdRoom = new StandardRoom("101");
        RoomBookingService bookingService = new RoomBookingService();
        bookingService.addRoom(stdRoom);

        stdRoom.addFacility(SWIMMING_POOL);
        stdRoom.addFacility(INTERNET);
        stdRoom.addFacility(ROOM_BREAKFAST);
        stdRoom.addFacility(LATE_CHECKOUT);
        stdRoom.addFacility(ENSUITE_BATHROOM);

        assertThat(bookingService.quoteRoom(stdRoom),is(equalTo(expectedPrice)));

    }
    @Test
    public void testQuoteSuiteRoom() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(5.00);
        SuiteRoom suiteRoom = new SuiteRoom("101");
        RoomBookingService bookingService = new RoomBookingService();
        bookingService.addRoom(suiteRoom);
        suiteRoom.addFacility(SWIMMING_POOL);
        suiteRoom.addFacility(ENSUITE_BATHROOM);
        assertThat(bookingService.quoteRoom(suiteRoom),is(equalTo(expectedPrice)));

    }

    @Test
    public void testQuoteGreatViewRoom() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(10.00);
        GreatViewRoom greatRoom = new GreatViewRoom("101");
        RoomBookingService bookingService = new RoomBookingService();
        bookingService.addRoom(greatRoom);
        greatRoom.addFacility(SWIMMING_POOL);
        greatRoom.addFacility(ENSUITE_BATHROOM);
        assertThat(bookingService.quoteRoom(greatRoom),is(equalTo(expectedPrice)));
    }
}