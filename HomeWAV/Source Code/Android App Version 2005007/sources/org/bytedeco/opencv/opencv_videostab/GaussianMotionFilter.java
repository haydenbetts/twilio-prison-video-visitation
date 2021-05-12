package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class GaussianMotionFilter extends MotionFilterBase {
    private native void allocate();

    private native void allocate(int i, float f);

    private native void allocateArray(long j);

    public native int radius();

    public native void setParams(int i);

    public native void setParams(int i, float f);

    @ByVal
    public native Mat stabilize(int i, @ByRef @Const MatVector matVector, @ByRef @Const Range range);

    public native float stdev();

    static {
        Loader.load();
    }

    public GaussianMotionFilter(Pointer pointer) {
        super(pointer);
    }

    public GaussianMotionFilter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public GaussianMotionFilter position(long j) {
        return (GaussianMotionFilter) super.position(j);
    }

    public GaussianMotionFilter getPointer(long j) {
        return new GaussianMotionFilter((Pointer) this).position(this.position + j);
    }

    public GaussianMotionFilter(int i, float f) {
        super((Pointer) null);
        allocate(i, f);
    }

    public GaussianMotionFilter() {
        super((Pointer) null);
        allocate();
    }
}
