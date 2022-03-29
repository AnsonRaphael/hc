package com.nanos.irctc.repository.booking;

import com.nanos.irctc.entity.booking.Booking;
import com.nanos.irctc.entity.train.Coach;
import com.nanos.irctc.entity.train.CoachType;
import com.nanos.irctc.entity.train.Seat;
import com.nanos.irctc.entity.train.Train;
import com.nanos.irctc.entity.user.Role;
import com.nanos.irctc.entity.user.User;
import com.nanos.irctc.repository.train.CoachRepository;
import com.nanos.irctc.repository.train.SeatRepository;
import com.nanos.irctc.repository.train.TrainRepository;
import com.nanos.irctc.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest // for testing repo - spinning db
class BookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Test
    void findBySeat() {
        //given
        Booking booking = new Booking();
        Seat seat=new Seat();
        Train train=Train.builder().trainName("lm").build();
        trainRepository.save(train);
        Coach coach=Coach.builder().coachType(CoachType.SEATER).
                train(train).build();
        coachRepository.save(coach);
        seat.setCoach(coach);
        seat = seatRepository.save(seat);
        User user = User.builder()
                .role(Role.USER)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();
        user = userRepository.save(user);
        booking.setUser(user);
        booking.setSeat(seat);
        // when
        booking= bookingRepository.save(booking);
        // then
        assertTrue(booking.getBookingId()!=0);
    }
}