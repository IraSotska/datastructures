package com.iryna.io;

import java.io.OutputStream;

public class ByteArrayOutputStream extends OutputStream {

    private final byte[] data = new byte[1000];
    private int currentPosition;

    @Override
    public void write(int b) {
        data[currentPosition] = (byte) b;
        currentPosition++;
    }

    @Override
    public void write(byte b[]) {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte b[], int off, int len) {
        for (int i = 0; i < len; i++) {
            data[currentPosition] = b[off + i];
            currentPosition++;
        }
    }

    @Override
    public String toString() {
        return new String(data, 0, currentPosition);
    }
}
