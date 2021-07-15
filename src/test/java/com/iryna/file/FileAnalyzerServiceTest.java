package com.iryna.file;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileAnalyzerServiceTest {

    private FileAnalyzerService fileAnalyzerService = new FileAnalyzerService();
    private String pathToTestFile = "C:/file.txt";

    @Test
    void getCountOfOccurrencesWordAtFile() {
        assertEquals(7, fileAnalyzerService.getCountOfOccurrencesWordAtFile(pathToTestFile, "method"));
    }

    @Test
    void getSentencesThatExistWord() {
        assertEquals(6, fileAnalyzerService.getSentencesThatExistWord(pathToTestFile, "method").size());
    }
}