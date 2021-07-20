package com.iryna.net.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private static final int PORT = 3000;

    public static void main(String[] args) {
        System.out.println(sendMessageToEchoServer("Hello 2"));
    }

    protected static String sendMessageToEchoServer(String message) {

        try (Socket socket = new Socket("localhost", PORT);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
             BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());) {

            byte[] buffer = new byte[100];
            bufferedOutputStream.write(message.getBytes());
            bufferedOutputStream.flush();

            int count = bufferedInputStream.read(buffer);
            return new String(buffer, 0, count);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        throw new RuntimeException();
    }
}
