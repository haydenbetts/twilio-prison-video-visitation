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
public class AccumLayer extends Layer {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native AccumLayer create(@ByRef @Const LayerParams layerParams);

    static {
        Loader.load();
    }

    public AccumLayer() {
        super((Pointer) null);
        allocate();
    }

    public AccumLayer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AccumLayer(Pointer pointer) {
        super(pointer);
    }

    public AccumLayer position(long j) {
        return (AccumLayer) super.position(j);
    }

    public AccumLayer getPointer(long j) {
        return new AccumLayer((Pointer) this).position(this.position + j);
    }
}
