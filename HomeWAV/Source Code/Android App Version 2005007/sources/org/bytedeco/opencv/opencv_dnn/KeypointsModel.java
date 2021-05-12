package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class KeypointsModel extends Model {
    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, @opencv_core.Str String str2);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    private native void allocate(@ByRef @Const Net net2);

    @ByVal
    public native Point2fVector estimate(@ByVal GpuMat gpuMat);

    @ByVal
    public native Point2fVector estimate(@ByVal GpuMat gpuMat, float f);

    @ByVal
    public native Point2fVector estimate(@ByVal Mat mat);

    @ByVal
    public native Point2fVector estimate(@ByVal Mat mat, float f);

    @ByVal
    public native Point2fVector estimate(@ByVal UMat uMat);

    @ByVal
    public native Point2fVector estimate(@ByVal UMat uMat, float f);

    static {
        Loader.load();
    }

    public KeypointsModel(Pointer pointer) {
        super(pointer);
    }

    public KeypointsModel(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2) {
        super((Pointer) null);
        allocate(bytePointer, bytePointer2);
    }

    public KeypointsModel(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public KeypointsModel(@opencv_core.Str String str, @opencv_core.Str String str2) {
        super((Pointer) null);
        allocate(str, str2);
    }

    public KeypointsModel(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }

    public KeypointsModel(@ByRef @Const Net net2) {
        super((Pointer) null);
        allocate(net2);
    }
}
