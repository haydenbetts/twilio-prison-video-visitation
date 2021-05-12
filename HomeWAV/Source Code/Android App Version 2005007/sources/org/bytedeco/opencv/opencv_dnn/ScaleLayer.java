package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class ScaleLayer extends Layer {
    @opencv_core.Ptr
    public static native ScaleLayer create(@ByRef @Const LayerParams layerParams);

    public native int axis();

    public native ScaleLayer axis(int i);

    public native ScaleLayer hasBias(boolean z);

    @Cast({"bool"})
    public native boolean hasBias();

    static {
        Loader.load();
    }

    public ScaleLayer(Pointer pointer) {
        super(pointer);
    }
}
