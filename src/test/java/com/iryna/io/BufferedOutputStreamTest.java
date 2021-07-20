package com.iryna.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class BufferedOutputStreamTest {

    private final String TEST_FILE_PATH = "file.txt";

    @BeforeEach
    void createTestFile() throws IOException {
        File file = new File(TEST_FILE_PATH);
        file.createNewFile();
    }

    @AfterEach
    void removeTestFile() {
        File file = new File(TEST_FILE_PATH);
        file.delete();
    }

    @DisplayName("test Write Single Byte")
    @Test
    void testWriteSingleByte() throws IOException {

        String content = "Hello";
        FileOutputStream byteArrayOutputStream = new FileOutputStream(TEST_FILE_PATH);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        bufferedOutputStream.write(72);
        bufferedOutputStream.write(101);
        bufferedOutputStream.write(108);
        bufferedOutputStream.write(108);
        bufferedOutputStream.write(111);
        bufferedOutputStream.flush();
        try (FileInputStream fis = new FileInputStream(TEST_FILE_PATH)) {
            for (int i = 0; i < content.length(); i++) {
                assertEquals(content.charAt(i), (char) fis.read());
            }
        }
    }

    @DisplayName("test Write Queue Of Chars")
    @Test
    void testWriteQueueOfChars() throws IOException {
        String content = "Writes the specified byte";
        FileOutputStream byteArrayOutputStream = new FileOutputStream(TEST_FILE_PATH);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        bufferedOutputStream.write(content.getBytes());
        bufferedOutputStream.flush();

        try (FileInputStream fis = new FileInputStream(TEST_FILE_PATH)) {
            for (int i = 0; i < content.length(); i++) {
                assertEquals(content.charAt(i), (char) fis.read());
            }
        }
    }

    @DisplayName("test Write Range Of Chars")
    @Test
    void testWriteRangeOfChars() throws IOException {
        String content = "Writes the specified byte to this output stream. The 24 high-order bits of b are ignored.";
        FileOutputStream byteArrayOutputStream = new FileOutputStream(TEST_FILE_PATH);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        bufferedOutputStream.write(content.getBytes(), 3, 18);
        bufferedOutputStream.flush();

        try (FileInputStream fis = new FileInputStream(TEST_FILE_PATH)) {
            for (int i = 0; i < 15; i++) {
                assertEquals(content.charAt(i + 3), (char) fis.read());
            }
        }
    }

    @Test
    void flush() throws IOException {

        String content = "Wr";
        FileOutputStream byteArrayOutputStream
                = new FileOutputStream(TEST_FILE_PATH);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        bufferedOutputStream.write(content.getBytes());
        bufferedOutputStream.flush();
        try (FileInputStream fis = new FileInputStream(TEST_FILE_PATH)) {
            for (int i = 0; i < 2; i++) {
                assertEquals(content.charAt(i), (char) fis.read());
            }
        }
    }

    @Test
    void close() throws IOException {
        String content = "Wri";
        FileOutputStream byteArrayOutputStream
                = new FileOutputStream(TEST_FILE_PATH);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        bufferedOutputStream.write(content.getBytes());
        bufferedOutputStream.close();
        bufferedOutputStream.write(content.getBytes());

        try (FileInputStream fis = new FileInputStream(TEST_FILE_PATH)) {
            assertEquals('W', (char) fis.read());
            assertEquals('r', (char) fis.read());
            assertEquals('i', (char) fis.read());
        }
    }
}