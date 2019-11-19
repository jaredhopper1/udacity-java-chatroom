package edu.udacity.java.nano.test;

import edu.udacity.java.nano.chat.Message;
import junit.framework.TestCase;

public class MessageTest extends TestCase {
    private Message testMessage = new Message("Hello World", "Jared Hopper", 1, "SPEAK");


    public void testGetMsg() {
        assertEquals("Hello World", testMessage.getMsg());
    }

    public void testSetMsg() {
        testMessage.setMsg("New Message");
        assertTrue(testMessage.getMsg() == "New Message");

    }

    public void testGetUsername() {
        assertEquals("Jared Hopper", testMessage.getUsername());
    }

    public void testSetUsername() {
        testMessage.setUsername("Eric Hopper");
        assertTrue(testMessage.getUsername() == "Eric Hopper");
    }

    public void testGetOnlineCount() {
        assertEquals(1, testMessage.getOnlineCount());
    }

    public void testSetOnlineCount() {
        testMessage.setOnlineCount(2);
        assertTrue(testMessage.getOnlineCount()== 2);
    }

    public void testGetType() {
        assertEquals("SPEAK", testMessage.getType());
    }

    public void testSetType() {
        testMessage.setType("LEAVE");
        assertTrue(testMessage.getType()== "LEAVE");

    }
}