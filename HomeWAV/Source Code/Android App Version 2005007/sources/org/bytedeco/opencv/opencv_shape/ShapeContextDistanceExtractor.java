package org.bytedeco.opencv.opencv_shape;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_shape;

@Namespace("cv")
@Properties(inherit = {opencv_shape.class})
public class ShapeContextDistanceExtractor extends ShapeDistanceExtractor {
    public native int getAngularBins();

    public native float getBendingEnergyWeight();

    @opencv_core.Ptr
    public native HistogramCostExtractor getCostExtractor();

    public native float getImageAppearanceWeight();

    public native void getImages(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void getImages(@ByVal Mat mat, @ByVal Mat mat2);

    public native void getImages(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native float getInnerRadius();

    public native int getIterations();

    public native float getOuterRadius();

    public native int getRadialBins();

    @Cast({"bool"})
    public native boolean getRotationInvariant();

    public native float getShapeContextWeight();

    public native float getStdDev();

    @opencv_core.Ptr
    public native ShapeTransformer getTransformAlgorithm();

    public native void setAngularBins(int i);

    public native void setBendingEnergyWeight(float f);

    public native void setCostExtractor(@opencv_core.Ptr HistogramCostExtractor histogramCostExtractor);

    public native void setImageAppearanceWeight(float f);

    public native void setImages(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void setImages(@ByVal Mat mat, @ByVal Mat mat2);

    public native void setImages(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void setInnerRadius(float f);

    public native void setIterations(int i);

    public native void setOuterRadius(float f);

    public native void setRadialBins(int i);

    public native void setRotationInvariant(@Cast({"bool"}) boolean z);

    public native void setShapeContextWeight(float f);

    public native void setStdDev(float f);

    public native void setTransformAlgorithm(@opencv_core.Ptr ShapeTransformer shapeTransformer);

    static {
        Loader.load();
    }

    public ShapeContextDistanceExtractor(Pointer pointer) {
        super(pointer);
    }
}
