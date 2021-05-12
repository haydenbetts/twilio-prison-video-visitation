package org.bytedeco.opencv.opencv_highgui;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.presets.opencv_highgui;

@Namespace("cv")
@Properties(inherit = {opencv_highgui.class})
public class QtFont extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Const
    public native IntPointer ascii();

    public native QtFont ascii(IntPointer intPointer);

    @ByRef
    public native Scalar color();

    public native QtFont color(Scalar scalar);

    @Const
    public native IntPointer cyrillic();

    public native QtFont cyrillic(IntPointer intPointer);

    public native float dx();

    public native QtFont dx(float f);

    public native int font_face();

    public native QtFont font_face(int i);

    @Const
    public native IntPointer greek();

    public native QtFont greek(IntPointer intPointer);

    public native float hscale();

    public native QtFont hscale(float f);

    public native int line_type();

    public native QtFont line_type(int i);

    @Cast({"const char*"})
    public native BytePointer nameFont();

    public native QtFont nameFont(BytePointer bytePointer);

    public native float shear();

    public native QtFont shear(float f);

    public native int thickness();

    public native QtFont thickness(int i);

    public native float vscale();

    public native QtFont vscale(float f);

    static {
        Loader.load();
    }

    public QtFont() {
        super((Pointer) null);
        allocate();
    }

    public QtFont(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public QtFont(Pointer pointer) {
        super(pointer);
    }

    public QtFont position(long j) {
        return (QtFont) super.position(j);
    }

    public QtFont getPointer(long j) {
        return new QtFont((Pointer) this).position(this.position + j);
    }
}
