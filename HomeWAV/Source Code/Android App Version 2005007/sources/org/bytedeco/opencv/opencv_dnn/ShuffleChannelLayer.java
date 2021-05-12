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
public class ShuffleChannelLayer extends Layer {
    @opencv_core.Ptr
    public static native Layer create(@ByRef @Const LayerParams layerParams);

    public native int group();

    public native ShuffleChannelLayer group(int i);

    static {
        Loader.load();
    }

    public ShuffleChannelLayer(Pointer pointer) {
        super(pointer);
    }
}
