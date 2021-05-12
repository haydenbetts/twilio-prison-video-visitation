package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
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
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.Point2fVectorVector;
import org.bytedeco.opencv.opencv_core.Point3iVector;
import org.bytedeco.opencv.opencv_core.PointVectorVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
public class FacemarkAAM extends FacemarkTrain {
    @opencv_core.Ptr
    public static native FacemarkAAM create();

    @opencv_core.Ptr
    public static native FacemarkAAM create(@ByRef(nullValue = "cv::face::FacemarkAAM::Params()") @Const Params params);

    @Cast({"bool"})
    public native boolean fitConfig(@ByVal Mat mat, @ByRef RectVector rectVector, @ByRef Point2fVectorVector point2fVectorVector, @StdVector Config config);

    static {
        Loader.load();
    }

    public FacemarkAAM(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native int m();

        public native Params m(int i);

        public native int max_m();

        public native Params max_m(int i);

        public native int max_n();

        public native Params max_n(int i);

        @StdString
        public native BytePointer model_filename();

        public native Params model_filename(BytePointer bytePointer);

        public native int n();

        public native Params n(int i);

        public native int n_iter();

        public native Params n_iter(int i);

        public native void read(@ByRef @Const FileNode fileNode);

        public native Params save_model(boolean z);

        @Cast({"bool"})
        public native boolean save_model();

        @StdVector
        public native FloatPointer scales();

        public native Params scales(FloatPointer floatPointer);

        public native int texture_max_m();

        public native Params texture_max_m(int i);

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
    public static class Config extends Pointer {
        private native void allocate();

        private native void allocate(@ByVal(nullValue = "cv::Mat::eye(2,2,CV_32F)") Mat mat, @ByVal(nullValue = "cv::Point2f(0.0f, 0.0f)") Point2f point2f, float f, int i);

        private native void allocateArray(long j);

        @ByRef
        public native Mat R();

        public native Config R(Mat mat);

        public native int model_scale_idx();

        public native Config model_scale_idx(int i);

        public native float scale();

        public native Config scale(float f);

        @ByRef
        public native Point2f t();

        public native Config t(Point2f point2f);

        static {
            Loader.load();
        }

        public Config(Pointer pointer) {
            super(pointer);
        }

        public Config(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Config position(long j) {
            return (Config) super.position(j);
        }

        public Config getPointer(long j) {
            return new Config((Pointer) this).position(this.position + j);
        }

        public Config(@ByVal(nullValue = "cv::Mat::eye(2,2,CV_32F)") Mat mat, @ByVal(nullValue = "cv::Point2f(0.0f, 0.0f)") Point2f point2f, float f, int i) {
            super((Pointer) null);
            allocate(mat, point2f, f, i);
        }

        public Config() {
            super((Pointer) null);
            allocate();
        }
    }

    public static class Data extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        @ByRef
        public native Point2fVector s0();

        public native Data s0(Point2fVector point2fVector);

        static {
            Loader.load();
        }

        public Data() {
            super((Pointer) null);
            allocate();
        }

        public Data(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Data(Pointer pointer) {
            super(pointer);
        }

        public Data position(long j) {
            return (Data) super.position(j);
        }

        public Data getPointer(long j) {
            return new Data((Pointer) this).position(this.position + j);
        }
    }

    public static class Model extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        @ByRef
        public native Mat Q();

        public native Model Q(Mat mat);

        @ByRef
        public native Mat S();

        public native Model S(Mat mat);

        @ByRef
        public native Point2fVector s0();

        public native Model s0(Point2fVector point2fVector);

        @StdVector
        public native FloatPointer scales();

        public native Model scales(FloatPointer floatPointer);

        @StdVector
        public native Texture textures();

        public native Model textures(Texture texture);

        @ByRef
        @Cast({"std::vector<cv::Vec3i>*"})
        public native Point3iVector triangles();

        public native Model triangles(Point3iVector point3iVector);

        static {
            Loader.load();
        }

        public Model() {
            super((Pointer) null);
            allocate();
        }

        public Model(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Model(Pointer pointer) {
            super(pointer);
        }

        public Model position(long j) {
            return (Model) super.position(j);
        }

        public Model getPointer(long j) {
            return new Model((Pointer) this).position(this.position + j);
        }

        public static class Texture extends Pointer {
            private native void allocate();

            private native void allocateArray(long j);

            @ByRef
            public native Mat A();

            public native Texture A(Mat mat);

            @ByRef
            public native Mat A0();

            public native Texture A0(Mat mat);

            @ByRef
            public native Mat AA();

            public native Texture AA(Mat mat);

            @ByRef
            public native Mat AA0();

            public native Texture AA0(Mat mat);

            @ByRef
            public native Point2fVector base_shape();

            public native Texture base_shape(Point2fVector point2fVector);

            @StdVector
            public native IntPointer ind1();

            public native Texture ind1(IntPointer intPointer);

            @StdVector
            public native IntPointer ind2();

            public native Texture ind2(IntPointer intPointer);

            public native int max_m();

            public native Texture max_m(int i);

            @ByRef
            public native Rect resolution();

            public native Texture resolution(Rect rect);

            @ByRef
            public native PointVectorVector textureIdx();

            public native Texture textureIdx(PointVectorVector pointVectorVector);

            static {
                Loader.load();
            }

            public Texture() {
                super((Pointer) null);
                allocate();
            }

            public Texture(long j) {
                super((Pointer) null);
                allocateArray(j);
            }

            public Texture(Pointer pointer) {
                super(pointer);
            }

            public Texture position(long j) {
                return (Texture) super.position(j);
            }

            public Texture getPointer(long j) {
                return new Texture((Pointer) this).position(this.position + j);
            }
        }
    }
}
