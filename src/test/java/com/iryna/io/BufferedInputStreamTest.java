package com.iryna.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class BufferedInputStreamTest {

    @DisplayName("read One Symbol At Time")
    @Test
    void readOneSymbolAtTime() {
        String content = "Hello";
        BufferedInputStream byteArrayInputStream = new BufferedInputStream(new java.io.ByteArrayInputStream(content.getBytes()));
        for (int i = 0; i < content.length(); i++) {
            assertEquals(content.charAt(i), (char) byteArrayInputStream.read());
        }
        assertEquals(-1, byteArrayInputStream.read());
    }

    @DisplayName("read Range Of Characters")
    @Test
    void readRangeOfCharacters() {
        String content = "Hello";
        byte[] res = new byte[10];
        BufferedInputStream byteArrayInputStream = new BufferedInputStream(new java.io.ByteArrayInputStream(content.getBytes()));
        assertEquals(0, byteArrayInputStream.read(res, 0, 0));
        int countReadByte = byteArrayInputStream.read(res, 0, 4);
        assertEquals(4, countReadByte);
        for (int i = 0; i < countReadByte; i++) {
            assertEquals(content.charAt(i), res[i]);
        }
        assertEquals(0, res[countReadByte]);
    }

    @Test
    void close() throws IOException {
        String content = "Hello";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(content.getBytes()));
        bufferedInputStream.close();
        assertEquals(0, bufferedInputStream.read());
    }

    @DisplayName("read To Array")
    @Test
    void readToArray() throws IOException {

        String content = "Hello";
        java.io.ByteArrayInputStream byteArrayInputStream = new java.io.ByteArrayInputStream(content.getBytes());

        byte[] bytes = new byte[5];
        int countReadBytes = byteArrayInputStream.read(bytes);
        assertEquals(5, countReadBytes);
        for (int i = 0; i < countReadBytes; i++) {
            assertEquals(content.charAt(i), (char) bytes[i]);
        }
    }
}