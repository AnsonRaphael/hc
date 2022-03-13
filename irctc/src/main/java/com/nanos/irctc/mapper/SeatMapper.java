package com.nanos.irctc.mapper;

import com.nanos.irctc.entity.train.Coach;
import com.nanos.irctc.entity.train.Seat;
import com.nanos.irctc.model.train.CoachDTO;
import com.nanos.irctc.model.train.SeatDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SeatMapper {
    SeatDTO seatToSeatDTO(Seat seat);
    Seat seatDTOToSeat(SeatDTO seatDTO);
}
