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
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class ClassificationModel extends Model {
    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, @opencv_core.Str String str2);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    private native void allocate(@ByRef @Const Net net2);

    @ByVal
    public native IntFloatPair classify(@ByVal GpuMat gpuMat);

    @ByVal
    public native IntFloatPair classify(@ByVal Mat mat);

    @ByVal
    public native IntFloatPair classify(@ByVal UMat uMat);

    public native void classify(@ByVal GpuMat gpuMat, @ByRef IntBuffer intBuffer, @ByRef FloatBuffer floatBuffer);

    public native void classify(@ByVal GpuMat gpuMat, @ByRef IntPointer intPointer, @ByRef FloatPointer floatPointer);

    public native void classify(@ByVal GpuMat gpuMat, @ByRef int[] iArr, @ByRef float[] fArr);

    public native void classify(@ByVal Mat mat, @ByRef IntBuffer intBuffer, @ByRef FloatBuffer floatBuffer);

    public native void classify(@ByVal Mat mat, @ByRef IntPointer intPointer, @ByRef FloatPointer floatPointer);

    public native void classify(@ByVal Mat mat, @ByRef int[] iArr, @ByRef float[] fArr);

    public native void classify(@ByVal UMat uMat, @ByRef IntBuffer intBuffer, @ByRef FloatBuffer floatBuffer);

    public native void classify(@ByVal UMat uMat, @ByRef IntPointer intPointer, @ByRef FloatPointer floatPointer);

    public native void classify(@ByVal UMat uMat, @ByRef int[] iArr, @ByRef float[] fArr);

    static {
        Loader.load();
    }

    public ClassificationModel(Pointer pointer) {
        super(pointer);
    }

    public ClassificationModel(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2) {
        super((Pointer) null);
        allocate(bytePointer, bytePointer2);
    }

    public ClassificationModel(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public ClassificationModel(@opencv_core.Str String str, @opencv_core.Str String str2) {
        super((Pointer) null);
        allocate(str, str2);
    }

    public ClassificationModel(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }

    public ClassificationModel(@ByRef @Const Net net2) {
        super((Pointer) null);
        allocate(net2);
    }
}
