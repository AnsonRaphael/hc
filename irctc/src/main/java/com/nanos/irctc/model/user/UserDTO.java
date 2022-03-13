package com.nanos.irctc.model.user;

import com.nanos.irctc.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private Role role;
}
