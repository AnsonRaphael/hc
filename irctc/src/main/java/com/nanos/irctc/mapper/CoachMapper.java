package com.nanos.irctc.mapper;

import com.nanos.irctc.entity.train.Coach;
import com.nanos.irctc.entity.train.Train;
import com.nanos.irctc.model.train.CoachDTO;
import com.nanos.irctc.model.train.TrainDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CoachMapper {
    CoachDTO coachToCoachDTO(Coach coach);
    Coach coachDTOToCoach(CoachDTO coachDTO);
}
