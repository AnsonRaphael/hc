package com.nanos.irctc.dataloader;


import com.nanos.irctc.entity.user.Role;
import com.nanos.irctc.model.user.UserDTO;
import com.nanos.irctc.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    @Override
    public void run(String... args) throws Exception {
        saveListOfUser();

    }
    public void saveListOfUser(){
        //List<UserDTO> userDTOS = new ArrayList<>();
        UserDTO userDTO = UserDTO.builder()
                .role(Role.USER)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();
        //userDTOS.add(userDTO);
        UserDTO userDTO1 = UserDTO.builder()
                .role(Role.ADMIN)
                .userName("admin1")
                .password("password")
                .email("admin1@email.com")
                .build();
        userService.saveUser(userDTO);
        userService.saveUser(userDTO1);
    }
}
