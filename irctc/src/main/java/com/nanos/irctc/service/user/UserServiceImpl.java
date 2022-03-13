package com.nanos.irctc.service.user;

import com.nanos.irctc.entity.user.User;
import com.nanos.irctc.exception.NotFoundException;
import com.nanos.irctc.model.user.UserDTO;
import com.nanos.irctc.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public void saveUser(UserDTO userDTO) {
        User user = User.builder().userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
        userRepository.save(user);
    }

    public void updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            throw new NotFoundException("User id "+id+" not found in DB");
        User user = userOptional.get();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            throw new NotFoundException("User id "+id+" not found in DB");
        User user = userOptional.get();
        userRepository.delete(user);
    }

    public UserDTO getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            throw new NotFoundException("User id "+id+" not found in DB");
        User user = userOptional.get();
        UserDTO userDTO = UserDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole()).build();

        return userDTO;
    }
}
