package org.bytedeco.opencv.opencv_structured_light;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_structured_light;

@Namespace("cv::structured_light")
@Properties(inherit = {opencv_structured_light.class})
public class SinusoidalPattern extends StructuredLightPattern {
    @opencv_core.Ptr
    public static native SinusoidalPattern create();

    @opencv_core.Ptr
    public static native SinusoidalPattern create(@opencv_core.Ptr Params params);

    public native void computeDataModulationTerm(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void computeDataModulationTerm(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal Mat mat2);

    public native void computeDataModulationTerm(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void computeDataModulationTerm(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void computeDataModulationTerm(@ByVal MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2);

    public native void computeDataModulationTerm(@ByVal MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void computeDataModulationTerm(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void computeDataModulationTerm(@ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal Mat mat2);

    public native void computeDataModulationTerm(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void computePhaseMap(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    public native void computePhaseMap(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    public native void computePhaseMap(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    public native void computePhaseMap(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    public native void computePhaseMap(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    public native void computePhaseMap(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    public native void computePhaseMap(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    public native void computePhaseMap(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    public native void computePhaseMap(@ByVal MatVector matVector, @ByVal Mat mat);

    public native void computePhaseMap(@ByVal MatVector matVector, @ByVal Mat mat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    public native void computePhaseMap(@ByVal MatVector matVector, @ByVal UMat uMat);

    public native void computePhaseMap(@ByVal MatVector matVector, @ByVal UMat uMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    public native void computePhaseMap(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    public native void computePhaseMap(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    public native void computePhaseMap(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    public native void computePhaseMap(@ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    public native void computePhaseMap(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    public native void computePhaseMap(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    public native void findProCamMatches(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMatVector gpuMatVector);

    public native void findProCamMatches(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal MatVector matVector);

    public native void findProCamMatches(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal UMatVector uMatVector);

    public native void findProCamMatches(@ByVal Mat mat, @ByVal Mat mat2, @ByVal GpuMatVector gpuMatVector);

    public native void findProCamMatches(@ByVal Mat mat, @ByVal Mat mat2, @ByVal MatVector matVector);

    public native void findProCamMatches(@ByVal Mat mat, @ByVal Mat mat2, @ByVal UMatVector uMatVector);

    public native void findProCamMatches(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal GpuMatVector gpuMatVector);

    public native void findProCamMatches(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal MatVector matVector);

    public native void findProCamMatches(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMatVector uMatVector);

    public native void unwrapPhaseMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size);

    public native void unwrapPhaseMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    public native void unwrapPhaseMap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size);

    public native void unwrapPhaseMap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    public native void unwrapPhaseMap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size);

    public native void unwrapPhaseMap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    static {
        Loader.load();
    }

    public SinusoidalPattern(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native int height();

        public native Params height(int i);

        public native Params horizontal(boolean z);

        @Cast({"bool"})
        public native boolean horizontal();

        @ByRef
        public native Point2fVector markersLocation();

        public native Params markersLocation(Point2fVector point2fVector);

        public native int methodId();

        public native Params methodId(int i);

        public native int nbrOfPeriods();

        public native Params nbrOfPeriods(int i);

        public native int nbrOfPixelsBetweenMarkers();

        public native Params nbrOfPixelsBetweenMarkers(int i);

        public native Params setMarkers(boolean z);

        @Cast({"bool"})
        public native boolean setMarkers();

        public native float shiftValue();

        public native Params shiftValue(float f);

        public native int width();

        public native Params width(int i);

        static {
            Loader.load();
        }

        public Params(Pointer pointer) {
            super(pointer);
        }

        public Params(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Params position(long j) {
            return (Params) super.position(j);
        }

        public Params getPointer(long j) {
            return new Params((Pointer) this).position(this.position + j);
        }

        public Params() {
            super((Pointer) null);
            allocate();
        }
    }
}
