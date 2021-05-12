package org.bytedeco.opencv.opencv_xfeatures2d;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
@NoOffset
public class FREAK extends Feature2D {
    public static final int NB_ORIENPAIRS = NB_ORIENPAIRS();
    public static final int NB_PAIRS = NB_PAIRS();
    public static final int NB_SCALES = NB_SCALES();

    @MemberGetter
    public static native int NB_ORIENPAIRS();

    @MemberGetter
    public static native int NB_PAIRS();

    @MemberGetter
    public static native int NB_SCALES();

    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native FREAK create();

    @opencv_core.Ptr
    public static native FREAK create(@Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, float f, int i, @StdVector IntBuffer intBuffer);

    @opencv_core.Ptr
    public static native FREAK create(@Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, float f, int i, @StdVector IntPointer intPointer);

    @opencv_core.Ptr
    public static native FREAK create(@Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, float f, int i, @StdVector int[] iArr);

    static {
        Loader.load();
    }

    public FREAK() {
        super((Pointer) null);
        allocate();
    }

    public FREAK(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FREAK(Pointer pointer) {
        super(pointer);
    }

    public FREAK position(long j) {
        return (FREAK) super.position(j);
    }

    public FREAK getPointer(long j) {
        return new FREAK((Pointer) this).position(this.position + j);
    }
}
