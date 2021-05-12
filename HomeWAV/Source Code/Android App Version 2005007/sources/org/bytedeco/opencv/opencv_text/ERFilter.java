package org.bytedeco.opencv.opencv_text;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_text;

@Namespace("cv::text")
@Properties(inherit = {opencv_text.class})
public class ERFilter extends Algorithm {
    public native int getNumRejected();

    public native void run(@ByVal GpuMat gpuMat, @ByRef ERStatVector eRStatVector);

    public native void run(@ByVal Mat mat, @ByRef ERStatVector eRStatVector);

    public native void run(@ByVal UMat uMat, @ByRef ERStatVector eRStatVector);

    public native void setCallback(@opencv_core.Ptr Callback callback);

    public native void setMaxArea(float f);

    public native void setMinArea(float f);

    public native void setMinProbability(float f);

    public native void setMinProbabilityDiff(float f);

    public native void setNonMaxSuppression(@Cast({"bool"}) boolean z);

    public native void setThresholdDelta(int i);

    static {
        Loader.load();
    }

    public ERFilter(Pointer pointer) {
        super(pointer);
    }

    public static class Callback extends Pointer {
        public native double eval(@ByRef @Const ERStat eRStat);

        static {
            Loader.load();
        }

        public Callback(Pointer pointer) {
            super(pointer);
        }
    }
}
