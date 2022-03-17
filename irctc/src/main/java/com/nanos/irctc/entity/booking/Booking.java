package com.nanos.irctc.entity.booking;

import com.nanos.irctc.entity.train.Seat;
import com.nanos.irctc.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bookingId;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
    private Date bookingDate;
    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "seatId")
    private Seat seat;
}
