package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class BlocksCompensator extends ExposureCompensator {
    public native void apply(int i, @ByVal Point point, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void apply(int i, @ByVal Point point, @ByVal Mat mat, @ByVal Mat mat2);

    public native void apply(int i, @ByVal Point point, @ByVal UMat uMat, @ByVal UMat uMat2);

    @ByVal
    public native Size getBlockSize();

    public native void getMatGains(@ByRef MatVector matVector);

    public native int getNrFeeds();

    public native int getNrGainsFilteringIterations();

    public native void setBlockSize(int i, int i2);

    public native void setBlockSize(@ByVal Size size);

    public native void setMatGains(@ByRef MatVector matVector);

    public native void setNrFeeds(int i);

    public native void setNrGainsFilteringIterations(int i);

    static {
        Loader.load();
    }

    public BlocksCompensator(Pointer pointer) {
        super(pointer);
    }
}
