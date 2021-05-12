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
public class NormalizeBBoxLayer extends Layer {
    @opencv_core.Ptr
    public static native NormalizeBBoxLayer create(@ByRef @Const LayerParams layerParams);

    public native NormalizeBBoxLayer acrossSpatial(boolean z);

    @Deprecated
    @Cast({"bool"})
    public native boolean acrossSpatial();

    public native float epsilon();

    public native NormalizeBBoxLayer epsilon(float f);

    public native float pnorm();

    public native NormalizeBBoxLayer pnorm(float f);

    static {
        Loader.load();
    }

    public NormalizeBBoxLayer(Pointer pointer) {
        super(pointer);
    }
}
