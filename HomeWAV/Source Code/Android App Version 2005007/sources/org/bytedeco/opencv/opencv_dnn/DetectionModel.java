package org.bytedeco.opencv.opencv_dnn;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class DetectionModel extends Model {
    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, @opencv_core.Str String str2);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    private native void allocate(@ByRef @Const Net net2);

    public native void detect(@ByVal GpuMat gpuMat, @StdVector IntBuffer intBuffer, @StdVector FloatBuffer floatBuffer, @ByRef RectVector rectVector);

    public native void detect(@ByVal GpuMat gpuMat, @StdVector IntBuffer intBuffer, @StdVector FloatBuffer floatBuffer, @ByRef RectVector rectVector, float f, float f2);

    public native void detect(@ByVal GpuMat gpuMat, @StdVector IntPointer intPointer, @StdVector FloatPointer floatPointer, @ByRef RectVector rectVector);

    public native void detect(@ByVal GpuMat gpuMat, @StdVector IntPointer intPointer, @StdVector FloatPointer floatPointer, @ByRef RectVector rectVector, float f, float f2);

    public native void detect(@ByVal GpuMat gpuMat, @StdVector int[] iArr, @StdVector float[] fArr, @ByRef RectVector rectVector);

    public native void detect(@ByVal GpuMat gpuMat, @StdVector int[] iArr, @StdVector float[] fArr, @ByRef RectVector rectVector, float f, float f2);

    public native void detect(@ByVal Mat mat, @StdVector IntBuffer intBuffer, @StdVector FloatBuffer floatBuffer, @ByRef RectVector rectVector);

    public native void detect(@ByVal Mat mat, @StdVector IntBuffer intBuffer, @StdVector FloatBuffer floatBuffer, @ByRef RectVector rectVector, float f, float f2);

    public native void detect(@ByVal Mat mat, @StdVector IntPointer intPointer, @StdVector FloatPointer floatPointer, @ByRef RectVector rectVector);

    public native void detect(@ByVal Mat mat, @StdVector IntPointer intPointer, @StdVector FloatPointer floatPointer, @ByRef RectVector rectVector, float f, float f2);

    public native void detect(@ByVal Mat mat, @StdVector int[] iArr, @StdVector float[] fArr, @ByRef RectVector rectVector);

    public native void detect(@ByVal Mat mat, @StdVector int[] iArr, @StdVector float[] fArr, @ByRef RectVector rectVector, float f, float f2);

    public native void detect(@ByVal UMat uMat, @StdVector IntBuffer intBuffer, @StdVector FloatBuffer floatBuffer, @ByRef RectVector rectVector);

    public native void detect(@ByVal UMat uMat, @StdVector IntBuffer intBuffer, @StdVector FloatBuffer floatBuffer, @ByRef RectVector rectVector, float f, float f2);

    public native void detect(@ByVal UMat uMat, @StdVector IntPointer intPointer, @StdVector FloatPointer floatPointer, @ByRef RectVector rectVector);

    public native void detect(@ByVal UMat uMat, @StdVector IntPointer intPointer, @StdVector FloatPointer floatPointer, @ByRef RectVector rectVector, float f, float f2);

    public native void detect(@ByVal UMat uMat, @StdVector int[] iArr, @StdVector float[] fArr, @ByRef RectVector rectVector);

    public native void detect(@ByVal UMat uMat, @StdVector int[] iArr, @StdVector float[] fArr, @ByRef RectVector rectVector, float f, float f2);

    static {
        Loader.load();
    }

    public DetectionModel(Pointer pointer) {
        super(pointer);
    }

    public DetectionModel(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2) {
        super((Pointer) null);
        allocate(bytePointer, bytePointer2);
    }

    public DetectionModel(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public DetectionModel(@opencv_core.Str String str, @opencv_core.Str String str2) {
        super((Pointer) null);
        allocate(str, str2);
    }

    public DetectionModel(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }

    public DetectionModel(@ByRef @Const Net net2) {
        super((Pointer) null);
        allocate(net2);
    }
}
