package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class ConsistentMosaicInpainter extends InpainterBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native void inpaint(int i, @ByRef Mat mat, @ByRef Mat mat2);

    public native void setStdevThresh(float f);

    public native float stdevThresh();

    static {
        Loader.load();
    }

    public ConsistentMosaicInpainter(Pointer pointer) {
        super(pointer);
    }

    public ConsistentMosaicInpainter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ConsistentMosaicInpainter position(long j) {
        return (ConsistentMosaicInpainter) super.position(j);
    }

    public ConsistentMosaicInpainter getPointer(long j) {
        return new ConsistentMosaicInpainter((Pointer) this).position(this.position + j);
    }

    public ConsistentMosaicInpainter() {
        super((Pointer) null);
        allocate();
    }
}
