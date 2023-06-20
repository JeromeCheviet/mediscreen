package com.mediscreen.muser.service;

import com.mediscreen.muser.exception.UserNotFoundException;
import com.mediscreen.muser.model.User;
import com.mediscreen.muser.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService = new UserServiceImpl();
    @Mock
    private UserRepository userRepository;
    @Mock
    private User expectedUser;

    @BeforeEach
    private void setUp() {
        int expectedUserId = 1;
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        LocalDate expectedBirthDate = LocalDate.parse("1990-12-25");
        String expectedGender = "Male";
        String expectedAddress = "1 rue du Puit";
        String expectedPhoneNumber = "000-111";

        expectedUser = new User();
        expectedUser.setUserID(expectedUserId);
        expectedUser.setFirstName(expectedFirstName);
        expectedUser.setLastName(expectedLastName);
        expectedUser.setBirthDate(expectedBirthDate);
        expectedUser.setGender(expectedGender);
        expectedUser.setAddress(expectedAddress);
        expectedUser.setPhoneNumber(expectedPhoneNumber);
    }

    @Test
    void testGetUsers() {
        User expectedUser2 = new User(2,
                "Jane",
                "Doe",
                LocalDate.parse("1980-03-07"),
                "Female",
                "1 rue du Puie",
                "000-222");

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(expectedUser);
        expectedUsers.add(expectedUser2);

        when(userRepository.findAll()).thenReturn(expectedUsers);

        Iterable<User> actualUsers = userService.getUsers();

        assertEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).findAll();

    }

    @Test
    void testGetUsers_raiseException_whenNoUsers() {
        when(userRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        Throwable exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUsers();
        });

        assertEquals("No users found in database", exception.getMessage());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById(){
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(expectedUser));

        Optional<User> actualUser = userService.getUserById(1);

        assertEquals(expectedUser.getFirstName(), actualUser.get().getFirstName());
    }
}
