package org.bytedeco.opencv.opencv_flann;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
public class SearchParams extends IndexParams {
    private native void allocate();

    private native void allocate(int i, float f, @Cast({"bool"}) boolean z);

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public SearchParams(Pointer pointer) {
        super(pointer);
    }

    public SearchParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SearchParams position(long j) {
        return (SearchParams) super.position(j);
    }

    public SearchParams getPointer(long j) {
        return new SearchParams((Pointer) this).position(this.position + j);
    }

    public SearchParams(int i, float f, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(i, f, z);
    }

    public SearchParams() {
        super((Pointer) null);
        allocate();
    }
}
