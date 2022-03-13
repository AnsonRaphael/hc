package com.nanos.irctc.service.user;

import com.nanos.irctc.model.user.UserDTO;

public interface UserService {
    public void saveUser(UserDTO userDTO);
    public void updateUser(Long id, UserDTO userDTO);
    public void deleteUser(Long id);
    public UserDTO getUser(Long id);
}
