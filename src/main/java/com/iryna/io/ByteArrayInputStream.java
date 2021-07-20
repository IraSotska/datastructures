package com.iryna.io;

import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStream extends InputStream {

    private final byte[] data;
    private int currentPosition;

    public ByteArrayInputStream(byte[] bytes) {
        data = bytes;
    }

    @Override
    public int read(byte[] b) {
        if (data.length == 0) {
            return -1;
        }
        for (int i = 0; i < data.length; i++) {
            b[i] = data[i];
            currentPosition++;
        }
        b[data.length] = -1;
        return data.length;
    }

    @Override
    public int read(byte[] b, int off, int len) {
        for (int i = 0; i < len + off; i++) {
            if (i < off) {
                b[i] = 0;
            } else {
                b[i] = data[i - off];
            }
        }
        return len;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public int read() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
        byte result;
        if (currentPosition < data.length) {
            result = data[currentPosition];
            currentPosition++;
        } else {
            result = -1;
        }
        return result;
    }
}
