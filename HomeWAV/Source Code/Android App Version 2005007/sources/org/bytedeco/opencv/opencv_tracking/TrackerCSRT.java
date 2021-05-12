package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerCSRT extends Tracker {
    @opencv_core.Ptr
    public static native TrackerCSRT create();

    @opencv_core.Ptr
    public static native TrackerCSRT create(@ByRef @Const Params params);

    public native void setInitialMask(@ByVal GpuMat gpuMat);

    public native void setInitialMask(@ByVal Mat mat);

    public native void setInitialMask(@ByVal UMat uMat);

    static {
        Loader.load();
    }

    public TrackerCSRT(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native int admm_iterations();

        public native Params admm_iterations(int i);

        public native int background_ratio();

        public native Params background_ratio(int i);

        public native float cheb_attenuation();

        public native Params cheb_attenuation(float f);

        public native float filter_lr();

        public native Params filter_lr(float f);

        public native float gsl_sigma();

        public native Params gsl_sigma(float f);

        public native int histogram_bins();

        public native Params histogram_bins(int i);

        public native float histogram_lr();

        public native Params histogram_lr(float f);

        public native float hog_clip();

        public native Params hog_clip(float f);

        public native float hog_orientations();

        public native Params hog_orientations(float f);

        public native float kaiser_alpha();

        public native Params kaiser_alpha(float f);

        public native int num_hog_channels_used();

        public native Params num_hog_channels_used(int i);

        public native int number_of_scales();

        public native Params number_of_scales(int i);

        public native float padding();

        public native Params padding(float f);

        public native float psr_threshold();

        public native Params psr_threshold(float f);

        public native void read(@ByRef @Const FileNode fileNode);

        public native float scale_lr();

        public native Params scale_lr(float f);

        public native float scale_model_max_area();

        public native Params scale_model_max_area(float f);

        public native float scale_sigma_factor();

        public native Params scale_sigma_factor(float f);

        public native float scale_step();

        public native Params scale_step(float f);

        public native float template_size();

        public native Params template_size(float f);

        public native Params use_channel_weights(boolean z);

        @Cast({"bool"})
        public native boolean use_channel_weights();

        public native Params use_color_names(boolean z);

        @Cast({"bool"})
        public native boolean use_color_names();

        public native Params use_gray(boolean z);

        @Cast({"bool"})
        public native boolean use_gray();

        public native Params use_hog(boolean z);

        @Cast({"bool"})
        public native boolean use_hog();

        public native Params use_rgb(boolean z);

        @Cast({"bool"})
        public native boolean use_rgb();

        public native Params use_segmentation(boolean z);

        @Cast({"bool"})
        public native boolean use_segmentation();

        public native float weights_lr();

        public native Params weights_lr(float f);

        @StdString
        public native BytePointer window_function();

        public native Params window_function(BytePointer bytePointer);

        public native void write(@ByRef FileStorage fileStorage);

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
