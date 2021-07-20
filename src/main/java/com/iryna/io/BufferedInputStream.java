package com.iryna.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class BufferedInputStream extends InputStream {

    private static final int DEFAULT_BUFFER_SIZE = 3;
    private final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    private int currentByte = 0;
    private final InputStream target;

    public BufferedInputStream(InputStream target) {
        this.target = target;
        getNextBytes();
    }

    @Override
    public int read(byte[] b) {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) {

        int byteCounter = 0;

        if (DEFAULT_BUFFER_SIZE - currentByte < len) {
            for (int i = 0; i < len; i++) {
                checkIfNeedToGetNextBytes();
                if (buffer[currentByte] == 0) {
                    return byteCounter;
                }
                b[i] = buffer[currentByte];
                currentByte++;
                byteCounter++;
            }
        }
        return byteCounter;
    }

    @Override
    public void close() throws IOException {
        Arrays.fill(buffer, (byte) 0);
        currentByte = 0;
        target.close();
    }

    @Override
    public int read() {
        checkIfNeedToGetNextBytes();
        if (buffer[currentByte] != 0) {
            int current = buffer[currentByte];
            currentByte++;
            return current;
        }
        return -1;
    }

    private void getNextBytes() {
        try {
            int readBytes = target.read(buffer);
            if ((readBytes < DEFAULT_BUFFER_SIZE) && (readBytes > 0)) {
                System.arraycopy(buffer, 0, buffer, 0, readBytes);
                for (int i = readBytes; i < DEFAULT_BUFFER_SIZE; i++) {
                    buffer[i] = 0;
                }
                buffer[readBytes] = -1;
            }
        } catch (IOException exception) {
            throw new RuntimeException("Cant read next bytes ", exception);
        }
    }

    private void checkIfNeedToGetNextBytes() {
        if (currentByte == DEFAULT_BUFFER_SIZE) {
            currentByte = 0;
            getNextBytes();
        }
    }
}
