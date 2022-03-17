package com.nanos.irctc.entity.train;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long coachId;
    private CoachType coachType;
    @ManyToOne
    @JoinColumn(name = "trainId",nullable = false)
    private Train train;
    @OneToMany(mappedBy = "coach",cascade = { CascadeType.ALL })
    private List<Seat> seats;
}
