package com.iryna.net.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 3000;

    public static void main(String[] args) {
        startEchoServer();
    }

    protected static void startEchoServer() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());) {
                byte[] buffer = new byte[100];
                int count = bufferedInputStream.read(buffer);
                bufferedOutputStream.write(("Echo: " + new String(buffer, 0, count)).getBytes());
                bufferedOutputStream.flush();

            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
