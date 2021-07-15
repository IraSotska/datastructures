package com.iryna.file;

import com.iryna.datastructures.list.ArrayList;
import java.io.*;

public class FileManager {

    private static int fileCounter;
    private static int directoryCounter;
    private static ArrayList<File> directoriesForRemove = new ArrayList<>();

    public static int countFiles(String path) {
        addFileCountAtPath(path);
        return fileCounter;
    }

    public static void copy(String from, String to) {
        copyAllDirectories(from,to);
    }

    private static void copyAllDirectories (String from, String to) {
        for (File innerFile : new File(from).listFiles()) {
            File fileTo = new File(to + "//" + innerFile.getName());
            if(innerFile.isDirectory()) {
                fileTo.mkdir();
                copyAllDirectories(innerFile.getPath(),to + "//" + innerFile.getName());
            }
            else if(innerFile.isFile()) {
                try {
                    fileTo.createNewFile();
                } catch (IOException exception) {
                    throw new RuntimeException("exception while creating new file");
                }
                copyFileContent(innerFile.getPath(), to + "//" + innerFile.getName());
            }
        }
    }

    private static void copyFileContent(String from, String to) {
        try {
            FileInputStream fileInputStream = new FileInputStream(from);
            FileOutputStream fileOutputStream = new FileOutputStream(to);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("exception while copy file content");
        }
    }

    public static int countDirectories(String path) {
        addDirectoryCountAtPath(path);
        return directoryCounter;
    }

    public static void move(String from, String to) {
        copy(from, to);
        removeAllFromFile(from);
        for (int i = directoriesForRemove.size() - 1; i >= 0; i--) {
            ((File)directoriesForRemove.get(i)).delete();
        }
    }

    private static void addFileCountAtPath(String path) {
        for (File innerFile : new File(path).listFiles()) {
            if(innerFile.isFile()) {
                fileCounter++;
            }
            else if (innerFile.isDirectory()) {
                addFileCountAtPath(innerFile.getPath());
            }
        }
    }

    private static void addDirectoryCountAtPath(String path) {
        for (File innerFile : new File(path).listFiles()) {
            if(innerFile.isDirectory()) {
                directoryCounter++;
                addDirectoryCountAtPath(innerFile.getPath());
            }
        }
    }

    private static void removeAllFromFile(String path) {
        for (File innerFile : new File(path).listFiles()) {
            if(innerFile.isDirectory()) {
                directoriesForRemove.add(innerFile);
                removeAllFromFile(innerFile.getPath());
            }
            else if(innerFile.isFile()) {
                innerFile.delete();
            }
        }
    }
}
