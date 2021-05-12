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
public class MVNLayer extends Layer {
    @opencv_core.Ptr
    public static native MVNLayer create(@ByRef @Const LayerParams layerParams);

    public native MVNLayer acrossChannels(boolean z);

    @Cast({"bool"})
    public native boolean acrossChannels();

    public native float eps();

    public native MVNLayer eps(float f);

    public native MVNLayer normVariance(boolean z);

    @Cast({"bool"})
    public native boolean normVariance();

    static {
        Loader.load();
    }

    public MVNLayer(Pointer pointer) {
        super(pointer);
    }
}
