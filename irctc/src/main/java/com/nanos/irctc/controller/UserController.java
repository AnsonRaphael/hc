package com.nanos.irctc.controller;

import com.nanos.irctc.model.user.UserDTO;
import com.nanos.irctc.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @Operation(summary = "add user ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created new user",
                    content = { @Content(mediaType = "application/json"
                    ) }),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDTO userDTO){
        userService.saveUser(userDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all users ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the all users",
                    content = { @Content(mediaType = "application/json"
                            ) })
                    })
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> users = userService.getAllUser();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

}
