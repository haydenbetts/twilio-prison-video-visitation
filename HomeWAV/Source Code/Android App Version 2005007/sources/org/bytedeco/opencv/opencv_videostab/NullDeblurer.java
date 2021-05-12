package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class NullDeblurer extends DeblurerBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native void deblur(int i, @ByRef Mat mat, @ByRef @Const Range range);

    static {
        Loader.load();
    }

    public NullDeblurer() {
        super((Pointer) null);
        allocate();
    }

    public NullDeblurer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NullDeblurer(Pointer pointer) {
        super(pointer);
    }

    public NullDeblurer position(long j) {
        return (NullDeblurer) super.position(j);
    }

    public NullDeblurer getPointer(long j) {
        return new NullDeblurer((Pointer) this).position(this.position + j);
    }
}
