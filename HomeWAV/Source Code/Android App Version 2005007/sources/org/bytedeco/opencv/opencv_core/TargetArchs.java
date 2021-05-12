package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_core.class})
public class TargetArchs extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"bool"})
    public static native boolean builtWith(@Cast({"cv::cuda::FeatureSet"}) int i);

    @Cast({"bool"})
    public static native boolean has(int i, int i2);

    @Cast({"bool"})
    public static native boolean hasBin(int i, int i2);

    @Cast({"bool"})
    public static native boolean hasEqualOrGreater(int i, int i2);

    @Cast({"bool"})
    public static native boolean hasEqualOrGreaterBin(int i, int i2);

    @Cast({"bool"})
    public static native boolean hasEqualOrGreaterPtx(int i, int i2);

    @Cast({"bool"})
    public static native boolean hasEqualOrLessPtx(int i, int i2);

    @Cast({"bool"})
    public static native boolean hasPtx(int i, int i2);

    static {
        Loader.load();
    }

    public TargetArchs() {
        super((Pointer) null);
        allocate();
    }

    public TargetArchs(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TargetArchs(Pointer pointer) {
        super(pointer);
    }

    public TargetArchs position(long j) {
        return (TargetArchs) super.position(j);
    }

    public TargetArchs getPointer(long j) {
        return new TargetArchs((Pointer) this).position(this.position + j);
    }
}
