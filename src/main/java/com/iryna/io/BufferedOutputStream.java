package com.iryna.io;

import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream {

    private final OutputStream target;
    private final int DEFAULT_BUFFER_SIZE = 3;
    private final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    private int currentBufferPosition;

    public BufferedOutputStream(OutputStream target) {
        this.target = target;
    }

    @Override
    public void write(int b) {
        if(currentBufferPosition + 1 == DEFAULT_BUFFER_SIZE) {
            saveBuffer();
        }
        buffer[currentBufferPosition] = (byte) b;
        currentBufferPosition++;
    }

    @Override
    public void write(byte[] b) {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) {

        for (int i = 0; i < len; i++) {
            if(currentBufferPosition == DEFAULT_BUFFER_SIZE) {
                saveBuffer();
            }
            buffer[currentBufferPosition] = b[i + off];
            currentBufferPosition++;
        }
    }

    @Override
    public void flush() throws IOException {
        saveBuffer();
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    private void saveBuffer() {
        try {
            target.write(buffer, 0, currentBufferPosition);
            currentBufferPosition = 0;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
