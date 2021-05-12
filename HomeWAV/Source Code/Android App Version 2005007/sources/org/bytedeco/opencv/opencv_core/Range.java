package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class Range extends Pointer {
    @ByVal
    public static native Range all();

    private native void allocate();

    private native void allocate(int i, int i2);

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean empty();

    public native int end();

    public native Range end(int i);

    public native int size();

    public native int start();

    public native Range start(int i);

    static {
        Loader.load();
    }

    public Range(Pointer pointer) {
        super(pointer);
    }

    public Range(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Range position(long j) {
        return (Range) super.position(j);
    }

    public Range getPointer(long j) {
        return new Range((Pointer) this).position(this.position + j);
    }

    public Range() {
        super((Pointer) null);
        allocate();
    }

    public Range(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }
}
