package com.iryna.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileAnalyzer {
    public static void main(String[] args) {

        FileAnalyzerService fileAnalyzerService = new FileAnalyzerService();

        Scanner in = new Scanner(System.in);
        System.out.print("Input file path: ");
        String path = in.nextLine();
        System.out.print("Input file word: ");
        String word = in.nextLine();

        System.out.println("Count of word at file: " + fileAnalyzerService.getCountOfOccurrencesWordAtFile(path, word));
        System.out.println("________________________________________________________________________");
        for (String s : fileAnalyzerService.getSentencesThatExistWord(path, word)) {
            System.out.println(s);
        }
    }
}

class FileAnalyzerService {

    private ArrayList<String> sentences = new ArrayList<>();

    private ArrayList<String> getFileContent(String path) {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            InputStream inputStream = new FileInputStream(path);
            int content;
            while ((content = inputStream.read()) != -1) {
                stringBuilder.append((char)content);
                if ((content == '.') || (content == '?') || (content == '!')) {
                    sentences.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                }
            }
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Exception while read file content");
        }
        return sentences;
    }

    public int getCountOfOccurrencesWordAtFile(String pathToFile, String word) {
        getFileContent(pathToFile);
        return getCountOfWord(word);
    }

    public ArrayList<String> getSentencesThatExistWord(String pathToFile, String word) {
        getFileContent(pathToFile);
        ArrayList<String> result = new ArrayList<>();
        for (String sentence: sentences) {
            if (isSentenceExistWord(sentence, word)) {
                result.add(sentence);
            }
        }
        return result;
    }

    private boolean isSentenceExistWord(String sentence, String word) {
        String[] words = sentence.split("\\W+");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    private int getCountOfWord(String word) {
        int wordsCounter = 0;
        for (String s: sentences) {
            String[] words = s.split("\\W+");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word)) {
                    wordsCounter++;
                }
            }
        }
        return wordsCounter;
    }
}
