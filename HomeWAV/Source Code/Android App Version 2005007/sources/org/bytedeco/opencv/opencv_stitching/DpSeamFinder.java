package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class DpSeamFinder extends SeamFinder {
    public static final int COLOR = 0;
    public static final int COLOR_GRAD = 1;

    private native void allocate();

    private native void allocate(@Cast({"cv::detail::DpSeamFinder::CostFunction"}) int i);

    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    @Cast({"cv::detail::DpSeamFinder::CostFunction"})
    public native int costFunction();

    public native void find(@ByRef @Const UMatVector uMatVector, @ByRef @Const PointVector pointVector, @ByRef UMatVector uMatVector2);

    public native void setCostFunction(@Cast({"cv::detail::DpSeamFinder::CostFunction"}) int i);

    public native void setCostFunction(@opencv_core.Str String str);

    public native void setCostFunction(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public DpSeamFinder(Pointer pointer) {
        super(pointer);
    }

    public DpSeamFinder(@Cast({"cv::detail::DpSeamFinder::CostFunction"}) int i) {
        super((Pointer) null);
        allocate(i);
    }

    public DpSeamFinder() {
        super((Pointer) null);
        allocate();
    }

    public DpSeamFinder(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public DpSeamFinder(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }
}
