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
public class InnerProductLayer extends Layer {
    @opencv_core.Ptr
    public static native InnerProductLayer create(@ByRef @Const LayerParams layerParams);

    public native int axis();

    public native InnerProductLayer axis(int i);

    static {
        Loader.load();
    }

    public InnerProductLayer(Pointer pointer) {
        super(pointer);
    }
}
