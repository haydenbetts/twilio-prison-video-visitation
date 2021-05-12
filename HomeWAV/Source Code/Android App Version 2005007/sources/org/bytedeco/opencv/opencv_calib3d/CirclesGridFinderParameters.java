package org.bytedeco.opencv.opencv_calib3d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Size2f;
import org.bytedeco.opencv.presets.opencv_calib3d;

@Namespace("cv")
@Properties(inherit = {opencv_calib3d.class})
@NoOffset
public class CirclesGridFinderParameters extends Pointer {
    public static final int ASYMMETRIC_GRID = 1;
    public static final int SYMMETRIC_GRID = 0;

    private native void allocate();

    private native void allocateArray(long j);

    public native float convexHullFactor();

    public native CirclesGridFinderParameters convexHullFactor(float f);

    public native CirclesGridFinderParameters densityNeighborhoodSize(Size2f size2f);

    @ByRef
    public native Size2f densityNeighborhoodSize();

    public native float edgeGain();

    public native CirclesGridFinderParameters edgeGain(float f);

    public native float edgePenalty();

    public native CirclesGridFinderParameters edgePenalty(float f);

    public native float existingVertexGain();

    public native CirclesGridFinderParameters existingVertexGain(float f);

    @Cast({"cv::CirclesGridFinderParameters::GridType"})
    public native int gridType();

    public native CirclesGridFinderParameters gridType(int i);

    public native int keypointScale();

    public native CirclesGridFinderParameters keypointScale(int i);

    public native int kmeansAttempts();

    public native CirclesGridFinderParameters kmeansAttempts(int i);

    public native float maxRectifiedDistance();

    public native CirclesGridFinderParameters maxRectifiedDistance(float f);

    public native float minDensity();

    public native CirclesGridFinderParameters minDensity(float f);

    public native int minDistanceToAddKeypoint();

    public native CirclesGridFinderParameters minDistanceToAddKeypoint(int i);

    public native float minGraphConfidence();

    public native CirclesGridFinderParameters minGraphConfidence(float f);

    public native float minRNGEdgeSwitchDist();

    public native CirclesGridFinderParameters minRNGEdgeSwitchDist(float f);

    public native float squareSize();

    public native CirclesGridFinderParameters squareSize(float f);

    public native float vertexGain();

    public native CirclesGridFinderParameters vertexGain(float f);

    public native float vertexPenalty();

    public native CirclesGridFinderParameters vertexPenalty(float f);

    static {
        Loader.load();
    }

    public CirclesGridFinderParameters(Pointer pointer) {
        super(pointer);
    }

    public CirclesGridFinderParameters(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CirclesGridFinderParameters position(long j) {
        return (CirclesGridFinderParameters) super.position(j);
    }

    public CirclesGridFinderParameters getPointer(long j) {
        return new CirclesGridFinderParameters((Pointer) this).position(this.position + j);
    }

    public CirclesGridFinderParameters() {
        super((Pointer) null);
        allocate();
    }
}
