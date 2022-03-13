package com.nanos.irctc.repository.train;

import com.nanos.irctc.entity.train.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}
