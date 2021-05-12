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
public class ColorInpainter extends InpainterBase {
    private native void allocate();

    private native void allocate(int i, double d);

    private native void allocateArray(long j);

    public native void inpaint(int i, @ByRef Mat mat, @ByRef Mat mat2);

    static {
        Loader.load();
    }

    public ColorInpainter(Pointer pointer) {
        super(pointer);
    }

    public ColorInpainter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ColorInpainter position(long j) {
        return (ColorInpainter) super.position(j);
    }

    public ColorInpainter getPointer(long j) {
        return new ColorInpainter((Pointer) this).position(this.position + j);
    }

    public ColorInpainter(int i, double d) {
        super((Pointer) null);
        allocate(i, d);
    }

    public ColorInpainter() {
        super((Pointer) null);
        allocate();
    }
}
