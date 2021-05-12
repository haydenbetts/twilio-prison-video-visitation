package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class SeamFinder extends Pointer {
    public static final int DP_SEAM = 2;
    public static final int NO = 0;
    public static final int VORONOI_SEAM = 1;

    @opencv_core.Ptr
    public static native SeamFinder createDefault(int i);

    public native void find(@ByRef @Const UMatVector uMatVector, @ByRef @Const PointVector pointVector, @ByRef UMatVector uMatVector2);

    static {
        Loader.load();
    }

    public SeamFinder(Pointer pointer) {
        super(pointer);
    }
}
