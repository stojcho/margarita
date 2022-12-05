package com.stoycho.margarita.service;

import com.stoycho.margarita.DTO.UserAccountDTO;
import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.model.ShoppingCard;
import com.stoycho.margarita.model.User;
import com.stoycho.margarita.repository.IRoleRepository;
import com.stoycho.margarita.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private  IUserRepository iUserRepository;
    @Mock
    private  IRoleRepository iRoleRepository;
    @Mock
    private  PasswordEncoder passwordEncoder;
    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest=new UserService(iUserRepository,iRoleRepository,passwordEncoder);
    }

    @Test
    void canGetAllUsers() {
        //when
        underTest.getUsers();
        //then
        verify(iUserRepository).findAll();
    }

    @Test
    void canSaveUser() {
        //Given
        User user=new User(
                772L,
                "fname lname",
                "uname",
                "email.abv.bg",
                "0000000000",
                "street",
                "5",
                "9000",
                "Varna",
                "Bulgaria",
                "12345678",
                new ArrayList<>(),
                new ShoppingCard(10L,null,null));

        //When
        underTest.saveUser(user);

        //Then
        ArgumentCaptor<User> userArgumentCaptor=
                ArgumentCaptor.forClass(User.class);
        verify(iUserRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void willThrowWhenUserIsTaken() {
        //Given
        User user=new User(
                772L,
                "fname lname",
                "uname",
                "email.abv.bg",
                "0000000000",
                "street",
                "5",
                "9000",
                "Varna",
                "Bulgaria",
                "12345678",
                new ArrayList<>(),
                new ShoppingCard(10L,null,null));

        given(iUserRepository.checkIfExist(user.getUsername()))
                .willReturn(true);

        //When
        //Then
        assertThatThrownBy(() ->underTest.saveUser(user))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Username " + "uname" + " taken");

        verify(iUserRepository, never()).save(any());
    }

    @Test
    void willThrowWhenPasswordIsOutsideTheLimits() {
        //Given
        User user=new User(
                772L,
                "fname lname",
                "uname",
                "email.abv.bg",
                "0000000000",
                "street",
                "5",
                "9000",
                "Varna",
                "Bulgaria",
                "123456",
                new ArrayList<>(),
                new ShoppingCard(10L,null,null));

        //When
        //Then
        assertThatThrownBy(() ->underTest.saveUser(user))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Password length should be between 8 to 15 characters");

        user.setPassword("12345678987654321");

        assertThatThrownBy(() ->underTest.saveUser(user))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Password length should be between 8 to 15 characters");

        user.setPassword("12345678 ");

        assertThatThrownBy(() ->underTest.saveUser(user))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Password should not contain any space");
        verify(iUserRepository, never()).save(any());
    }
    @Test
    void canGetUserAccountDTO(){
        //Given
        User user=new User(
                772L,
                "fname lname",
                "uname",
                "email.abv.bg",
                "0000000000",
                "street",
                "5",
                "9000",
                "Varna",
                "Bulgaria",
                "12345678",
                new ArrayList<>(),
                new ShoppingCard(10L,null,null));
        UserAccountDTO expected=new UserAccountDTO(user);
        given(iUserRepository.getUserByUsername(user.getUsername()))
                .willReturn(user);
        //When
        UserAccountDTO received=underTest.getUserAccountDTO(user.getUsername());

        //Then
        assertThat(received).isEqualTo(expected);
    }
    @Test
    void canUpdateUser(){
        //Given
        User user=new User(
                772L,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                new ArrayList<>(),
                new ShoppingCard(10L,null,null));

        UserAccountDTO expected =new UserAccountDTO("uname",
                "name",
                "test@abv.bg",
                "08888888888",
                "street",
                "2",
                "7621",
                "Eindhoven",
                "Bulgaria");
        given(iUserRepository.getUserByUsername(expected.getUsername()))
                .willReturn(user);

        //When
        UserAccountDTO received=underTest.updateUser(expected);

        //Then
        ArgumentCaptor<String> userNameArgumentCaptor=
                ArgumentCaptor.forClass(String.class);
        verify(iUserRepository).getUserByUsername(userNameArgumentCaptor.capture());

        String capturedUserName = userNameArgumentCaptor.getValue();

        assertThat(capturedUserName).isEqualTo(expected.getUsername());
        expected.setUsername(null);
        assertThat(received).isEqualTo(expected);
    }

    @Test
    void canGetUserById() {
        //Given
        long userId=1L;
        //When
        underTest.getUser(userId);

        //Then
        ArgumentCaptor<Long> userArgumentCaptor=
                ArgumentCaptor.forClass(Long.class);
        verify(iUserRepository).getUserById(userArgumentCaptor.capture());

        Long capturedId = userArgumentCaptor.getValue();

        assertThat(capturedId).isEqualTo(userId);
    }

    @Test
    void canGetUserByUsername() {
        //Given
        String username="uName";
        //When
        underTest.getUser(username);

        //Then
        ArgumentCaptor<String> userArgumentCaptor=
                ArgumentCaptor.forClass(String.class);
        verify(iUserRepository).getUserByUsername(userArgumentCaptor.capture());

        String capturedUsername = userArgumentCaptor.getValue();

        assertThat(capturedUsername).isEqualTo(username);
    }

    @Test
    void canDeleteUser() {
        //Given
        long userId=1L;
        //When
        underTest.deleteUser(userId);

        //Then
        ArgumentCaptor<Long> userArgumentCaptor=
                ArgumentCaptor.forClass(Long.class);
        verify(iUserRepository).deleteById(userArgumentCaptor.capture());

        Long capturedId = userArgumentCaptor.getValue();

        assertThat(capturedId).isEqualTo(userId);
    }
}