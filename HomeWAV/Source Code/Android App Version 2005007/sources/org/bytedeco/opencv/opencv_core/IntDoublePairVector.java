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
@Name({"std::vector<std::pair<int,double> >"})
public class IntDoublePairVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @Index(function = "at")
    public native int first(@Cast({"size_t"}) long j);

    public native IntDoublePairVector first(@Cast({"size_t"}) long j, int i);

    @ByRef
    @Name({"operator ="})
    public native IntDoublePairVector put(@ByRef IntDoublePairVector intDoublePairVector);

    public native void resize(@Cast({"size_t"}) long j);

    @Index(function = "at")
    public native double second(@Cast({"size_t"}) long j);

    public native IntDoublePairVector second(@Cast({"size_t"}) long j, double d);

    public native long size();

    static {
        Loader.load();
    }

    public IntDoublePairVector(Pointer pointer) {
        super(pointer);
    }

    public IntDoublePairVector(int[] iArr, double[] dArr) {
        this((long) Math.min(iArr.length, dArr.length));
        put(iArr, dArr);
    }

    public IntDoublePairVector() {
        allocate();
    }

    public IntDoublePairVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    public IntDoublePairVector put(int[] iArr, double[] dArr) {
        int i = 0;
        while (i < iArr.length && i < dArr.length) {
            long j = (long) i;
            first(j, iArr[i]);
            second(j, dArr[i]);
            i++;
        }
        return this;
    }
}
