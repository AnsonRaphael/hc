package com.nanos.irctc.model.train;

import com.nanos.irctc.entity.train.CoachType;
import com.nanos.irctc.entity.train.Seat;
import com.nanos.irctc.entity.train.Train;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CoachDTO {
    private Long coachId;
    private CoachType coachType;
    private TrainDTO train;
    private List<SeatDTO> seatsDtos;
}
