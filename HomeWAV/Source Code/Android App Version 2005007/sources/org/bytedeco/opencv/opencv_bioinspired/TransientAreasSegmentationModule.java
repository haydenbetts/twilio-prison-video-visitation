package org.bytedeco.opencv.opencv_bioinspired;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_bioinspired;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::bioinspired")
@Properties(inherit = {opencv_bioinspired.class})
public class TransientAreasSegmentationModule extends Algorithm {
    @opencv_core.Ptr
    public static native TransientAreasSegmentationModule create(@ByVal Size size);

    public native void clearAllBuffers();

    @ByVal
    public native SegmentationParameters getParameters();

    public native void getSegmentationPicture(@ByVal GpuMat gpuMat);

    public native void getSegmentationPicture(@ByVal Mat mat);

    public native void getSegmentationPicture(@ByVal UMat uMat);

    @ByVal
    public native Size getSize();

    @opencv_core.Str
    public native BytePointer printSetup();

    public native void run(@ByVal GpuMat gpuMat);

    public native void run(@ByVal GpuMat gpuMat, int i);

    public native void run(@ByVal Mat mat);

    public native void run(@ByVal Mat mat, int i);

    public native void run(@ByVal UMat uMat);

    public native void run(@ByVal UMat uMat, int i);

    public native void setup();

    public native void setup(@opencv_core.Str String str, @Cast({"const bool"}) boolean z);

    public native void setup(@opencv_core.Str BytePointer bytePointer, @Cast({"const bool"}) boolean z);

    public native void setup(@ByVal SegmentationParameters segmentationParameters);

    public native void setup(@ByRef FileStorage fileStorage);

    public native void setup(@ByRef FileStorage fileStorage, @Cast({"const bool"}) boolean z);

    public native void write(@opencv_core.Str String str);

    public native void write(@opencv_core.Str BytePointer bytePointer);

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public TransientAreasSegmentationModule(Pointer pointer) {
        super(pointer);
    }
}
