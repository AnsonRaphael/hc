package com.nanos.irctc.service.user;

import com.nanos.irctc.entity.user.Role;
import com.nanos.irctc.entity.user.User;
import com.nanos.irctc.exception.BadRequestException;
import com.nanos.irctc.exception.NotFoundException;
import com.nanos.irctc.mapper.UserMapper;
import com.nanos.irctc.mapper.UserMapperImpl;
import com.nanos.irctc.model.user.UserDTO;
import com.nanos.irctc.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserMapperImpl.class})
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    private UserService userService;


    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, userMapper);
    }


    @Test
    void canAddSaveUser() {
        // given
        User user = User.builder()
                .role(Role.USER)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();
        //when
        userService.saveUser(userMapper.userToUserDTO(user));
        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        // check arguments
        Mockito.verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        // check object
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // given
        User user = User.builder()
                .role(Role.USER)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();

        given(userRepository.selectExistsEmail(ArgumentMatchers.anyString()))
                .willReturn(true);

        // when
        // then
        assertThatThrownBy(() -> userService.saveUser(userMapper.userToUserDTO(user)))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + user.getEmail() + " taken");

        verify(userRepository, never()).save(any());

    }

    @Test
    void canUpdateUser() {
        // given
        long id = 66L;
        User user = User.builder()
                .userId(id)
                .role(Role.USER)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();

        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
        //when
        assert user != null;
        user.setUserName("user2");
        userService.updateUser(id, userMapper.userToUserDTO(user));

        //then
        Mockito.verify(userRepository).save(user);

    }

    @Test
    void WillThrowWhenUpdateUserNotExist() {

        given(userRepository.findById(any())).willReturn(Optional.empty());
        //when
        //then
        long id = 55L;
        assertThatThrownBy(() -> userService.updateUser(id, new UserDTO()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("User id " + id + " not found in DB");

        verify(userRepository, never()).save(any());
    }

    @Test
    void canDeleteUser() {
        User user = User.builder()
                .userId(55L)
                .role(Role.USER)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
        //when
        userService.deleteUser(55L);
        //then
        assert user != null;
        Mockito.verify(userRepository).delete(user);
    }

    @Test
    void WillThrowWhenDeleteUserNotExist() {

        given(userRepository.findById(any())).willReturn(Optional.empty());
        //when
        //then
        long id = 55L;
        assertThatThrownBy(() -> userService.deleteUser(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("User id " + id + " not found in DB");

        verify(userRepository, never()).delete(any());
    }

    @Test
    void canGetUser() {
        // given
        User user = User.builder()
                .userId(55L)
                .role(Role.USER)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
        //when
        Long id = 55L;
        userService.getUser(id);
        //then
        verify(userRepository).findById(id);
    }

    @Test
    void willThrowWhenGetUserNotExist() {
        // given
        Long id = 55L;
        given(userRepository.findById(id)).willReturn(Optional.empty());
        //when
        assertThatThrownBy(() -> userService.getUser(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("User id " + id + " not found in DB");
        //then
        verify(userRepository).findById(any());
    }

    @Test
    void canGetAllUser() {
        // given
        //when
        userService.getAllUser();
        //then
        verify(userRepository).findAll();
    }
}