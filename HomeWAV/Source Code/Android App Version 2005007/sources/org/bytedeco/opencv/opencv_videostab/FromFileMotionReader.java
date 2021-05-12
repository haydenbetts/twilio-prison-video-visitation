package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.BoolPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class FromFileMotionReader extends ImageMotionEstimatorBase {
    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    @ByVal
    public native Mat estimate(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @ByVal
    public native Mat estimate(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @Cast({"bool*"}) BoolPointer boolPointer);

    @ByVal
    public native Mat estimate(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @Cast({"bool*"}) boolean[] zArr);

    static {
        Loader.load();
    }

    public FromFileMotionReader(Pointer pointer) {
        super(pointer);
    }

    public FromFileMotionReader(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public FromFileMotionReader(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }
}
