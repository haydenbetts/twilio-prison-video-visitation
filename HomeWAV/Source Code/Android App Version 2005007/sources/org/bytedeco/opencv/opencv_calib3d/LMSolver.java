package org.bytedeco.opencv.opencv_calib3d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_calib3d;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_calib3d.class})
public class LMSolver extends Algorithm {
    @opencv_core.Ptr
    public static native LMSolver create(@opencv_core.Ptr Callback callback, int i);

    @opencv_core.Ptr
    public static native LMSolver create(@opencv_core.Ptr Callback callback, int i, double d);

    public native int getMaxIters();

    public native int run(@ByVal GpuMat gpuMat);

    public native int run(@ByVal Mat mat);

    public native int run(@ByVal UMat uMat);

    public native void setMaxIters(int i);

    static {
        Loader.load();
    }

    public LMSolver(Pointer pointer) {
        super(pointer);
    }

    public static class Callback extends Pointer {
        @Cast({"bool"})
        public native boolean compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

        @Cast({"bool"})
        public native boolean compute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

        @Cast({"bool"})
        public native boolean compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

        static {
            Loader.load();
        }

        public Callback(Pointer pointer) {
            super(pointer);
        }
    }
}
