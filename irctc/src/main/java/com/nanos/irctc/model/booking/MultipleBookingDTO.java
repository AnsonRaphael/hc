package com.nanos.irctc.model.booking;

import com.nanos.irctc.model.train.SeatDTO;
import com.nanos.irctc.model.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MultipleBookingDTO {
    private List<Long> bookingIds=new ArrayList<>();
    private UserDTO userDTO;
    private Date bookingDate;
    private List<SeatDTO> seatDTOs;
}
