package com.iryna.net.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @DisplayName("send Message Via Socket")
    @Test
    void sendMessageViaSocketTest() {

        Server.startEchoServer();
        assertEquals("Echo: hello socket", Client.sendMessageToEchoServer("hello socket"));
    }
}