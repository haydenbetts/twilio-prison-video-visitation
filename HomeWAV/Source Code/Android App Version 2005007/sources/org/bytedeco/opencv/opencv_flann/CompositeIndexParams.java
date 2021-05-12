package org.bytedeco.opencv.opencv_flann;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
public class CompositeIndexParams extends IndexParams {
    private native void allocate();

    private native void allocate(int i, int i2, int i3, @Cast({"cvflann::flann_centers_init_t"}) int i4, float f);

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public CompositeIndexParams(Pointer pointer) {
        super(pointer);
    }

    public CompositeIndexParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CompositeIndexParams position(long j) {
        return (CompositeIndexParams) super.position(j);
    }

    public CompositeIndexParams getPointer(long j) {
        return new CompositeIndexParams((Pointer) this).position(this.position + j);
    }

    public CompositeIndexParams(int i, int i2, int i3, @Cast({"cvflann::flann_centers_init_t"}) int i4, float f) {
        super((Pointer) null);
        allocate(i, i2, i3, i4, f);
    }

    public CompositeIndexParams() {
        super((Pointer) null);
        allocate();
    }
}
