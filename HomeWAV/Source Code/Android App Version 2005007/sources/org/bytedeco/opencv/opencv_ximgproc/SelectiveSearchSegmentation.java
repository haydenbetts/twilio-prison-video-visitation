package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc::segmentation")
@Properties(inherit = {opencv_ximgproc.class})
public class SelectiveSearchSegmentation extends Algorithm {
    public native void addGraphSegmentation(@opencv_core.Ptr GraphSegmentation graphSegmentation);

    public native void addImage(@ByVal GpuMat gpuMat);

    public native void addImage(@ByVal Mat mat);

    public native void addImage(@ByVal UMat uMat);

    public native void addStrategy(@opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy);

    public native void clearGraphSegmentations();

    public native void clearImages();

    public native void clearStrategies();

    public native void process(@ByRef RectVector rectVector);

    public native void setBaseImage(@ByVal GpuMat gpuMat);

    public native void setBaseImage(@ByVal Mat mat);

    public native void setBaseImage(@ByVal UMat uMat);

    public native void switchToSelectiveSearchFast();

    public native void switchToSelectiveSearchFast(int i, int i2, float f);

    public native void switchToSelectiveSearchQuality();

    public native void switchToSelectiveSearchQuality(int i, int i2, float f);

    public native void switchToSingleStrategy();

    public native void switchToSingleStrategy(int i, float f);

    static {
        Loader.load();
    }

    public SelectiveSearchSegmentation(Pointer pointer) {
        super(pointer);
    }
}
