package com.nanos.irctc.service.booking;

import com.nanos.irctc.model.booking.BookingDTO;
import com.nanos.irctc.model.booking.MultipleBookingDTO;
import com.nanos.irctc.model.train.SeatDTO;

import java.util.List;

public interface BookingService {
    public BookingDTO addBooking(BookingDTO bookingDTO);
    public MultipleBookingDTO addMultiBooking(MultipleBookingDTO multipleBookingDTO);
    public List<SeatDTO> getAvailableCoachSeat(Long coachId);
}
