package com.nanos.irctc.service.booking;

import com.nanos.irctc.entity.booking.Booking;
import com.nanos.irctc.entity.train.Coach;
import com.nanos.irctc.entity.train.Seat;
import com.nanos.irctc.entity.user.User;
import com.nanos.irctc.exception.NotFoundException;
import com.nanos.irctc.exception.SeatUnAvailableException;
import com.nanos.irctc.mapper.*;
import com.nanos.irctc.model.booking.BookingDTO;
import com.nanos.irctc.model.booking.MultipleBookingDTO;
import com.nanos.irctc.model.train.SeatDTO;
import com.nanos.irctc.repository.booking.BookingRepository;
import com.nanos.irctc.repository.train.CoachRepository;
import com.nanos.irctc.repository.train.SeatRepository;
import com.nanos.irctc.repository.train.TrainRepository;
import com.nanos.irctc.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    SeatMapper seatMapper;
    UserMapper userMapper;
    private final TrainRepository trainRepository;
    private final CoachRepository coachRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    TrainMapper trainMapper;
    CoachMapper coachMapper;
    @Transactional
    public BookingDTO addBooking(BookingDTO bookingDTO){
        if(!isSeatAvailable(bookingDTO.getSeatDTO()))
            throw new SeatUnAvailableException("Seat already booked");
        Booking booking = bookingMapper.bookingDTOToBooking(bookingDTO);
        Seat seat = seatRepository.getById(bookingDTO.getSeatDTO().getSeatId());
        User user =userRepository.getById(bookingDTO.getUserDTO().getUserId());
        booking.setUser(user);
        booking.setSeat(seat);
        booking= bookingRepository.save(booking);
       return bookingMapper.bookingToBookingDTO(booking);
    }

    @Transactional
    public MultipleBookingDTO addMultiBooking(MultipleBookingDTO multipleBookingDTO){
        List<SeatDTO> seatDTOS = multipleBookingDTO.getSeatDTOs();
        if(seatDTOS==null)
            return null;
        for (int i = 0; i < seatDTOS.size(); i++) {
            SeatDTO seatDTO = seatDTOS.get(i);
            if(!isSeatAvailable(seatDTO))
                throw new SeatUnAvailableException("Seat already booked");
        }
        for (int i = 0; i < seatDTOS.size(); i++) {
            SeatDTO seatDTO = seatDTOS.get(i);
            Booking booking = Booking.builder()
                    .bookingDate(multipleBookingDTO.getBookingDate())
                    .seat(seatMapper.seatDTOToSeat(seatDTO))
                    .user(userMapper.userDTOToUser(multipleBookingDTO.getUserDTO()))
                    .build();
            booking= bookingRepository.save(booking);
            multipleBookingDTO.getBookingIds().add(booking.getBookingId());
        }


        return multipleBookingDTO;
    }
    private boolean isSeatAvailable(SeatDTO seatDTO){
        boolean result =true;
        Seat seat = seatMapper.seatDTOToSeat(seatDTO);
        Optional<Booking> optionalSeat = bookingRepository.findBySeat(seat);
        if(optionalSeat.isPresent())
            return false;
        return result;
    }

    public List<SeatDTO> getAvailableCoachSeat(Long coachId) {
        Optional<Coach> coachOptional = coachRepository.findById(coachId);
        if (!coachOptional.isPresent())
            throw new NotFoundException("coach id "+coachId+" not found in DB");
        Coach coach = coachOptional.get();
        List<SeatDTO> seatDTOS= new ArrayList<>();
        List<Seat> seats = coach.getSeats();
        for (int i = 0; i < seats.size(); i++) {
            Seat seat=seats.get(i);
            SeatDTO seatDTO= seatMapper.seatToSeatDTO(seat);
            if(isSeatAvailable(seatDTO))
            seatDTOS.add(seatDTO);
        }
        return seatDTOS;
    }
}
