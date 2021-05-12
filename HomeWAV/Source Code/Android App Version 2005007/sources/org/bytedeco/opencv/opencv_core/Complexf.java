package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::Complex<float>"})
@NoOffset
public class Complexf extends FloatPointer {
    private native void allocate();

    private native void allocate(float f);

    private native void allocate(float f, float f2);

    @ByVal
    public native Complexf conj();

    public native float im();

    public native Complexf im(float f);

    public native float re();

    public native Complexf re(float f);

    static {
        Loader.load();
    }

    public Complexf(Pointer pointer) {
        super(pointer);
    }

    public Complexf() {
        super((Pointer) null);
        allocate();
    }

    public Complexf(float f, float f2) {
        super((Pointer) null);
        allocate(f, f2);
    }

    public Complexf(float f) {
        super((Pointer) null);
        allocate(f);
    }
}
