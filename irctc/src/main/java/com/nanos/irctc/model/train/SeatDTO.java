package com.nanos.irctc.model.train;

import com.nanos.irctc.entity.train.Coach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SeatDTO {
    private Long seatId;
    private CoachDTO coachDTO;
}
