package org.bytedeco.opencv.opencv_flann;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
public class LshIndexParams extends IndexParams {
    private native void allocate(int i, int i2, int i3);

    static {
        Loader.load();
    }

    public LshIndexParams(Pointer pointer) {
        super(pointer);
    }

    public LshIndexParams(int i, int i2, int i3) {
        super((Pointer) null);
        allocate(i, i2, i3);
    }
}
