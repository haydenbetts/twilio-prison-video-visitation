package org.bytedeco.opencv.opencv_tracking;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Function;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Namespace("cv")
@NoOffset
public class CvHaarEvaluator extends CvFeatureEvaluator {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"operator ()"})
    public native float apply(int i, int i2);

    public native void generateFeatures();

    public native void generateFeatures(int i);

    @StdVector
    public native FeatureHaar getFeatures();

    @ByRef
    public native FeatureHaar getFeatures(int i);

    public native void init(@Const CvFeatureParams cvFeatureParams, int i, @ByVal Size size);

    public native void setImage(@ByRef @Const Mat mat);

    public native void setImage(@ByRef @Const Mat mat, @Cast({"uchar"}) byte b, int i);

    @Function
    @ByVal
    public native Size setWinSize();

    @Function
    public native void setWinSize(@ByVal Size size);

    public native void writeFeature(@ByRef FileStorage fileStorage);

    public native void writeFeatures(@ByRef FileStorage fileStorage, @ByRef @Const Mat mat);

    static {
        Loader.load();
    }

    public CvHaarEvaluator() {
        super((Pointer) null);
        allocate();
    }

    public CvHaarEvaluator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvHaarEvaluator(Pointer pointer) {
        super(pointer);
    }

    public CvHaarEvaluator position(long j) {
        return (CvHaarEvaluator) super.position(j);
    }

    public CvHaarEvaluator getPointer(long j) {
        return new CvHaarEvaluator((Pointer) this).position(this.position + j);
    }

    @NoOffset
    public static class FeatureHaar extends Pointer {
        private native void allocate(@ByVal Size size);

        @Cast({"bool"})
        public native boolean eval(@ByRef @Const Mat mat, @ByVal Rect rect, FloatBuffer floatBuffer);

        @Cast({"bool"})
        public native boolean eval(@ByRef @Const Mat mat, @ByVal Rect rect, FloatPointer floatPointer);

        @Cast({"bool"})
        public native boolean eval(@ByRef @Const Mat mat, @ByVal Rect rect, float[] fArr);

        @ByRef
        @Const
        public native RectVector getAreas();

        public native float getInitMean();

        public native float getInitSigma();

        public native int getNumAreas();

        @StdVector
        public native FloatPointer getWeights();

        public native void write(@ByVal FileStorage fileStorage);

        static {
            Loader.load();
        }

        public FeatureHaar(Pointer pointer) {
            super(pointer);
        }

        public FeatureHaar(@ByVal Size size) {
            super((Pointer) null);
            allocate(size);
        }
    }
}
