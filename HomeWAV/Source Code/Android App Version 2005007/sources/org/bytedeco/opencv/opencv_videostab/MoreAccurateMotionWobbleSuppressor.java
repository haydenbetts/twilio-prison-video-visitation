package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class MoreAccurateMotionWobbleSuppressor extends MoreAccurateMotionWobbleSuppressorBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native void suppress(int i, @ByRef @Const Mat mat, @ByRef Mat mat2);

    static {
        Loader.load();
    }

    public MoreAccurateMotionWobbleSuppressor() {
        super((Pointer) null);
        allocate();
    }

    public MoreAccurateMotionWobbleSuppressor(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MoreAccurateMotionWobbleSuppressor(Pointer pointer) {
        super(pointer);
    }

    public MoreAccurateMotionWobbleSuppressor position(long j) {
        return (MoreAccurateMotionWobbleSuppressor) super.position(j);
    }

    public MoreAccurateMotionWobbleSuppressor getPointer(long j) {
        return new MoreAccurateMotionWobbleSuppressor((Pointer) this).position(this.position + j);
    }
}
