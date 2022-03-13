package com.nanos.irctc.service.train;

import com.nanos.irctc.config.SeatNoConstants;
import com.nanos.irctc.entity.train.Coach;
import com.nanos.irctc.entity.train.Seat;
import com.nanos.irctc.entity.train.Train;
import com.nanos.irctc.entity.user.User;
import com.nanos.irctc.exception.NotFoundException;
import com.nanos.irctc.mapper.CoachMapper;
import com.nanos.irctc.mapper.SeatMapper;
import com.nanos.irctc.mapper.TrainMapper;
import com.nanos.irctc.model.train.CoachDTO;
import com.nanos.irctc.model.train.SeatDTO;
import com.nanos.irctc.model.train.TrainDTO;
import com.nanos.irctc.model.user.UserDTO;
import com.nanos.irctc.repository.train.CoachRepository;
import com.nanos.irctc.repository.train.SeatRepository;
import com.nanos.irctc.repository.train.TrainRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class TrainServiceImpl implements TrainService {
    private final TrainRepository trainRepository;
    private final CoachRepository coachRepository;
    private final SeatRepository seatRepository;
    SeatMapper seatMapper;
    TrainMapper trainMapper;
    CoachMapper coachMapper;
    public void saveTrain(TrainDTO trainDTO) {
        Train train = Train.builder()
                        .trainName(trainDTO.getTrainName())
                                        .build();
        trainRepository.save(train);
    }

    public void updateTrain(Long id, TrainDTO trainDTO) {
        Optional<Train> trainOptional = trainRepository.findById(id);
        if (!trainOptional.isPresent())
            throw new NotFoundException("train id "+id+" not found in DB");
        Train train = trainOptional.get();
        train.setTrainName(trainDTO.getTrainName());
        trainRepository.save(train);
    }

    public void deleteTrain(Long id) {
        Optional<Train> trainOptional = trainRepository.findById(id);
        if (!trainOptional.isPresent())
            throw new NotFoundException("train id "+id+" not found in DB");
        Train train = trainOptional.get();
        trainRepository.delete(train);
    }
    @Transactional
    public void addCoaches(Long trainId, CoachDTO coachDTO){
        Optional<Train> trainOptional = trainRepository.findById(trainId);
        if (!trainOptional.isPresent())
            throw new NotFoundException("train id "+trainId+" not found in DB");
        Train train = trainOptional.get();
        Coach coach = Coach.builder()
                .coachType(coachDTO.getCoachType())
                .train(train)
                .build();
        coach=coachRepository.saveAndFlush(coach);
        int noOfSeats= 0;
        if(coachDTO.getCoachType().equals("AC"))
            noOfSeats=SeatNoConstants.AC;
        if(coachDTO.getCoachType().equals("NON_AC"))
            noOfSeats=SeatNoConstants.NON_AC;
        if(coachDTO.getCoachType().equals("SEATER"))
            noOfSeats=SeatNoConstants.SEATER;
        List<Seat> seats=new ArrayList<>();
        for (int i = 0; i < noOfSeats; i++) {
            Seat seat=Seat.builder()
                    .coach(coach).build();
            seats.add(seat);
        }
        seatRepository.saveAllAndFlush(seats);
    }

    @Transactional
    public void removeCoaches(Long coachId){
        Optional<Coach> coachOptional = coachRepository.findById(coachId);
        if (!coachOptional.isPresent())
            throw new NotFoundException("coach id "+coachId+" not found in DB");
        Coach coach = coachOptional.get();
        coachRepository.delete(coach);
    }
    @Transactional
    public void updateCoaches(Long coachId,CoachDTO coachDTO){
        Optional<Coach> coachOptional = coachRepository.findById(coachId);
        if (!coachOptional.isPresent())
            throw new NotFoundException("coach id "+coachId+" not found in DB");
        Coach coach = coachOptional.get();
        if(coach.getCoachType()==coachDTO.getCoachType())
            return;
        int currentSeatNo=coach.getSeats().size();
        int noOfSeats= 0;
        if(coachDTO.getCoachType().equals("AC"))
            noOfSeats=SeatNoConstants.AC;
        if(coachDTO.getCoachType().equals("NON_AC"))
            noOfSeats=SeatNoConstants.NON_AC;
        if(coachDTO.getCoachType().equals("SEATER"))
            noOfSeats=SeatNoConstants.SEATER;
        if(currentSeatNo>noOfSeats){
            int diff=currentSeatNo-noOfSeats;
            List<Seat> seats =coach.getSeats();
            for (int i = 0; i < diff; i++) {
                seatRepository.delete(seats.get(i));
            }
        }else{
            int diff=noOfSeats-currentSeatNo;
            List<Seat> seats=new ArrayList<>();
            for (int i = 0; i < diff; i++) {
                Seat seat=Seat.builder()
                        .coach(coach).build();
                seats.add(seat);
            }
            seatRepository.saveAllAndFlush(seats);
        }

        coachRepository.save(coach);
    }


    public List<SeatDTO> getCoachSeat(Long coachId) {
        Optional<Coach> coachOptional = coachRepository.findById(coachId);
        if (!coachOptional.isPresent())
            throw new NotFoundException("coach id "+coachId+" not found in DB");
        Coach coach = coachOptional.get();
        List<SeatDTO> seatDTOS= new ArrayList<>();
        List<Seat> seats = coach.getSeats();
        for (int i = 0; i < seats.size(); i++) {
            Seat seat=seats.get(i);
            SeatDTO seatDTO= seatMapper.seatToSeatDTO(seat);
            seatDTOS.add(seatDTO);
        }
        return seatDTOS;
    }


}
