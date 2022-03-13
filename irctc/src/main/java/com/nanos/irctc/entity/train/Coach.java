package com.nanos.irctc.entity.train;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
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
    @OneToMany(mappedBy = "coach",fetch = FetchType.EAGER,cascade = { CascadeType.REMOVE })
    private List<Seat> seats;
}
