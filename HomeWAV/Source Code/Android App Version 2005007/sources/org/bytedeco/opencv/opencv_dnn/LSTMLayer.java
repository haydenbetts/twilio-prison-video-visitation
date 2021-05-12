package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class LSTMLayer extends Layer {
    @opencv_core.Ptr
    public static native LSTMLayer create(@ByRef @Const LayerParams layerParams);

    public native int inputNameToIndex(@opencv_core.Str String str);

    public native int inputNameToIndex(@opencv_core.Str BytePointer bytePointer);

    public native int outputNameToIndex(@opencv_core.Str String str);

    public native int outputNameToIndex(@opencv_core.Str BytePointer bytePointer);

    public native void setOutShape();

    public native void setOutShape(@ByRef(nullValue = "cv::dnn::MatShape()") @Const @StdVector IntPointer intPointer);

    @Deprecated
    public native void setProduceCellOutput();

    @Deprecated
    public native void setProduceCellOutput(@Cast({"bool"}) boolean z);

    @Deprecated
    public native void setUseTimstampsDim();

    @Deprecated
    public native void setUseTimstampsDim(@Cast({"bool"}) boolean z);

    @Deprecated
    public native void setWeights(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3);

    static {
        Loader.load();
    }

    public LSTMLayer(Pointer pointer) {
        super(pointer);
    }
}
