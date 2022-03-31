package com.nanos.irctc.service.booking;

import com.nanos.irctc.mapper.*;
import com.nanos.irctc.repository.train.CoachRepository;
import com.nanos.irctc.repository.train.SeatRepository;
import com.nanos.irctc.repository.train.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserMapperImpl.class})
class BookingServiceImplTest {


    BookingMapper bookingMapper;
    SeatMapper seatMapper;
    UserMapper userMapper;
    @Mock
    TrainRepository trainRepository;
    CoachRepository coachRepository;
    SeatRepository seatRepository;
    CoachMapper coachMapper;

    @Test
    void addBooking() {
        // Mockito.when(trainRepository.getById(any(Long.class))).thenReturn(null);
        //assertTrue();
        // verify(trainRepository,atLeast(1)).findById(any(Long.class));
    }

    @Test
    void addMultiBooking() {
    }

    @Test
    void getAvailableCoachSeat() {
    }
}