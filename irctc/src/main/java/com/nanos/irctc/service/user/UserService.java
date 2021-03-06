package com.nanos.irctc.service.user;

import com.nanos.irctc.model.user.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO saveUser(UserDTO userDTO);
    public void updateUser(Long id, UserDTO userDTO);
    public void deleteUser(Long id);
    public UserDTO getUser(Long id);
    public List<UserDTO> getAllUser();
}
