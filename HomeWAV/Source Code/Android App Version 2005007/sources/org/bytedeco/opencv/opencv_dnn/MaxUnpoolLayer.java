package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class MaxUnpoolLayer extends Layer {
    @opencv_core.Ptr
    public static native MaxUnpoolLayer create(@ByRef @Const LayerParams layerParams);

    @ByRef
    public native Size poolKernel();

    public native MaxUnpoolLayer poolKernel(Size size);

    @ByRef
    public native Size poolPad();

    public native MaxUnpoolLayer poolPad(Size size);

    @ByRef
    public native Size poolStride();

    public native MaxUnpoolLayer poolStride(Size size);

    static {
        Loader.load();
    }

    public MaxUnpoolLayer(Pointer pointer) {
        super(pointer);
    }
}
