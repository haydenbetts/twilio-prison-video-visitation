package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class ClfOnlineStump extends Pointer {
    private native void allocate();

    private native void allocate(int i);

    public native float _e0();

    public native ClfOnlineStump _e0(float f);

    public native float _e1();

    public native ClfOnlineStump _e1(float f);

    public native float _lRate();

    public native ClfOnlineStump _lRate(float f);

    public native float _log_n0();

    public native ClfOnlineStump _log_n0(float f);

    public native float _log_n1();

    public native ClfOnlineStump _log_n1(float f);

    public native float _mu0();

    public native ClfOnlineStump _mu0(float f);

    public native float _mu1();

    public native ClfOnlineStump _mu1(float f);

    public native float _q();

    public native ClfOnlineStump _q(float f);

    public native int _s();

    public native ClfOnlineStump _s(int i);

    public native float _sig0();

    public native ClfOnlineStump _sig0(float f);

    public native float _sig1();

    public native ClfOnlineStump _sig1(float f);

    @Cast({"bool"})
    public native boolean classify(@ByRef @Const Mat mat, int i);

    public native float classifyF(@ByRef @Const Mat mat, int i);

    @StdVector
    public native FloatPointer classifySetF(@ByRef @Const Mat mat);

    public native void init();

    public native void update(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    static {
        Loader.load();
    }

    public ClfOnlineStump(Pointer pointer) {
        super(pointer);
    }

    public ClfOnlineStump() {
        super((Pointer) null);
        allocate();
    }

    public ClfOnlineStump(int i) {
        super((Pointer) null);
        allocate(i);
    }
}
