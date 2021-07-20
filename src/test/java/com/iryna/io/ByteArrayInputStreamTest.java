package com.iryna.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ByteArrayInputStreamTest {

    @DisplayName("Read Single Byte")
    @Test
    public void testReadSingleByte() {
        String content = "Hello";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());

        assertEquals('H', (char) byteArrayInputStream.read());
        assertEquals('e', (char) byteArrayInputStream.read());
        assertEquals('l', (char) byteArrayInputStream.read());
        assertEquals('l', (char) byteArrayInputStream.read());
        assertEquals('o', (char) byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
    }

    @DisplayName("Read Empty Single Byte")
    @Test
    public void testReadEmptySingleByte() {
        String content = "";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
        assertEquals(-1, byteArrayInputStream.read());
    }

    @DisplayName("Read To Array")
    @Test
    void testReadToArray() {
        String content = "Hello";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
        byte[] result = new byte[10];
        int messageSize = byteArrayInputStream.read(result);
        for (int i = 0; i < messageSize; i++) {
            assertEquals(content.charAt(i), result[i]);
        }
        assertEquals(-1, byteArrayInputStream.read());
    }

    @DisplayName("Read Empty Bytes To Array")
    @Test
    void testReadEmptyBytesToArray() {
        String content = "";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
        byte[] result = new byte[10];
        int messageSize = byteArrayInputStream.read(result);

        assertEquals(-1, messageSize);
    }

    @DisplayName("Read Range Of Bytes From Start")
    @Test
    void testReadRangeOfBytesFromStart() {
        String content = "Writes the specified byte to this output stream. The 24 high-order bits of b are ignored.";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
        byte[] result = new byte[20];
        int messageSize = byteArrayInputStream.read(result, 0, 18);
        for (int i = 0; i < messageSize; i++) {
            assertEquals(content.charAt(i), result[i]);
        }
    }

    @DisplayName("Read Range Of Bytes")
    @Test
    void testReadRangeOfBytes() {
        String content = "Writes the specified byte to this output stream. The 24 high-order bits of b are ignored.";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
        byte[] result = new byte[30];
        int messageSize = byteArrayInputStream.read(result, 3, 18);
        assertEquals(18, messageSize);
        for (int i = 0; i < messageSize; i++) {
            assertEquals(content.charAt(i), result[i + 3]);
        }
    }

    @DisplayName("Read Empty Range Of Bytes")
    @Test
    void testReadEmptyRangeOfBytes() {
        String content = "Writes the specified byte to this output stream. The 24 high-order bits of b are ignored.";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
        byte[] result = new byte[20];
        int messageSize = byteArrayInputStream.read(result, 0, 0);
        assertEquals(0, messageSize);
        for (int i = 0; i < messageSize; i++) {
            assertEquals(0, result[i]);
        }
    }
}