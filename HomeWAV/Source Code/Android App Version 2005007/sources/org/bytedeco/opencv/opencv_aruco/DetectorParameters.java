package org.bytedeco.opencv.opencv_aruco;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_aruco;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::aruco")
@Properties(inherit = {opencv_aruco.class})
@NoOffset
public class DetectorParameters extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native DetectorParameters create();

    public native double adaptiveThreshConstant();

    public native DetectorParameters adaptiveThreshConstant(double d);

    public native int adaptiveThreshWinSizeMax();

    public native DetectorParameters adaptiveThreshWinSizeMax(int i);

    public native int adaptiveThreshWinSizeMin();

    public native DetectorParameters adaptiveThreshWinSizeMin(int i);

    public native int adaptiveThreshWinSizeStep();

    public native DetectorParameters adaptiveThreshWinSizeStep(int i);

    public native float aprilTagCriticalRad();

    public native DetectorParameters aprilTagCriticalRad(float f);

    public native int aprilTagDeglitch();

    public native DetectorParameters aprilTagDeglitch(int i);

    public native float aprilTagMaxLineFitMse();

    public native DetectorParameters aprilTagMaxLineFitMse(float f);

    public native int aprilTagMaxNmaxima();

    public native DetectorParameters aprilTagMaxNmaxima(int i);

    public native int aprilTagMinClusterPixels();

    public native DetectorParameters aprilTagMinClusterPixels(int i);

    public native int aprilTagMinWhiteBlackDiff();

    public native DetectorParameters aprilTagMinWhiteBlackDiff(int i);

    public native float aprilTagQuadDecimate();

    public native DetectorParameters aprilTagQuadDecimate(float f);

    public native float aprilTagQuadSigma();

    public native DetectorParameters aprilTagQuadSigma(float f);

    public native int cornerRefinementMaxIterations();

    public native DetectorParameters cornerRefinementMaxIterations(int i);

    public native int cornerRefinementMethod();

    public native DetectorParameters cornerRefinementMethod(int i);

    public native double cornerRefinementMinAccuracy();

    public native DetectorParameters cornerRefinementMinAccuracy(double d);

    public native int cornerRefinementWinSize();

    public native DetectorParameters cornerRefinementWinSize(int i);

    public native DetectorParameters detectInvertedMarker(boolean z);

    @Cast({"bool"})
    public native boolean detectInvertedMarker();

    public native double errorCorrectionRate();

    public native DetectorParameters errorCorrectionRate(double d);

    public native int markerBorderBits();

    public native DetectorParameters markerBorderBits(int i);

    public native double maxErroneousBitsInBorderRate();

    public native DetectorParameters maxErroneousBitsInBorderRate(double d);

    public native double maxMarkerPerimeterRate();

    public native DetectorParameters maxMarkerPerimeterRate(double d);

    public native double minCornerDistanceRate();

    public native DetectorParameters minCornerDistanceRate(double d);

    public native int minDistanceToBorder();

    public native DetectorParameters minDistanceToBorder(int i);

    public native double minMarkerDistanceRate();

    public native DetectorParameters minMarkerDistanceRate(double d);

    public native double minMarkerPerimeterRate();

    public native DetectorParameters minMarkerPerimeterRate(double d);

    public native double minOtsuStdDev();

    public native DetectorParameters minOtsuStdDev(double d);

    public native double perspectiveRemoveIgnoredMarginPerCell();

    public native DetectorParameters perspectiveRemoveIgnoredMarginPerCell(double d);

    public native int perspectiveRemovePixelPerCell();

    public native DetectorParameters perspectiveRemovePixelPerCell(int i);

    public native double polygonalApproxAccuracyRate();

    public native DetectorParameters polygonalApproxAccuracyRate(double d);

    static {
        Loader.load();
    }

    public DetectorParameters(Pointer pointer) {
        super(pointer);
    }

    public DetectorParameters(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public DetectorParameters position(long j) {
        return (DetectorParameters) super.position(j);
    }

    public DetectorParameters getPointer(long j) {
        return new DetectorParameters((Pointer) this).position(this.position + j);
    }

    public DetectorParameters() {
        super((Pointer) null);
        allocate();
    }
}
