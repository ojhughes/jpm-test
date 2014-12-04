package com.jpmorgan.hotel.service;

import com.jpmorgan.hotel.domain.*;

import static com.jpmorgan.hotel.domain.Facilities.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RoomBookingServiceTest {

    private RoomBookingService bookingService = new RoomBookingService();
    private BookingSchedule schedule = BookingSchedule.INSTANCE;

    @BeforeClass
    public static void loadInitialRooms(){
        BookingSchedule.INSTANCE.addRoom(new GreatViewRoom(101));
        BookingSchedule.INSTANCE.addRoom(new StandardRoom(102));
        BookingSchedule.INSTANCE.addRoom(new SuiteRoom(103));
        BookingSchedule.INSTANCE.addRoom(new StandardRoom(104));
        BookingSchedule.INSTANCE.addRoom(new GreatViewRoom(105));
        BookingSchedule.INSTANCE.addRoom(new SuiteRoom(106));
    }
    @Test
    public void test_standard_room_one_facility() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(3.00);

        Set<Facilities> facilities = new HashSet<>();
        facilities.add(SWIMMING_POOL);

        assertThat(bookingService.quoteRoom(new StandardRoom(), facilities),
                is(equalTo(expectedPrice)));
    }
    @Test
    public void test_standard_room_three_facilities() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(9.00);

        Set<Facilities> facilities = new HashSet<>();
        facilities.add(SWIMMING_POOL);
        facilities.add(ROOM_BREAKFAST);
        facilities.add(INTERNET);

        assertThat(bookingService.quoteRoom(new StandardRoom(), facilities),
                is(equalTo(expectedPrice)));
    }
    @Test
    public void test_standard_room_five_facilities() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(21.00);

        Set<Facilities> facilities = new HashSet<>();
        facilities.add(SWIMMING_POOL);
        facilities.add(ROOM_BREAKFAST);
        facilities.add(INTERNET);
        facilities.add(LATE_CHECKOUT);
        facilities.add(ENSUITE_BATHROOM);

        assertThat(bookingService.quoteRoom(new StandardRoom(), facilities),
                is(equalTo(expectedPrice)));
    }
    @Test
    public void test_suite_room_one_facility() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(1.00);

        Set<Facilities> facilities = new HashSet<>();
        facilities.add(SWIMMING_POOL);

        assertThat(bookingService.quoteRoom(new SuiteRoom(), facilities),
                is(equalTo(expectedPrice)));
    }
    @Test
    public void test_suite_room_five_facilities() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(13.00);

        Set<Facilities> facilities = new HashSet<>();
        facilities.add(SWIMMING_POOL);
        facilities.add(ROOM_BREAKFAST);
        facilities.add(INTERNET);
        facilities.add(LATE_CHECKOUT);
        facilities.add(ENSUITE_BATHROOM);

        assertThat(bookingService.quoteRoom(new SuiteRoom(), facilities),
                is(equalTo(expectedPrice)));
    }
    @Test
    public void test_great_view__room_one_facility() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(2.00);

        Set<Facilities> facilities = new HashSet<>();
        facilities.add(SWIMMING_POOL);

        assertThat(bookingService.quoteRoom(new GreatViewRoom(), facilities),
                is(equalTo(expectedPrice)));
    }
    @Test
    public void test_great_view_room_five_facilities() throws Exception {
        final BigDecimal expectedPrice = new BigDecimal(26.00);

        RoomBookingService bookingService = new RoomBookingService();
        Set<Facilities> facilities = new HashSet<>();
        facilities.add(SWIMMING_POOL);
        facilities.add(ROOM_BREAKFAST);
        facilities.add(INTERNET);
        facilities.add(LATE_CHECKOUT);
        facilities.add(ENSUITE_BATHROOM);

        assertThat(bookingService.quoteRoom(new GreatViewRoom(), facilities),
                is(equalTo(expectedPrice)));
    }
    @Test
    public void test_booking_with_available_room(){
        Room room = new GreatViewRoom(101);
        bookingService.bookRoom(room);
        assertThat(bookingService.isRoomAvailable(room), is(false));
        assertThat(schedule.getAllAvailableRooms(), not(hasItem(room)));
    }

    @Test(expected = NullPointerException.class)
    public void test_booking_with_no_room_number(){
        Room room = new StandardRoom();
        assertThat(bookingService.isRoomAvailable(room), is(false));
        bookingService.bookRoom(room);
    }
    @Test
    public void test_adding_then_booking_new_room(){
        Room room = new SuiteRoom(107);
        bookingService.addRoom(room);
        assertThat(schedule.getAllAvailableRooms(), hasItem(room));
        bookingService.bookRoom(room);
        assertThat(bookingService.isRoomAvailable(room), is(false));
    }
    @Test(expected = NullPointerException.class)
    public void test_booking_with_nonexistent_room(){
        Room room = new StandardRoom(999);
        assertThat(bookingService.isRoomAvailable(room), is(false));
        bookingService.bookRoom(room);
    }
    @Test(expected = IllegalStateException.class)
    public void test_adding_room_with_duplicate_room_number(){
        //Room 101 is Great View room not a Standard room
        Room room = new StandardRoom(101);
        assertThat(bookingService.isRoomAvailable(room), is(false));
        bookingService.addRoom(room);
    }
    @Test(expected = IllegalStateException.class)
    public void test_booking_room_with_duplicate_room_number(){
        //Room 101 is Great View room not a Standard room
        Room room = new StandardRoom(101);
        assertThat(bookingService.isRoomAvailable(room), is(false));
        bookingService.bookRoom(room);
    }
}