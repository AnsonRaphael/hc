package com.nanos.irctc.model.booking;

import com.nanos.irctc.entity.train.Seat;
import com.nanos.irctc.entity.user.User;
import com.nanos.irctc.model.train.SeatDTO;
import com.nanos.irctc.model.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingDTO {
    private Long bookingId;
    private UserDTO userDTO;
    private Date bookingDate;
    private SeatDTO seatDTO;
}
