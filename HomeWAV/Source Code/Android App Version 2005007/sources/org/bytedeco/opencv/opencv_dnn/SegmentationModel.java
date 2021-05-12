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
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class SegmentationModel extends Model {
    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, @opencv_core.Str String str2);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    private native void allocate(@ByRef @Const Net net2);

    public native void segment(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void segment(@ByVal Mat mat, @ByVal Mat mat2);

    public native void segment(@ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public SegmentationModel(Pointer pointer) {
        super(pointer);
    }

    public SegmentationModel(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2) {
        super((Pointer) null);
        allocate(bytePointer, bytePointer2);
    }

    public SegmentationModel(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public SegmentationModel(@opencv_core.Str String str, @opencv_core.Str String str2) {
        super((Pointer) null);
        allocate(str, str2);
    }

    public SegmentationModel(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }

    public SegmentationModel(@ByRef @Const Net net2) {
        super((Pointer) null);
        allocate(net2);
    }
}
