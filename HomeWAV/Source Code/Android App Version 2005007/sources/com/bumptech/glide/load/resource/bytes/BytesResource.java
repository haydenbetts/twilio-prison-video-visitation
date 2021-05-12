package com.bumptech.glide.load.resource.bytes;

import com.bumptech.glide.load.engine.Resource;
import java.util.Objects;

public class BytesResource implements Resource<byte[]> {
    private final byte[] bytes;

    public void recycle() {
    }

    public BytesResource(byte[] bArr) {
        Objects.requireNonNull(bArr, "Bytes must not be null");
        this.bytes = bArr;
    }

    public byte[] get() {
        return this.bytes;
    }

    public int getSize() {
        return this.bytes.length;
    }
}
