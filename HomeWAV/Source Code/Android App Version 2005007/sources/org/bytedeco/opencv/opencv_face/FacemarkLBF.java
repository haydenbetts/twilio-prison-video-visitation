package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
public class FacemarkLBF extends FacemarkTrain {
    @opencv_core.Ptr
    public static native FacemarkLBF create();

    @opencv_core.Ptr
    public static native FacemarkLBF create(@ByRef(nullValue = "cv::face::FacemarkLBF::Params()") @Const Params params);

    static {
        Loader.load();
    }

    public FacemarkLBF(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native double bagging_overlap();

        public native Params bagging_overlap(double d);

        @opencv_core.Str
        public native BytePointer cascade_face();

        public native Params cascade_face(BytePointer bytePointer);

        @ByRef
        public native Rect detectROI();

        public native Params detectROI(Rect rect);

        @StdVector
        public native IntPointer feats_m();

        public native Params feats_m(IntPointer intPointer);

        public native int initShape_n();

        public native Params initShape_n(int i);

        @StdString
        public native BytePointer model_filename();

        public native Params model_filename(BytePointer bytePointer);

        public native int n_landmarks();

        public native Params n_landmarks(int i);

        @MemberGetter
        @StdVector
        public native IntPointer pupils();

        @StdVector
        public native IntPointer pupils(int i);

        public native Params pupils(int i, IntPointer intPointer);

        @StdVector
        public native DoublePointer radius_m();

        public native Params radius_m(DoublePointer doublePointer);

        public native void read(@ByRef @Const FileNode fileNode);

        public native Params save_model(boolean z);

        @Cast({"bool"})
        public native boolean save_model();

        @Cast({"unsigned int"})
        public native int seed();

        public native Params seed(int i);

        public native double shape_offset();

        public native Params shape_offset(double d);

        public native int stages_n();

        public native Params stages_n(int i);

        public native int tree_depth();

        public native Params tree_depth(int i);

        public native int tree_n();

        public native Params tree_n(int i);

        public native Params verbose(boolean z);

        @Cast({"bool"})
        public native boolean verbose();

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

    @NoOffset
    public static class BBox extends Pointer {
        private native void allocate();

        private native void allocate(double d, double d2, double d3, double d4);

        private native void allocateArray(long j);

        public native double height();

        public native BBox height(double d);

        @ByVal
        public native Mat project(@ByRef @Const Mat mat);

        @ByVal
        public native Mat reproject(@ByRef @Const Mat mat);

        public native double width();

        public native BBox width(double d);

        public native double x();

        public native BBox x(double d);

        public native double x_center();

        public native BBox x_center(double d);

        public native double x_scale();

        public native BBox x_scale(double d);

        public native double y();

        public native BBox y(double d);

        public native double y_center();

        public native BBox y_center(double d);

        public native double y_scale();

        public native BBox y_scale(double d);

        static {
            Loader.load();
        }

        public BBox(Pointer pointer) {
            super(pointer);
        }

        public BBox(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public BBox position(long j) {
            return (BBox) super.position(j);
        }

        public BBox getPointer(long j) {
            return new BBox((Pointer) this).position(this.position + j);
        }

        public BBox() {
            super((Pointer) null);
            allocate();
        }

        public BBox(double d, double d2, double d3, double d4) {
            super((Pointer) null);
            allocate(d, d2, d3, d4);
        }
    }
}
