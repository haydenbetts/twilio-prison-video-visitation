package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::Complex<double>"})
@NoOffset
public class Complexd extends DoublePointer {
    private native void allocate();

    private native void allocate(double d);

    private native void allocate(double d, double d2);

    @ByVal
    public native Complexd conj();

    public native double im();

    public native Complexd im(double d);

    public native double re();

    public native Complexd re(double d);

    static {
        Loader.load();
    }

    public Complexd(Pointer pointer) {
        super(pointer);
    }

    public Complexd() {
        super((Pointer) null);
        allocate();
    }

    public Complexd(double d, double d2) {
        super((Pointer) null);
        allocate(d, d2);
    }

    public Complexd(double d) {
        super((Pointer) null);
        allocate(d);
    }
}
