package org.bytedeco.opencv.opencv_flann;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
public class AutotunedIndexParams extends IndexParams {
    private native void allocate();

    private native void allocate(float f, float f2, float f3, float f4);

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public AutotunedIndexParams(Pointer pointer) {
        super(pointer);
    }

    public AutotunedIndexParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AutotunedIndexParams position(long j) {
        return (AutotunedIndexParams) super.position(j);
    }

    public AutotunedIndexParams getPointer(long j) {
        return new AutotunedIndexParams((Pointer) this).position(this.position + j);
    }

    public AutotunedIndexParams(float f, float f2, float f3, float f4) {
        super((Pointer) null);
        allocate(f, f2, f3, f4);
    }

    public AutotunedIndexParams() {
        super((Pointer) null);
        allocate();
    }
}
