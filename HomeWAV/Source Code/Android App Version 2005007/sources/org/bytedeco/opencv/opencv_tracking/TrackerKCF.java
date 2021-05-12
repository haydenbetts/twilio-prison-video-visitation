package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerKCF extends Tracker {
    public static final int CN = 2;
    public static final int CUSTOM = 4;
    public static final int GRAY = 1;

    @opencv_core.Ptr
    public static native TrackerKCF create();

    @opencv_core.Ptr
    public static native TrackerKCF create(@ByRef @Const Params params);

    public native void setFeatureExtractor(Arg0_Mat_Rect_Mat arg0_Mat_Rect_Mat);

    public native void setFeatureExtractor(Arg0_Mat_Rect_Mat arg0_Mat_Rect_Mat, @Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public TrackerKCF(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native Params compress_feature(boolean z);

        @Cast({"bool"})
        public native boolean compress_feature();

        public native int compressed_size();

        public native Params compressed_size(int i);

        public native int desc_npca();

        public native Params desc_npca(int i);

        public native int desc_pca();

        public native Params desc_pca(int i);

        public native float detect_thresh();

        public native Params detect_thresh(float f);

        public native float interp_factor();

        public native Params interp_factor(float f);

        public native float lambda();

        public native Params lambda(float f);

        public native int max_patch_size();

        public native Params max_patch_size(int i);

        public native float output_sigma_factor();

        public native Params output_sigma_factor(float f);

        public native float pca_learning_rate();

        public native Params pca_learning_rate(float f);

        public native void read(@ByRef @Const FileNode fileNode);

        public native Params resize(boolean z);

        @Cast({"bool"})
        public native boolean resize();

        public native float sigma();

        public native Params sigma(float f);

        public native Params split_coeff(boolean z);

        @Cast({"bool"})
        public native boolean split_coeff();

        public native Params wrap_kernel(boolean z);

        @Cast({"bool"})
        public native boolean wrap_kernel();

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

    public static class Arg0_Mat_Rect_Mat extends FunctionPointer {
        private native void allocate();

        public native void call(@Const @ByVal Mat mat, @Const @ByVal Rect rect, @ByRef Mat mat2);

        static {
            Loader.load();
        }

        public Arg0_Mat_Rect_Mat(Pointer pointer) {
            super(pointer);
        }

        protected Arg0_Mat_Rect_Mat() {
            allocate();
        }
    }
}
