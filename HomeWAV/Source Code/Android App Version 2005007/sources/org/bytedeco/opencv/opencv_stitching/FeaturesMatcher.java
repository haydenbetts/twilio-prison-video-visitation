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
public class FeaturesMatcher extends Pointer {
    @Name({"operator ()"})
    public native void apply(@ByRef @Const ImageFeatures imageFeatures, @ByRef @Const ImageFeatures imageFeatures2, @ByRef MatchesInfo matchesInfo);

    @Name({"operator ()"})
    public native void apply2(@StdVector ImageFeatures imageFeatures, @StdVector MatchesInfo matchesInfo);

    @Name({"operator ()"})
    public native void apply2(@StdVector ImageFeatures imageFeatures, @StdVector MatchesInfo matchesInfo, @ByRef(nullValue = "cv::UMat()") @Const UMat uMat);

    public native void collectGarbage();

    @Cast({"bool"})
    public native boolean isThreadSafe();

    static {
        Loader.load();
    }

    public FeaturesMatcher(Pointer pointer) {
        super(pointer);
    }
}
