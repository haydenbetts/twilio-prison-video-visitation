package org.bytedeco.opencv.opencv_face;

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
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point2fVectorVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
public class FacemarkKazemi extends Facemark {
    @opencv_core.Ptr
    public static native FacemarkKazemi create();

    @opencv_core.Ptr
    public static native FacemarkKazemi create(@ByRef(nullValue = "cv::face::FacemarkKazemi::Params()") @Const Params params);

    @Cast({"bool"})
    public native boolean getFaces(@ByVal Mat mat, @ByRef RectVector rectVector);

    @Cast({"bool"})
    public native boolean setFaceDetector(@Cast({"bool (*)(cv::InputArray, cv::OutputArray, void*)"}) Pointer pointer, Pointer pointer2);

    @Cast({"bool"})
    public native boolean training(@ByRef MatVector matVector, @ByRef Point2fVectorVector point2fVectorVector, @StdString String str, @ByVal Size size);

    @Cast({"bool"})
    public native boolean training(@ByRef MatVector matVector, @ByRef Point2fVectorVector point2fVectorVector, @StdString String str, @ByVal Size size, @StdString String str2);

    @Cast({"bool"})
    public native boolean training(@ByRef MatVector matVector, @ByRef Point2fVectorVector point2fVectorVector, @StdString BytePointer bytePointer, @ByVal Size size);

    @Cast({"bool"})
    public native boolean training(@ByRef MatVector matVector, @ByRef Point2fVectorVector point2fVectorVector, @StdString BytePointer bytePointer, @ByVal Size size, @StdString BytePointer bytePointer2);

    static {
        Loader.load();
    }

    public FacemarkKazemi(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        @Cast({"unsigned long"})
        public native long cascade_depth();

        public native Params cascade_depth(long j);

        @opencv_core.Str
        public native BytePointer configfile();

        public native Params configfile(BytePointer bytePointer);

        public native float lambda();

        public native Params lambda(float f);

        public native float learning_rate();

        public native Params learning_rate(float f);

        @Cast({"unsigned long"})
        public native long num_test_coordinates();

        public native Params num_test_coordinates(long j);

        @Cast({"unsigned long"})
        public native long num_test_splits();

        public native Params num_test_splits(long j);

        @Cast({"unsigned long"})
        public native long num_trees_per_cascade_level();

        public native Params num_trees_per_cascade_level(long j);

        @Cast({"unsigned long"})
        public native long oversampling_amount();

        public native Params oversampling_amount(long j);

        @Cast({"unsigned long"})
        public native long tree_depth();

        public native Params tree_depth(long j);

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
