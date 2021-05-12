package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.SizeVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class VoronoiSeamFinder extends PairwiseSeamFinder {
    private native void allocate();

    private native void allocateArray(long j);

    public native void find(@ByRef @Const SizeVector sizeVector, @ByRef @Const PointVector pointVector, @ByRef UMatVector uMatVector);

    public native void find(@ByRef @Const UMatVector uMatVector, @ByRef @Const PointVector pointVector, @ByRef UMatVector uMatVector2);

    static {
        Loader.load();
    }

    public VoronoiSeamFinder() {
        super((Pointer) null);
        allocate();
    }

    public VoronoiSeamFinder(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public VoronoiSeamFinder(Pointer pointer) {
        super(pointer);
    }

    public VoronoiSeamFinder position(long j) {
        return (VoronoiSeamFinder) super.position(j);
    }

    public VoronoiSeamFinder getPointer(long j) {
        return new VoronoiSeamFinder((Pointer) this).position(this.position + j);
    }
}
