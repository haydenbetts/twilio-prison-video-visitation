package org.bytedeco.opencv.opencv_bioinspired;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
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
public class Retina extends Algorithm {
    @opencv_core.Ptr
    public static native Retina create(@ByVal Size size);

    @opencv_core.Ptr
    public static native Retina create(@ByVal Size size, @Cast({"const bool"}) boolean z);

    @opencv_core.Ptr
    public static native Retina create(@ByVal Size size, @Cast({"const bool"}) boolean z, int i, @Cast({"const bool"}) boolean z2, float f, float f2);

    public native void activateContoursProcessing(@Cast({"const bool"}) boolean z);

    public native void activateMovingContoursProcessing(@Cast({"const bool"}) boolean z);

    public native void applyFastToneMapping(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void applyFastToneMapping(@ByVal Mat mat, @ByVal Mat mat2);

    public native void applyFastToneMapping(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void clearBuffers();

    @ByVal
    public native Size getInputSize();

    public native void getMagno(@ByVal GpuMat gpuMat);

    public native void getMagno(@ByVal Mat mat);

    public native void getMagno(@ByVal UMat uMat);

    @Const
    @ByVal
    public native Mat getMagnoRAW();

    public native void getMagnoRAW(@ByVal GpuMat gpuMat);

    public native void getMagnoRAW(@ByVal Mat mat);

    public native void getMagnoRAW(@ByVal UMat uMat);

    @ByVal
    public native Size getOutputSize();

    @ByVal
    public native RetinaParameters getParameters();

    public native void getParvo(@ByVal GpuMat gpuMat);

    public native void getParvo(@ByVal Mat mat);

    public native void getParvo(@ByVal UMat uMat);

    @Const
    @ByVal
    public native Mat getParvoRAW();

    public native void getParvoRAW(@ByVal GpuMat gpuMat);

    public native void getParvoRAW(@ByVal Mat mat);

    public native void getParvoRAW(@ByVal UMat uMat);

    @opencv_core.Str
    public native BytePointer printSetup();

    public native void run(@ByVal GpuMat gpuMat);

    public native void run(@ByVal Mat mat);

    public native void run(@ByVal UMat uMat);

    public native void setColorSaturation();

    public native void setColorSaturation(@Cast({"const bool"}) boolean z, float f);

    public native void setup();

    public native void setup(@opencv_core.Str String str, @Cast({"const bool"}) boolean z);

    public native void setup(@opencv_core.Str BytePointer bytePointer, @Cast({"const bool"}) boolean z);

    public native void setup(@ByVal RetinaParameters retinaParameters);

    public native void setup(@ByRef FileStorage fileStorage);

    public native void setup(@ByRef FileStorage fileStorage, @Cast({"const bool"}) boolean z);

    public native void setupIPLMagnoChannel();

    public native void setupIPLMagnoChannel(@Cast({"const bool"}) boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7);

    public native void setupOPLandIPLParvoChannel();

    public native void setupOPLandIPLParvoChannel(@Cast({"const bool"}) boolean z, @Cast({"const bool"}) boolean z2, float f, float f2, float f3, float f4, float f5, float f6, float f7);

    public native void write(@opencv_core.Str String str);

    public native void write(@opencv_core.Str BytePointer bytePointer);

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public Retina(Pointer pointer) {
        super(pointer);
    }
}
