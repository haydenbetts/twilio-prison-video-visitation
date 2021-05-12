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
public class DataAugmentationLayer extends Layer {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native DataAugmentationLayer create(@ByRef @Const LayerParams layerParams);

    static {
        Loader.load();
    }

    public DataAugmentationLayer() {
        super((Pointer) null);
        allocate();
    }

    public DataAugmentationLayer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public DataAugmentationLayer(Pointer pointer) {
        super(pointer);
    }

    public DataAugmentationLayer position(long j) {
        return (DataAugmentationLayer) super.position(j);
    }

    public DataAugmentationLayer getPointer(long j) {
        return new DataAugmentationLayer((Pointer) this).position(this.position + j);
    }
}
