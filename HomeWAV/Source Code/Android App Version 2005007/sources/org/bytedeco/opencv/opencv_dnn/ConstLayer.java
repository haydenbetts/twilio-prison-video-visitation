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
public class ConstLayer extends Layer {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native Layer create(@ByRef @Const LayerParams layerParams);

    static {
        Loader.load();
    }

    public ConstLayer() {
        super((Pointer) null);
        allocate();
    }

    public ConstLayer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ConstLayer(Pointer pointer) {
        super(pointer);
    }

    public ConstLayer position(long j) {
        return (ConstLayer) super.position(j);
    }

    public ConstLayer getPointer(long j) {
        return new ConstLayer((Pointer) this).position(this.position + j);
    }
}
