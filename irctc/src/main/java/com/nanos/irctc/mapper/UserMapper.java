package com.nanos.irctc.mapper;

import com.nanos.irctc.entity.train.Coach;
import com.nanos.irctc.entity.user.User;
import com.nanos.irctc.model.train.CoachDTO;
import com.nanos.irctc.model.user.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
