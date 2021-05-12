package org.bytedeco.opencv.opencv_text;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.SizeVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_text;

@Namespace("cv::text")
@Properties(inherit = {opencv_text.class})
public class TextDetectorCNN extends TextDetector {
    @opencv_core.Ptr
    public static native TextDetectorCNN create(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    public static native TextDetectorCNN create(@opencv_core.Str String str, @opencv_core.Str String str2, @ByVal SizeVector sizeVector);

    @opencv_core.Ptr
    public static native TextDetectorCNN create(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    public static native TextDetectorCNN create(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByVal SizeVector sizeVector);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @ByRef FloatVector floatVector);

    public native void detect(@ByVal Mat mat, @ByRef RectVector rectVector, @ByRef FloatVector floatVector);

    public native void detect(@ByVal UMat uMat, @ByRef RectVector rectVector, @ByRef FloatVector floatVector);

    static {
        Loader.load();
    }

    public TextDetectorCNN(Pointer pointer) {
        super(pointer);
    }
}
