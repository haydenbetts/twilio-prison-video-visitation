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
public class ColorAverageInpainter extends InpainterBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native void inpaint(int i, @ByRef Mat mat, @ByRef Mat mat2);

    static {
        Loader.load();
    }

    public ColorAverageInpainter() {
        super((Pointer) null);
        allocate();
    }

    public ColorAverageInpainter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ColorAverageInpainter(Pointer pointer) {
        super(pointer);
    }

    public ColorAverageInpainter position(long j) {
        return (ColorAverageInpainter) super.position(j);
    }

    public ColorAverageInpainter getPointer(long j) {
        return new ColorAverageInpainter((Pointer) this).position(this.position + j);
    }
}
