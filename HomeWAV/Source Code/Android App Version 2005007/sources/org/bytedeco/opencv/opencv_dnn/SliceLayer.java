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
public class SliceLayer extends Layer {
    @opencv_core.Ptr
    public static native SliceLayer create(@ByRef @Const LayerParams layerParams);

    public native int axis();

    public native SliceLayer axis(int i);

    public native int num_split();

    public native SliceLayer num_split(int i);

    @ByRef
    public native RangeVectorVector sliceRanges();

    public native SliceLayer sliceRanges(RangeVectorVector rangeVectorVector);

    static {
        Loader.load();
    }

    public SliceLayer(Pointer pointer) {
        super(pointer);
    }
}
