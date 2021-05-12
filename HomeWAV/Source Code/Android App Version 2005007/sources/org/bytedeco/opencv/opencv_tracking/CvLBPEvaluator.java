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
public class CvLBPEvaluator extends CvFeatureEvaluator {
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

    public CvLBPEvaluator() {
        super((Pointer) null);
        allocate();
    }

    public CvLBPEvaluator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvLBPEvaluator(Pointer pointer) {
        super(pointer);
    }

    public CvLBPEvaluator position(long j) {
        return (CvLBPEvaluator) super.position(j);
    }

    public CvLBPEvaluator getPointer(long j) {
        return new CvLBPEvaluator((Pointer) this).position(this.position + j);
    }
}
