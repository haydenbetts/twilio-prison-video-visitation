package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class RansacParams extends Pointer {
    private native void allocate();

    private native void allocate(int i, float f, float f2, float f3);

    private native void allocateArray(long j);

    @ByVal
    public static native RansacParams default2dMotion(@Cast({"cv::videostab::MotionModel"}) int i);

    public native float eps();

    public native RansacParams eps(float f);

    public native int niters();

    public native float prob();

    public native RansacParams prob(float f);

    public native int size();

    public native RansacParams size(int i);

    public native float thresh();

    public native RansacParams thresh(float f);

    static {
        Loader.load();
    }

    public RansacParams(Pointer pointer) {
        super(pointer);
    }

    public RansacParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public RansacParams position(long j) {
        return (RansacParams) super.position(j);
    }

    public RansacParams getPointer(long j) {
        return new RansacParams((Pointer) this).position(this.position + j);
    }

    public RansacParams() {
        super((Pointer) null);
        allocate();
    }

    public RansacParams(int i, float f, float f2, float f3) {
        super((Pointer) null);
        allocate(i, f, f2, f3);
    }
}
