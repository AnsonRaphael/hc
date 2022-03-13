package com.nanos.irctc.entity.user;

import com.nanos.irctc.entity.booking.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private Role role;

    @OneToOne(mappedBy = "user")
    private Booking booking;
}
