package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class FastMarchingMethod extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @ByVal
    public native Mat distanceMap();

    static {
        Loader.load();
    }

    public FastMarchingMethod(Pointer pointer) {
        super(pointer);
    }

    public FastMarchingMethod(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FastMarchingMethod position(long j) {
        return (FastMarchingMethod) super.position(j);
    }

    public FastMarchingMethod getPointer(long j) {
        return new FastMarchingMethod((Pointer) this).position(this.position + j);
    }

    public FastMarchingMethod() {
        super((Pointer) null);
        allocate();
    }
}
