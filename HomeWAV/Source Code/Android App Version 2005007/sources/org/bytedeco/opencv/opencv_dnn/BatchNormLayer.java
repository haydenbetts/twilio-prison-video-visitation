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
public class BatchNormLayer extends ActivationLayer {
    @opencv_core.Ptr
    public static native BatchNormLayer create(@ByRef @Const LayerParams layerParams);

    public native float epsilon();

    public native BatchNormLayer epsilon(float f);

    public native BatchNormLayer hasBias(boolean z);

    @Cast({"bool"})
    public native boolean hasBias();

    public native BatchNormLayer hasWeights(boolean z);

    @Cast({"bool"})
    public native boolean hasWeights();

    static {
        Loader.load();
    }

    public BatchNormLayer(Pointer pointer) {
        super(pointer);
    }
}
