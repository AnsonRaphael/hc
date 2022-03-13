package com.nanos.irctc.entity.train;

import com.nanos.irctc.entity.booking.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seatId;
    @ManyToOne
    @JoinColumn(name = "coachId",nullable = false)
    private Coach coach;

    @OneToOne(mappedBy = "seat")
    private Booking booking;
}
