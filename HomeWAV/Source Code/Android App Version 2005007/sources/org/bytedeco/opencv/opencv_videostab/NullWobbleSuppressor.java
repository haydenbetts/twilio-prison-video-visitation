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
public class NullWobbleSuppressor extends WobbleSuppressorBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native void suppress(int i, @ByRef @Const Mat mat, @ByRef Mat mat2);

    static {
        Loader.load();
    }

    public NullWobbleSuppressor() {
        super((Pointer) null);
        allocate();
    }

    public NullWobbleSuppressor(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NullWobbleSuppressor(Pointer pointer) {
        super(pointer);
    }

    public NullWobbleSuppressor position(long j) {
        return (NullWobbleSuppressor) super.position(j);
    }

    public NullWobbleSuppressor getPointer(long j) {
        return new NullWobbleSuppressor((Pointer) this).position(this.position + j);
    }
}
