package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class RNNLayer extends Layer {
    @opencv_core.Ptr
    public static native RNNLayer create(@ByRef @Const LayerParams layerParams);

    public native void setProduceHiddenOutput();

    public native void setProduceHiddenOutput(@Cast({"bool"}) boolean z);

    public native void setWeights(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, @ByRef @Const Mat mat4, @ByRef @Const Mat mat5);

    static {
        Loader.load();
    }

    public RNNLayer(Pointer pointer) {
        super(pointer);
    }
}
