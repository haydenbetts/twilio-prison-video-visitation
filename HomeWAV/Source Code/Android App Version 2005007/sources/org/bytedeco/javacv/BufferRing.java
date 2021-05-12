package org.bytedeco.javacv;

import org.bytedeco.javacv.BufferRing.ReleasableBuffer;

public class BufferRing<B extends ReleasableBuffer> {
    private Object[] buffers;
    private int position;

    public interface BufferFactory<B extends ReleasableBuffer> {
        B create();
    }

    public interface ReleasableBuffer {
        void release();
    }

    public BufferRing(BufferFactory<B> bufferFactory, int i) {
        this.buffers = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.buffers[i2] = bufferFactory.create();
        }
        this.position = 0;
    }

    public int capacity() {
        return this.buffers.length;
    }

    public int position() {
        return this.position;
    }

    public BufferRing position(int i) {
        Object[] objArr = this.buffers;
        this.position = ((i % objArr.length) + objArr.length) % objArr.length;
        return this;
    }

    public B get() {
        return (ReleasableBuffer) this.buffers[this.position];
    }

    public B get(int i) {
        B[] bArr = this.buffers;
        return (ReleasableBuffer) bArr[(((this.position + i) % bArr.length) + bArr.length) % bArr.length];
    }

    public void release() {
        int i = 0;
        while (true) {
            Object[] objArr = this.buffers;
            if (i < objArr.length) {
                ((ReleasableBuffer) objArr[i]).release();
                i++;
            } else {
                this.buffers = null;
                return;
            }
        }
    }
}
