package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class CvHOGEvaluator extends CvFeatureEvaluator {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"operator ()"})
    public native float apply(int i, int i2);

    public native void init(@Const CvFeatureParams cvFeatureParams, int i, @ByVal Size size);

    public native void setImage(@ByRef @Const Mat mat, @Cast({"uchar"}) byte b, int i);

    public native void writeFeatures(@ByRef FileStorage fileStorage, @ByRef @Const Mat mat);

    static {
        Loader.load();
    }

    public CvHOGEvaluator() {
        super((Pointer) null);
        allocate();
    }

    public CvHOGEvaluator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvHOGEvaluator(Pointer pointer) {
        super(pointer);
    }

    public CvHOGEvaluator position(long j) {
        return (CvHOGEvaluator) super.position(j);
    }

    public CvHOGEvaluator getPointer(long j) {
        return new CvHOGEvaluator((Pointer) this).position(this.position + j);
    }
}
