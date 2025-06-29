package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetUserById() {
        User mockUser = new User("1", "Alice");
        when(userRepository.getUserById("1")).thenReturn(mockUser);

        User user = userService.getUserById("1");

        assertNotNull(user);
        assertEquals("Alice", user.getName());
        verify(userRepository).getUserById("1");
    }
}
