package com.nanos.irctc.mapper;

import com.nanos.irctc.entity.booking.Booking;
import com.nanos.irctc.entity.train.Coach;
import com.nanos.irctc.model.booking.BookingDTO;
import com.nanos.irctc.model.train.CoachDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BookingMapper {
    BookingDTO bookingToBookingDTO(Booking booking);
    Booking bookingDTOToBooking(BookingDTO bookingDTO);
}
