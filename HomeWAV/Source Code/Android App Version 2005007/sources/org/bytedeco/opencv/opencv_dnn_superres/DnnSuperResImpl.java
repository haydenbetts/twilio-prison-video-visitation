package org.bytedeco.opencv.opencv_dnn_superres;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn_superres;

@Namespace("cv::dnn_superres")
@Properties(inherit = {opencv_dnn_superres.class})
@NoOffset
public class DnnSuperResImpl extends Pointer {
    private native void allocate();

    private native void allocate(@opencv_core.Str String str, int i);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i);

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native DnnSuperResImpl create();

    @opencv_core.Str
    public native BytePointer getAlgorithm();

    public native int getScale();

    public native void readModel(@opencv_core.Str String str);

    public native void readModel(@opencv_core.Str String str, @opencv_core.Str String str2);

    public native void readModel(@opencv_core.Str BytePointer bytePointer);

    public native void readModel(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native void setModel(@opencv_core.Str String str, int i);

    public native void setModel(@opencv_core.Str BytePointer bytePointer, int i);

    public native void setPreferableBackend(int i);

    public native void setPreferableTarget(int i);

    public native void upsample(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void upsample(@ByVal Mat mat, @ByVal Mat mat2);

    public native void upsample(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void upsampleMultioutput(@ByVal GpuMat gpuMat, @ByRef MatVector matVector, @StdVector IntBuffer intBuffer, @ByRef @Const StringVector stringVector);

    public native void upsampleMultioutput(@ByVal GpuMat gpuMat, @ByRef MatVector matVector, @StdVector IntPointer intPointer, @ByRef @Const StringVector stringVector);

    public native void upsampleMultioutput(@ByVal GpuMat gpuMat, @ByRef MatVector matVector, @StdVector int[] iArr, @ByRef @Const StringVector stringVector);

    public native void upsampleMultioutput(@ByVal Mat mat, @ByRef MatVector matVector, @StdVector IntBuffer intBuffer, @ByRef @Const StringVector stringVector);

    public native void upsampleMultioutput(@ByVal Mat mat, @ByRef MatVector matVector, @StdVector IntPointer intPointer, @ByRef @Const StringVector stringVector);

    public native void upsampleMultioutput(@ByVal Mat mat, @ByRef MatVector matVector, @StdVector int[] iArr, @ByRef @Const StringVector stringVector);

    public native void upsampleMultioutput(@ByVal UMat uMat, @ByRef MatVector matVector, @StdVector IntBuffer intBuffer, @ByRef @Const StringVector stringVector);

    public native void upsampleMultioutput(@ByVal UMat uMat, @ByRef MatVector matVector, @StdVector IntPointer intPointer, @ByRef @Const StringVector stringVector);

    public native void upsampleMultioutput(@ByVal UMat uMat, @ByRef MatVector matVector, @StdVector int[] iArr, @ByRef @Const StringVector stringVector);

    static {
        Loader.load();
    }

    public DnnSuperResImpl(Pointer pointer) {
        super(pointer);
    }

    public DnnSuperResImpl(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public DnnSuperResImpl position(long j) {
        return (DnnSuperResImpl) super.position(j);
    }

    public DnnSuperResImpl getPointer(long j) {
        return new DnnSuperResImpl((Pointer) this).position(this.position + j);
    }

    public DnnSuperResImpl() {
        super((Pointer) null);
        allocate();
    }

    public DnnSuperResImpl(@opencv_core.Str BytePointer bytePointer, int i) {
        super((Pointer) null);
        allocate(bytePointer, i);
    }

    public DnnSuperResImpl(@opencv_core.Str String str, int i) {
        super((Pointer) null);
        allocate(str, i);
    }
}
