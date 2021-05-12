package org.bytedeco.opencv.opencv_core;

import java.util.Arrays;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"std::vector<std::vector<char> >"})
public class ByteVectorVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @Index(function = "at")
    @Cast({"char"})
    public native byte get(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    public native ByteVectorVector put(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2, byte b);

    @ByRef
    @Name({"operator ="})
    public native ByteVectorVector put(@ByRef ByteVectorVector byteVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    @Index(function = "at")
    public native void resize(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    public native long size();

    @Index(function = "at")
    public native long size(@Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public ByteVectorVector(Pointer pointer) {
        super(pointer);
    }

    public ByteVectorVector(byte[]... bArr) {
        this((long) bArr.length);
        put(bArr);
    }

    public ByteVectorVector() {
        allocate();
    }

    public ByteVectorVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    public boolean empty(@Cast({"size_t"}) long j) {
        return size(j) == 0;
    }

    public void clear(@Cast({"size_t"}) long j) {
        resize(j, 0);
    }

    public byte[][] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        byte[][] bArr = new byte[size][];
        for (int i = 0; i < size; i++) {
            long j = (long) i;
            bArr[i] = new byte[(size(j) < 2147483647L ? (int) size(j) : Integer.MAX_VALUE)];
            for (int i2 = 0; i2 < bArr[i].length; i2++) {
                bArr[i][i2] = get(j, (long) i2);
            }
        }
        return bArr;
    }

    public String toString() {
        return Arrays.deepToString(get());
    }

    public ByteVectorVector put(byte[]... bArr) {
        if (size() != ((long) bArr.length)) {
            resize((long) bArr.length);
        }
        for (int i = 0; i < bArr.length; i++) {
            long j = (long) i;
            if (size(j) != ((long) bArr[i].length)) {
                resize(j, (long) bArr[i].length);
            }
            for (int i2 = 0; i2 < bArr[i].length; i2++) {
                long j2 = j;
                put(j2, (long) i2, bArr[i][i2]);
            }
        }
        return this;
    }
}
