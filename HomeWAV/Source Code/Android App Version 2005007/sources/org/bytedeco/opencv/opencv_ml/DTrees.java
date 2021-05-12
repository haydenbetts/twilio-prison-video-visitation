package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
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
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class DTrees extends StatModel {
    public static final int PREDICT_AUTO = 0;
    public static final int PREDICT_MASK = 768;
    public static final int PREDICT_MAX_VOTE = 512;
    public static final int PREDICT_SUM = 256;

    @opencv_core.Ptr
    public static native DTrees create();

    @opencv_core.Ptr
    public static native DTrees load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native DTrees load(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    public static native DTrees load(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Ptr
    public static native DTrees load(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native int getCVFolds();

    public native int getMaxCategories();

    public native int getMaxDepth();

    public native int getMinSampleCount();

    @StdVector
    public native Node getNodes();

    @ByVal
    public native Mat getPriors();

    public native float getRegressionAccuracy();

    @StdVector
    public native IntPointer getRoots();

    @StdVector
    public native Split getSplits();

    @StdVector
    public native IntPointer getSubsets();

    @Cast({"bool"})
    public native boolean getTruncatePrunedTree();

    @Cast({"bool"})
    public native boolean getUse1SERule();

    @Cast({"bool"})
    public native boolean getUseSurrogates();

    public native void setCVFolds(int i);

    public native void setMaxCategories(int i);

    public native void setMaxDepth(int i);

    public native void setMinSampleCount(int i);

    public native void setPriors(@ByRef @Const Mat mat);

    public native void setRegressionAccuracy(float f);

    public native void setTruncatePrunedTree(@Cast({"bool"}) boolean z);

    public native void setUse1SERule(@Cast({"bool"}) boolean z);

    public native void setUseSurrogates(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public DTrees(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Node extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native int classIdx();

        public native Node classIdx(int i);

        public native int defaultDir();

        public native Node defaultDir(int i);

        public native int left();

        public native Node left(int i);

        public native int parent();

        public native Node parent(int i);

        public native int right();

        public native Node right(int i);

        public native int split();

        public native Node split(int i);

        public native double value();

        public native Node value(double d);

        static {
            Loader.load();
        }

        public Node(Pointer pointer) {
            super(pointer);
        }

        public Node(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Node position(long j) {
            return (Node) super.position(j);
        }

        public Node getPointer(long j) {
            return new Node((Pointer) this).position(this.position + j);
        }

        public Node() {
            super((Pointer) null);
            allocate();
        }
    }

    @NoOffset
    public static class Split extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native float c();

        public native Split c(float f);

        public native Split inversed(boolean z);

        @Cast({"bool"})
        public native boolean inversed();

        public native int next();

        public native Split next(int i);

        public native float quality();

        public native Split quality(float f);

        public native int subsetOfs();

        public native Split subsetOfs(int i);

        public native int varIdx();

        public native Split varIdx(int i);

        static {
            Loader.load();
        }

        public Split(Pointer pointer) {
            super(pointer);
        }

        public Split(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Split position(long j) {
            return (Split) super.position(j);
        }

        public Split getPointer(long j) {
            return new Split((Pointer) this).position(this.position + j);
        }

        public Split() {
            super((Pointer) null);
            allocate();
        }
    }
}
