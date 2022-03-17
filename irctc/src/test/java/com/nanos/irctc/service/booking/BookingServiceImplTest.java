package com.nanos.irctc.service.booking;

import com.nanos.irctc.mapper.*;
import com.nanos.irctc.repository.booking.BookingRepository;
import com.nanos.irctc.repository.train.CoachRepository;
import com.nanos.irctc.repository.train.SeatRepository;
import com.nanos.irctc.repository.train.TrainRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


class BookingServiceImplTest {


    BookingMapper bookingMapper;
    SeatMapper seatMapper;
    UserMapper userMapper;
    @Mock
    TrainRepository trainRepository;
    CoachRepository coachRepository;
    SeatRepository seatRepository;
    TrainMapper trainMapper;
    CoachMapper coachMapper;
    @Test
    void addBooking() {
        Mockito.when(trainRepository.getById(any(Long.class))).thenReturn(null);
        //assertTrue();
        verify(trainRepository,atLeast(1)).findById(any(Long.class));
    }

    @Test
    void addMultiBooking() {
    }

    @Test
    void getAvailableCoachSeat() {
    }
}