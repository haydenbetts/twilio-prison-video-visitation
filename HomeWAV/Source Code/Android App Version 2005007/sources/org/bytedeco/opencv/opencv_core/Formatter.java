package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class Formatter extends Pointer {
    public static final int FMT_C = 5;
    public static final int FMT_CSV = 2;
    public static final int FMT_DEFAULT = 0;
    public static final int FMT_MATLAB = 1;
    public static final int FMT_NUMPY = 4;
    public static final int FMT_PYTHON = 3;

    @opencv_core.Ptr
    public static native Formatter get();

    @opencv_core.Ptr
    public static native Formatter get(@Cast({"cv::Formatter::FormatType"}) int i);

    @opencv_core.Ptr
    public native Formatted format(@ByRef @Const Mat mat);

    public native void set16fPrecision();

    public native void set16fPrecision(int i);

    public native void set32fPrecision();

    public native void set32fPrecision(int i);

    public native void set64fPrecision();

    public native void set64fPrecision(int i);

    public native void setMultiline();

    public native void setMultiline(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public Formatter(Pointer pointer) {
        super(pointer);
    }
}
