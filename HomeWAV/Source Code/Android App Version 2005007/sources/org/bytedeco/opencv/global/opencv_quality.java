package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.Mat;

public class opencv_quality extends org.bytedeco.opencv.presets.opencv_quality {
    public static final int EXPANDED_MAT_DEFAULT_TYPE = EXPANDED_MAT_DEFAULT_TYPE();

    @Namespace("cv::quality::quality_utils")
    @MemberGetter
    public static native int EXPANDED_MAT_DEFAULT_TYPE();

    @Namespace("cv::quality::quality_utils")
    @ByVal
    public static native Mat get_column_range(@ByRef @Const Mat mat);

    static {
        Loader.load();
    }
}
