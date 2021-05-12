package org.bytedeco.opencv.opencv_flann;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
public class KDTreeIndexParams extends IndexParams {
    private native void allocate();

    private native void allocate(int i);

    static {
        Loader.load();
    }

    public KDTreeIndexParams(Pointer pointer) {
        super(pointer);
    }

    public KDTreeIndexParams(int i) {
        super((Pointer) null);
        allocate(i);
    }

    public KDTreeIndexParams() {
        super((Pointer) null);
        allocate();
    }
}
