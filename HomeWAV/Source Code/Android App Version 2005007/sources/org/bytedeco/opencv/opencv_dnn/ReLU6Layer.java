package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class ReLU6Layer extends ActivationLayer {
    @opencv_core.Ptr
    public static native ReLU6Layer create(@ByRef @Const LayerParams layerParams);

    public native float maxValue();

    public native ReLU6Layer maxValue(float f);

    public native float minValue();

    public native ReLU6Layer minValue(float f);

    static {
        Loader.load();
    }

    public ReLU6Layer(Pointer pointer) {
        super(pointer);
    }
}
