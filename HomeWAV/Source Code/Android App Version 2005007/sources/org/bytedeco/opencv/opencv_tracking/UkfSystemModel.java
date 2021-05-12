package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv::tracking")
@Properties(inherit = {opencv_tracking.class})
public class UkfSystemModel extends Pointer {
    public native void measurementFunction(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef Mat mat3);

    public native void stateConversionFunction(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, @ByRef Mat mat4);

    static {
        Loader.load();
    }

    public UkfSystemModel(Pointer pointer) {
        super(pointer);
    }
}
