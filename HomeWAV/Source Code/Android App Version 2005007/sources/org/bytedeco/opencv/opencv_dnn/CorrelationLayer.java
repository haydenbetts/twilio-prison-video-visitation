package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class CorrelationLayer extends Layer {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native CorrelationLayer create(@ByRef @Const LayerParams layerParams);

    static {
        Loader.load();
    }

    public CorrelationLayer() {
        super((Pointer) null);
        allocate();
    }

    public CorrelationLayer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CorrelationLayer(Pointer pointer) {
        super(pointer);
    }

    public CorrelationLayer position(long j) {
        return (CorrelationLayer) super.position(j);
    }

    public CorrelationLayer getPointer(long j) {
        return new CorrelationLayer((Pointer) this).position(this.position + j);
    }
}
