package com.nanos.irctc.model.train;

import com.nanos.irctc.entity.train.Coach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TrainDTO {
    private Long trainId;
    private String trainName;
    private List<CoachDTO> coachesDtos;
}
