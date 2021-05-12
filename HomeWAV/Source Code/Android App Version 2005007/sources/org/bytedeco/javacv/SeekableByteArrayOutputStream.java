package org.bytedeco.javacv;

import java.io.ByteArrayOutputStream;

public class SeekableByteArrayOutputStream extends ByteArrayOutputStream implements Seekable {
    long position;

    public void seek(long j, int i) {
        if (j < 0 || j > ((long) this.count) || i != 0) {
            throw new IllegalArgumentException();
        }
        this.position = j;
    }

    public synchronized void write(int i) {
        if (this.position < ((long) this.count)) {
            this.buf[(int) this.position] = (byte) i;
        } else {
            super.write(i);
        }
        this.position++;
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        if (this.position < ((long) this.count)) {
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        } else {
            super.write(bArr, i, i2);
            this.position = (long) this.count;
        }
    }
}
