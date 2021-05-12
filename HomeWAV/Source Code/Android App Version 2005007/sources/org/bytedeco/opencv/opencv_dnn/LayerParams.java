package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class LayerParams extends Dict {
    private native void allocate();

    private native void allocateArray(long j);

    @ByRef
    public native MatVector blobs();

    public native LayerParams blobs(MatVector matVector);

    @opencv_core.Str
    public native BytePointer name();

    public native LayerParams name(BytePointer bytePointer);

    @opencv_core.Str
    public native BytePointer type();

    public native LayerParams type(BytePointer bytePointer);

    static {
        Loader.load();
    }

    public LayerParams() {
        super((Pointer) null);
        allocate();
    }

    public LayerParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public LayerParams(Pointer pointer) {
        super(pointer);
    }

    public LayerParams position(long j) {
        return (LayerParams) super.position(j);
    }

    public LayerParams getPointer(long j) {
        return new LayerParams((Pointer) this).position(this.position + j);
    }
}
