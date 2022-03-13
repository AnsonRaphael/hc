package com.nanos.irctc.mapper;

import com.nanos.irctc.entity.train.Train;
import com.nanos.irctc.model.train.TrainDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TrainMapper {
TrainDTO trainToTrainDTO(Train train);
Train trainDTOToTrain(TrainDTO trainDTO);
}
