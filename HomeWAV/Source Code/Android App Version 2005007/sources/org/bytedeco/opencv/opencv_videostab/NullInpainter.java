package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class NullInpainter extends InpainterBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native void inpaint(int i, @ByRef Mat mat, @ByRef Mat mat2);

    static {
        Loader.load();
    }

    public NullInpainter() {
        super((Pointer) null);
        allocate();
    }

    public NullInpainter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NullInpainter(Pointer pointer) {
        super(pointer);
    }

    public NullInpainter position(long j) {
        return (NullInpainter) super.position(j);
    }

    public NullInpainter getPointer(long j) {
        return new NullInpainter((Pointer) this).position(this.position + j);
    }
}
