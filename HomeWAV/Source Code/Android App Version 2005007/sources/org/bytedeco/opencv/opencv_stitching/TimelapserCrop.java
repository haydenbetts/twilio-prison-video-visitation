package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.SizeVector;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class TimelapserCrop extends Timelapser {
    private native void allocate();

    private native void allocateArray(long j);

    public native void initialize(@ByRef @Const PointVector pointVector, @ByRef @Const SizeVector sizeVector);

    static {
        Loader.load();
    }

    public TimelapserCrop() {
        super((Pointer) null);
        allocate();
    }

    public TimelapserCrop(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TimelapserCrop(Pointer pointer) {
        super(pointer);
    }

    public TimelapserCrop position(long j) {
        return (TimelapserCrop) super.position(j);
    }

    public TimelapserCrop getPointer(long j) {
        return new TimelapserCrop((Pointer) this).position(this.position + j);
    }
}
