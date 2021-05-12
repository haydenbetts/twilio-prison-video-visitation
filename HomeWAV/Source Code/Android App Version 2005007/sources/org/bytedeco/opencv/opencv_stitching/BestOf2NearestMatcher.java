package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class BestOf2NearestMatcher extends FeaturesMatcher {
    private native void allocate();

    private native void allocate(@Cast({"bool"}) boolean z, float f, int i, int i2);

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native BestOf2NearestMatcher create();

    @opencv_core.Ptr
    public static native BestOf2NearestMatcher create(@Cast({"bool"}) boolean z, float f, int i, int i2);

    public native void collectGarbage();

    static {
        Loader.load();
    }

    public BestOf2NearestMatcher(Pointer pointer) {
        super(pointer);
    }

    public BestOf2NearestMatcher(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BestOf2NearestMatcher position(long j) {
        return (BestOf2NearestMatcher) super.position(j);
    }

    public BestOf2NearestMatcher getPointer(long j) {
        return new BestOf2NearestMatcher((Pointer) this).position(this.position + j);
    }

    public BestOf2NearestMatcher(@Cast({"bool"}) boolean z, float f, int i, int i2) {
        super((Pointer) null);
        allocate(z, f, i, i2);
    }

    public BestOf2NearestMatcher() {
        super((Pointer) null);
        allocate();
    }
}
