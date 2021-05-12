package org.bytedeco.opencv.opencv_flann;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
public class HierarchicalClusteringIndexParams extends IndexParams {
    private native void allocate();

    private native void allocate(int i, @Cast({"cvflann::flann_centers_init_t"}) int i2, int i3, int i4);

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public HierarchicalClusteringIndexParams(Pointer pointer) {
        super(pointer);
    }

    public HierarchicalClusteringIndexParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public HierarchicalClusteringIndexParams position(long j) {
        return (HierarchicalClusteringIndexParams) super.position(j);
    }

    public HierarchicalClusteringIndexParams getPointer(long j) {
        return new HierarchicalClusteringIndexParams((Pointer) this).position(this.position + j);
    }

    public HierarchicalClusteringIndexParams(int i, @Cast({"cvflann::flann_centers_init_t"}) int i2, int i3, int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, i4);
    }

    public HierarchicalClusteringIndexParams() {
        super((Pointer) null);
        allocate();
    }
}
