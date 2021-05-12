package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_shape.AffineTransformer;
import org.bytedeco.opencv.opencv_shape.HausdorffDistanceExtractor;
import org.bytedeco.opencv.opencv_shape.HistogramCostExtractor;
import org.bytedeco.opencv.opencv_shape.ShapeContextDistanceExtractor;
import org.bytedeco.opencv.opencv_shape.ShapeTransformer;
import org.bytedeco.opencv.opencv_shape.ThinPlateSplineShapeTransformer;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_shape extends org.bytedeco.opencv.presets.opencv_shape {
    @Namespace("cv")
    public static native float EMDL1(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native float EMDL1(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native float EMDL1(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native AffineTransformer createAffineTransformer(@Cast({"bool"}) boolean z);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HistogramCostExtractor createChiHistogramCostExtractor();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HistogramCostExtractor createChiHistogramCostExtractor(int i, float f);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HistogramCostExtractor createEMDHistogramCostExtractor();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HistogramCostExtractor createEMDHistogramCostExtractor(int i, int i2, float f);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HistogramCostExtractor createEMDL1HistogramCostExtractor();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HistogramCostExtractor createEMDL1HistogramCostExtractor(int i, float f);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HausdorffDistanceExtractor createHausdorffDistanceExtractor();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HausdorffDistanceExtractor createHausdorffDistanceExtractor(int i, float f);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HistogramCostExtractor createNormHistogramCostExtractor();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native HistogramCostExtractor createNormHistogramCostExtractor(int i, int i2, float f);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native ShapeContextDistanceExtractor createShapeContextDistanceExtractor();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native ShapeContextDistanceExtractor createShapeContextDistanceExtractor(int i, int i2, float f, float f2, int i3, @opencv_core.Ptr HistogramCostExtractor histogramCostExtractor, @opencv_core.Ptr ShapeTransformer shapeTransformer);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native ThinPlateSplineShapeTransformer createThinPlateSplineShapeTransformer();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native ThinPlateSplineShapeTransformer createThinPlateSplineShapeTransformer(double d);

    static {
        Loader.load();
    }
}
