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
@Name({"std::vector<std::vector<int> >"})
public class IntVectorVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @Index(function = "at")
    public native int get(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    public native IntVectorVector put(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2, int i);

    @ByRef
    @Name({"operator ="})
    public native IntVectorVector put(@ByRef IntVectorVector intVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    @Index(function = "at")
    public native void resize(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    public native long size();

    @Index(function = "at")
    public native long size(@Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public IntVectorVector(Pointer pointer) {
        super(pointer);
    }

    public IntVectorVector(int[]... iArr) {
        this((long) iArr.length);
        put(iArr);
    }

    public IntVectorVector() {
        allocate();
    }

    public IntVectorVector(long j) {
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

    public int[][] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        int[][] iArr = new int[size][];
        for (int i = 0; i < size; i++) {
            long j = (long) i;
            iArr[i] = new int[(size(j) < 2147483647L ? (int) size(j) : Integer.MAX_VALUE)];
            for (int i2 = 0; i2 < iArr[i].length; i2++) {
                iArr[i][i2] = get(j, (long) i2);
            }
        }
        return iArr;
    }

    public String toString() {
        return Arrays.deepToString(get());
    }

    public IntVectorVector put(int[]... iArr) {
        if (size() != ((long) iArr.length)) {
            resize((long) iArr.length);
        }
        for (int i = 0; i < iArr.length; i++) {
            long j = (long) i;
            if (size(j) != ((long) iArr[i].length)) {
                resize(j, (long) iArr[i].length);
            }
            for (int i2 = 0; i2 < iArr[i].length; i2++) {
                long j2 = j;
                put(j2, (long) i2, iArr[i][i2]);
            }
        }
        return this;
    }
}
