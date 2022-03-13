package com.nanos.irctc.entity.train;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long trainId;
    private String trainName;
    @OneToMany(mappedBy = "train",fetch = FetchType.EAGER,cascade = { CascadeType.REMOVE })
    private List<Coach> coaches;
}
