package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class TermCriteria extends Pointer {
    public static final int COUNT = 1;
    public static final int EPS = 2;
    public static final int MAX_ITER = 1;

    private native void allocate();

    private native void allocate(int i, int i2, double d);

    private native void allocateArray(long j);

    public native double epsilon();

    public native TermCriteria epsilon(double d);

    @Cast({"bool"})
    public native boolean isValid();

    public native int maxCount();

    public native TermCriteria maxCount(int i);

    public native int type();

    public native TermCriteria type(int i);

    static {
        Loader.load();
    }

    public TermCriteria(Pointer pointer) {
        super(pointer);
    }

    public TermCriteria(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TermCriteria position(long j) {
        return (TermCriteria) super.position(j);
    }

    public TermCriteria getPointer(long j) {
        return new TermCriteria((Pointer) this).position(this.position + j);
    }

    public TermCriteria() {
        super((Pointer) null);
        allocate();
    }

    public TermCriteria(int i, int i2, double d) {
        super((Pointer) null);
        allocate(i, i2, d);
    }
}
