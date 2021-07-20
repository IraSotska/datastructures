package com.iryna.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ByteArrayOutputStreamTest {

    @DisplayName("write Byte Array")
    @Test
    void writeByteArrayTest() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String content = "Hello";
        byteArrayOutputStream.write(content.getBytes());
        assertEquals(content, byteArrayOutputStream.toString());

    }

    @DisplayName("write One Char")
    @Test
    void writeOneCharTest() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(121);
        assertEquals("y", byteArrayOutputStream.toString());
    }

    @DisplayName("write Range From Start Position")
    @Test
    void writeRangeFromStartPositionTest() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String content = "Writes the specified byte to this output stream. The 24 high-order bits of b are ignored.";
        byteArrayOutputStream.write(content.getBytes(), 0, 15);
        assertEquals("Writes the spec", byteArrayOutputStream.toString());
    }

    @DisplayName("write Range From Specified Position")
    @Test
    void writeRangeFromSpecifiedPositionTest() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String content = "Writes the specified byte to this output stream. The 24 high-order bits of b are ignored.";
        byteArrayOutputStream.write(content.getBytes(), 10, 15);
        assertEquals(" specified byte", byteArrayOutputStream.toString());
    }
}