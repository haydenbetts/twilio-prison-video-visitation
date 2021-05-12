package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class RTrees extends DTrees {
    @opencv_core.Ptr
    public static native RTrees create();

    @opencv_core.Ptr
    public static native RTrees load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native RTrees load(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    public static native RTrees load(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Ptr
    public static native RTrees load(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native int getActiveVarCount();

    @Cast({"bool"})
    public native boolean getCalculateVarImportance();

    @ByVal
    public native TermCriteria getTermCriteria();

    @ByVal
    public native Mat getVarImportance();

    public native void getVotes(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    public native void getVotes(@ByVal Mat mat, @ByVal Mat mat2, int i);

    public native void getVotes(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    public native void setActiveVarCount(int i);

    public native void setCalculateVarImportance(@Cast({"bool"}) boolean z);

    public native void setTermCriteria(@ByRef @Const TermCriteria termCriteria);

    static {
        Loader.load();
    }

    public RTrees(Pointer pointer) {
        super(pointer);
    }
}
