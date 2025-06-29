package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        User user = new User("123", "John");

        assertEquals("123", user.getId());
        assertEquals("John", user.getName());
    }

    @Test
    public void testUserSetters() {
        User user = new User("123", "John");
        user.setId("456");
        user.setName("Doe");

        assertEquals("456", user.getId());
        assertEquals("Doe", user.getName());
    }
}
