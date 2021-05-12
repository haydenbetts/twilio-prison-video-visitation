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
public class FlowWarpLayer extends Layer {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native FlowWarpLayer create(@ByRef @Const LayerParams layerParams);

    static {
        Loader.load();
    }

    public FlowWarpLayer() {
        super((Pointer) null);
        allocate();
    }

    public FlowWarpLayer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FlowWarpLayer(Pointer pointer) {
        super(pointer);
    }

    public FlowWarpLayer position(long j) {
        return (FlowWarpLayer) super.position(j);
    }

    public FlowWarpLayer getPointer(long j) {
        return new FlowWarpLayer((Pointer) this).position(this.position + j);
    }
}
