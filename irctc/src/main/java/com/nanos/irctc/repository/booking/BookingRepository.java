package com.nanos.irctc.repository.booking;

import com.nanos.irctc.entity.booking.Booking;
import com.nanos.irctc.entity.train.Seat;
import com.nanos.irctc.entity.train.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    public Optional<Booking> findBySeat(Seat seat);
}
