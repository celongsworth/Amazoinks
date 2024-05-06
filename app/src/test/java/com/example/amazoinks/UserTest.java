package com.example.amazoinks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.amazoinks.database.entities.User;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    User user1;
    User  user2;

    @Before
    public void setup(){
        user1 = new User("trent", "1234");
        user2 =  new User("xtina", "5678");
    }

    @Test
    public void getUserNameTest(){
        assertNotEquals(user2.getUsername(), user1.getUsername());
        assertEquals(user1.getUsername(),  "trent");
    }

    @Test
    public void getPasswordTest(){
        assertNotEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getPassword(), "1234");
    }
}
