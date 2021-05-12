package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"std::vector<std::pair<int,int> >"})
public class IntIntPairVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @Index(function = "at")
    public native int first(@Cast({"size_t"}) long j);

    public native IntIntPairVector first(@Cast({"size_t"}) long j, int i);

    @ByRef
    @Name({"operator ="})
    public native IntIntPairVector put(@ByRef IntIntPairVector intIntPairVector);

    public native void resize(@Cast({"size_t"}) long j);

    @Index(function = "at")
    public native int second(@Cast({"size_t"}) long j);

    public native IntIntPairVector second(@Cast({"size_t"}) long j, int i);

    public native long size();

    static {
        Loader.load();
    }

    public IntIntPairVector(Pointer pointer) {
        super(pointer);
    }

    public IntIntPairVector(int[] iArr, int[] iArr2) {
        this((long) Math.min(iArr.length, iArr2.length));
        put(iArr, iArr2);
    }

    public IntIntPairVector() {
        allocate();
    }

    public IntIntPairVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    public IntIntPairVector put(int[] iArr, int[] iArr2) {
        int i = 0;
        while (i < iArr.length && i < iArr2.length) {
            long j = (long) i;
            first(j, iArr[i]);
            second(j, iArr2[i]);
            i++;
        }
        return this;
    }
}
