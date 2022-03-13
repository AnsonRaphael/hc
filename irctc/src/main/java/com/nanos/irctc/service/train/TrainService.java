package com.nanos.irctc.service.train;

import com.nanos.irctc.model.train.CoachDTO;
import com.nanos.irctc.model.train.SeatDTO;
import com.nanos.irctc.model.train.TrainDTO;

import java.util.List;

public interface TrainService {
    public TrainDTO saveTrain(TrainDTO trainDTO);
    public TrainDTO updateTrain(Long id, TrainDTO trainDTO);
    public void deleteTrain(Long id);
    public CoachDTO addCoaches(Long trainId, CoachDTO coachDTO);
    public void removeCoaches(Long coachId);
    public void updateCoaches(Long coachId,CoachDTO coachDTO);
    public List<SeatDTO> getCoachSeat(Long coachId);
}
