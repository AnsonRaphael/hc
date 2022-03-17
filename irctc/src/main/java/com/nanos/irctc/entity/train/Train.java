package com.nanos.irctc.entity.train;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long trainId;
    private String trainName;
    @OneToMany(mappedBy = "train",cascade = { CascadeType.ALL })
    private List<Coach> coaches;
}
