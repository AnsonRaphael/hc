package com.nanos.irctc.entity.user;

import com.nanos.irctc.entity.booking.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;


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
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private Role role;

    @OneToOne(mappedBy = "user")
    private Booking booking;
}
