package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.DMatchVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class MatchesInfo extends Pointer {
    private native void allocate();

    private native void allocate(@ByRef @Const MatchesInfo matchesInfo);

    private native void allocateArray(long j);

    @ByRef
    public native Mat H();

    public native MatchesInfo H(Mat mat);

    public native double confidence();

    public native MatchesInfo confidence(double d);

    public native int dst_img_idx();

    public native MatchesInfo dst_img_idx(int i);

    @Cast({"uchar*"})
    @StdVector
    public native BytePointer getInliers();

    @ByVal
    public native DMatchVector getMatches();

    @Cast({"uchar*"})
    @StdVector
    public native BytePointer inliers_mask();

    public native MatchesInfo inliers_mask(BytePointer bytePointer);

    @ByRef
    public native DMatchVector matches();

    public native MatchesInfo matches(DMatchVector dMatchVector);

    public native int num_inliers();

    public native MatchesInfo num_inliers(int i);

    @ByRef
    @Name({"operator ="})
    public native MatchesInfo put(@ByRef @Const MatchesInfo matchesInfo);

    public native int src_img_idx();

    public native MatchesInfo src_img_idx(int i);

    static {
        Loader.load();
    }

    public MatchesInfo(Pointer pointer) {
        super(pointer);
    }

    public MatchesInfo(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MatchesInfo position(long j) {
        return (MatchesInfo) super.position(j);
    }

    public MatchesInfo getPointer(long j) {
        return new MatchesInfo(this).position(this.position + j);
    }

    public MatchesInfo() {
        super((Pointer) null);
        allocate();
    }

    public MatchesInfo(@ByRef @Const MatchesInfo matchesInfo) {
        super((Pointer) null);
        allocate(matchesInfo);
    }
}
