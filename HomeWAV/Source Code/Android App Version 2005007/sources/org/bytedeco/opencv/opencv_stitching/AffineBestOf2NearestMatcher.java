package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class AffineBestOf2NearestMatcher extends BestOf2NearestMatcher {
    private native void allocate();

    private native void allocate(@Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, float f, int i);

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public AffineBestOf2NearestMatcher(Pointer pointer) {
        super(pointer);
    }

    public AffineBestOf2NearestMatcher(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AffineBestOf2NearestMatcher position(long j) {
        return (AffineBestOf2NearestMatcher) super.position(j);
    }

    public AffineBestOf2NearestMatcher getPointer(long j) {
        return new AffineBestOf2NearestMatcher((Pointer) this).position(this.position + j);
    }

    public AffineBestOf2NearestMatcher(@Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, float f, int i) {
        super((Pointer) null);
        allocate(z, z2, f, i);
    }

    public AffineBestOf2NearestMatcher() {
        super((Pointer) null);
        allocate();
    }
}
