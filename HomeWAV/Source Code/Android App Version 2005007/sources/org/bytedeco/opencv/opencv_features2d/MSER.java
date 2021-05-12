package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.PointVectorVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
public class MSER extends Feature2D {
    @opencv_core.Ptr
    public static native MSER create();

    @opencv_core.Ptr
    public static native MSER create(int i, int i2, int i3, double d, double d2, int i4, double d3, double d4, int i5);

    public native void detectRegions(@ByVal GpuMat gpuMat, @ByRef PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    public native void detectRegions(@ByVal Mat mat, @ByRef PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    public native void detectRegions(@ByVal UMat uMat, @ByRef PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @opencv_core.Str
    public native BytePointer getDefaultName();

    public native int getDelta();

    public native int getMaxArea();

    public native int getMinArea();

    @Cast({"bool"})
    public native boolean getPass2Only();

    public native void setDelta(int i);

    public native void setMaxArea(int i);

    public native void setMinArea(int i);

    public native void setPass2Only(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public MSER(Pointer pointer) {
        super(pointer);
    }
}
