package com.iryna.file;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    private final String testPath = "C:/test";
    private final String testPath2 = "C:/test1";

    @Test
    void countFiles() {
        assertEquals(6, FileManager.countFiles(testPath));
    }

    @Test
    void countDirs() {
        assertEquals(4, FileManager.countDirectories(testPath));
    }

    @Test
    void copy() {
        FileManager.copy(testPath, testPath2);
        File fileFrom = new File(testPath);
        File fileTo = new File(testPath);
        assertEquals(fileFrom.listFiles().length, fileTo.listFiles().length);
    }

    @Test
    void move() {
        File fileFrom = new File(testPath2);
        int fileCount = fileFrom.listFiles().length;
        FileManager.move(testPath2, testPath);
        assertTrue(fileFrom.listFiles().length == 0);
        assertEquals(fileCount, new File(testPath).listFiles().length);
    }
}