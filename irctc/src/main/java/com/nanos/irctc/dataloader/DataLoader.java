package com.nanos.irctc.dataloader;


import com.nanos.irctc.entity.train.CoachType;
import com.nanos.irctc.entity.user.Role;
import com.nanos.irctc.model.booking.BookingDTO;
import com.nanos.irctc.model.booking.MultipleBookingDTO;
import com.nanos.irctc.model.train.CoachDTO;
import com.nanos.irctc.model.train.SeatDTO;
import com.nanos.irctc.model.train.TrainDTO;
import com.nanos.irctc.model.user.UserDTO;
import com.nanos.irctc.service.booking.BookingService;
import com.nanos.irctc.service.train.TrainService;
import com.nanos.irctc.service.train.TrainServiceImpl;
import com.nanos.irctc.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final TrainService trainService;
    private final BookingService bookingService;
    @Override
    public void run(String... args) throws Exception {
       // saveListOfUser();
       // saveTrain();
        //saveListOfBooking();
    }

    public void saveListOfBooking(){
        //List<UserDTO> userDTOS = new ArrayList<>();
        UserDTO userDTO = UserDTO.builder()
                .role(Role.USER)
                .userName("user3")
                .password("password")
                .email("user1@email.com")
                .build();
        userDTO=userService.saveUser(userDTO);
        TrainDTO trainDTO = TrainDTO.builder()
                .trainName("SH1 - cl")
                .build();
        trainDTO=trainService.saveTrain(trainDTO);
        CoachDTO coachDTO = CoachDTO.builder()
                .coachType(CoachType.SEATER)
                .build();
        coachDTO=trainService.addCoaches(trainDTO.getTrainId(),coachDTO);

        List<SeatDTO> seatDTo =trainService.getCoachSeat(coachDTO.getCoachId());
//        MultipleBookingDTO bookingDTO = MultipleBookingDTO.builder()
//                .bookingDate(new Date(System.currentTimeMillis()))
//                .userDTO(userDTO)
//                .seatDTOs(seatDTo)
//                .build();
//
//        bookingService.addMultiBooking(bookingDTO);
        BookingDTO bookingDTO = BookingDTO.builder()
                .bookingDate(new Date(System.currentTimeMillis()))
                .userDTO(userDTO)
                .seatDTO(seatDTo.get(0))
                .build();

        bookingService.addBooking(bookingDTO);
        //userDTOS.add(userDTO);

    }


    public void saveListOfUser(){
        //List<UserDTO> userDTOS = new ArrayList<>();
        UserDTO userDTO = UserDTO.builder()
                .role(Role.USER)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();
        //userDTOS.add(userDTO);
        UserDTO userDTO1 = UserDTO.builder()
                .role(Role.ADMIN)
                .userName("admin1")
                .password("password")
                .email("admin1@email.com")
                .build();
        userService.saveUser(userDTO);
        userService.saveUser(userDTO1);
    }
    public void saveTrain(){
        //List<UserDTO> userDTOS = new ArrayList<>();
        //userDTOS.add(userDTO);
//        UserDTO userDTO1 = UserDTO.builder()
//                .role(Role.ADMIN)
//                .userName("admin2")
//                .password("password")
//                .email("admin2@email.com")
//                .build();
//        userService.saveUser(userDTO1);

        TrainDTO trainDTO = TrainDTO.builder()
                .trainName("SH1 - cl")
                .build();
        trainDTO=trainService.saveTrain(trainDTO);
        CoachDTO coachDTO = CoachDTO.builder()
                .coachType(CoachType.SEATER)
                .build();
        coachDTO=trainService.addCoaches(trainDTO.getTrainId(),coachDTO);
        CoachDTO coachDTO1 = CoachDTO.builder()
                .coachType(CoachType.SEATER)
                .build();
        coachDTO1=trainService.addCoaches(trainDTO.getTrainId(),coachDTO1);
        trainService.removeCoaches(coachDTO1.getCoachId());
        coachDTO.setCoachType(CoachType.NON_AC);
        trainService.updateCoaches(coachDTO.getCoachId(),coachDTO);


        List<SeatDTO> col = trainService.getCoachSeat(coachDTO.getCoachId());
        System.out.println(col);
    }
    public void deleteTrain(){
        //List<UserDTO> userDTOS = new ArrayList<>();
        //userDTOS.add(userDTO);
//        UserDTO userDTO1 = UserDTO.builder()
//                .role(Role.ADMIN)
//                .userName("admin2")
//                .password("password")
//                .email("admin2@email.com")
//                .build();
//        userService.saveUser(userDTO1);

        TrainDTO trainDTO = TrainDTO.builder()
                .trainName("SH2 - cl")
                .build();
        trainDTO=trainService.saveTrain(trainDTO);
        CoachDTO coachDTO = CoachDTO.builder()
                .coachType(CoachType.AC)
                .build();
        trainService.addCoaches(trainDTO.getTrainId(),coachDTO);
        CoachDTO coachDTO1 = CoachDTO.builder()
                .coachType(CoachType.SEATER)
                .build();
        trainService.addCoaches(trainDTO.getTrainId(),coachDTO1);

        trainService.removeCoaches(coachDTO1.getCoachId());
    }


}
