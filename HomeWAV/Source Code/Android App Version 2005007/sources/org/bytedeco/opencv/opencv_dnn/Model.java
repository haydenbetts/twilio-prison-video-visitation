package org.bytedeco.opencv.opencv_dnn;

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
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class Model extends Net {
    private native void allocate();

    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, @opencv_core.Str String str2);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    private native void allocate(@ByRef @Const Net net2);

    private native void allocateArray(long j);

    public native void predict(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector);

    public native void predict(@ByVal GpuMat gpuMat, @ByVal MatVector matVector);

    public native void predict(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector);

    public native void predict(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector);

    public native void predict(@ByVal Mat mat, @ByVal MatVector matVector);

    public native void predict(@ByVal Mat mat, @ByVal UMatVector uMatVector);

    public native void predict(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector);

    public native void predict(@ByVal UMat uMat, @ByVal MatVector matVector);

    public native void predict(@ByVal UMat uMat, @ByVal UMatVector uMatVector);

    @ByRef
    public native Model setInputCrop(@Cast({"bool"}) boolean z);

    @ByRef
    public native Model setInputMean(@ByRef @Const Scalar scalar);

    public native void setInputParams();

    public native void setInputParams(double d, @ByRef(nullValue = "cv::Size()") @Const Size size, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @ByRef
    public native Model setInputScale(double d);

    @ByRef
    public native Model setInputSize(int i, int i2);

    @ByRef
    public native Model setInputSize(@ByRef @Const Size size);

    @ByRef
    public native Model setInputSwapRB(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public Model(Pointer pointer) {
        super(pointer);
    }

    public Model(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Model position(long j) {
        return (Model) super.position(j);
    }

    public Model getPointer(long j) {
        return new Model((Net) this).position(this.position + j);
    }

    public Model() {
        super((Pointer) null);
        allocate();
    }

    public Model(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2) {
        super((Pointer) null);
        allocate(bytePointer, bytePointer2);
    }

    public Model(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public Model(@opencv_core.Str String str, @opencv_core.Str String str2) {
        super((Pointer) null);
        allocate(str, str2);
    }

    public Model(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }

    public Model(@ByRef @Const Net net2) {
        super((Pointer) null);
        allocate(net2);
    }
}
