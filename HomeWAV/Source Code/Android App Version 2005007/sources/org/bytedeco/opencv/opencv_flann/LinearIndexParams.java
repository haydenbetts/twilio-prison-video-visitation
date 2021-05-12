package org.bytedeco.opencv.opencv_flann;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
public class LinearIndexParams extends IndexParams {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public LinearIndexParams(Pointer pointer) {
        super(pointer);
    }

    public LinearIndexParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public LinearIndexParams position(long j) {
        return (LinearIndexParams) super.position(j);
    }

    public LinearIndexParams getPointer(long j) {
        return new LinearIndexParams((Pointer) this).position(this.position + j);
    }

    public LinearIndexParams() {
        super((Pointer) null);
        allocate();
    }
}
