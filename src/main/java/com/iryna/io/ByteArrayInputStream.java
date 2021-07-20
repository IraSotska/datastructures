package com.iryna.io;

import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStream extends InputStream {
    public ByteArrayInputStream(byte[] bytes) {
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
