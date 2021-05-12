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
public class PowerLayer extends ActivationLayer {
    @opencv_core.Ptr
    public static native PowerLayer create(@ByRef @Const LayerParams layerParams);

    public native float power();

    public native PowerLayer power(float f);

    public native float scale();

    public native PowerLayer scale(float f);

    public native float shift();

    public native PowerLayer shift(float f);

    static {
        Loader.load();
    }

    public PowerLayer(Pointer pointer) {
        super(pointer);
    }
}
