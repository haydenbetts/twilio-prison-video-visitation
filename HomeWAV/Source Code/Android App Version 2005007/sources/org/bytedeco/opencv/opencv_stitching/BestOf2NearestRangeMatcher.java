package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class BestOf2NearestRangeMatcher extends BestOf2NearestMatcher {
    private native void allocate();

    private native void allocate(int i, @Cast({"bool"}) boolean z, float f, int i2, int i3);

    private native void allocateArray(long j);

    @Name({"operator ()"})
    public native void apply(@StdVector ImageFeatures imageFeatures, @StdVector MatchesInfo matchesInfo);

    @Name({"operator ()"})
    public native void apply(@StdVector ImageFeatures imageFeatures, @StdVector MatchesInfo matchesInfo, @ByRef(nullValue = "cv::UMat()") @Const UMat uMat);

    static {
        Loader.load();
    }

    public BestOf2NearestRangeMatcher(Pointer pointer) {
        super(pointer);
    }

    public BestOf2NearestRangeMatcher(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BestOf2NearestRangeMatcher position(long j) {
        return (BestOf2NearestRangeMatcher) super.position(j);
    }

    public BestOf2NearestRangeMatcher getPointer(long j) {
        return new BestOf2NearestRangeMatcher((Pointer) this).position(this.position + j);
    }

    public BestOf2NearestRangeMatcher(int i, @Cast({"bool"}) boolean z, float f, int i2, int i3) {
        super((Pointer) null);
        allocate(i, z, f, i2, i3);
    }

    public BestOf2NearestRangeMatcher() {
        super((Pointer) null);
        allocate();
    }
}
