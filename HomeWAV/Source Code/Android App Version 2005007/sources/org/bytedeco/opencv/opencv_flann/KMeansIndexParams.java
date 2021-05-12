package org.bytedeco.opencv.opencv_flann;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
public class KMeansIndexParams extends IndexParams {
    private native void allocate();

    private native void allocate(int i, int i2, @Cast({"cvflann::flann_centers_init_t"}) int i3, float f);

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public KMeansIndexParams(Pointer pointer) {
        super(pointer);
    }

    public KMeansIndexParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public KMeansIndexParams position(long j) {
        return (KMeansIndexParams) super.position(j);
    }

    public KMeansIndexParams getPointer(long j) {
        return new KMeansIndexParams((Pointer) this).position(this.position + j);
    }

    public KMeansIndexParams(int i, int i2, @Cast({"cvflann::flann_centers_init_t"}) int i3, float f) {
        super((Pointer) null);
        allocate(i, i2, i3, f);
    }

    public KMeansIndexParams() {
        super((Pointer) null);
        allocate();
    }
}
