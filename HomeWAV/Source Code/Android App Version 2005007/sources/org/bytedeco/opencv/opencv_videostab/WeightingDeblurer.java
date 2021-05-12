package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class WeightingDeblurer extends DeblurerBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native void deblur(int i, @ByRef Mat mat, @ByRef @Const Range range);

    public native float sensitivity();

    public native void setSensitivity(float f);

    static {
        Loader.load();
    }

    public WeightingDeblurer(Pointer pointer) {
        super(pointer);
    }

    public WeightingDeblurer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public WeightingDeblurer position(long j) {
        return (WeightingDeblurer) super.position(j);
    }

    public WeightingDeblurer getPointer(long j) {
        return new WeightingDeblurer((Pointer) this).position(this.position + j);
    }

    public WeightingDeblurer() {
        super((Pointer) null);
        allocate();
    }
}
