package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.AbstractCvFont;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
public class CvFont extends AbstractCvFont {
    private native void allocate();

    private native void allocateArray(long j);

    @Const
    public native IntPointer ascii();

    public native CvFont ascii(IntPointer intPointer);

    @ByRef
    public native CvScalar color();

    public native CvFont color(CvScalar cvScalar);

    @Const
    public native IntPointer cyrillic();

    public native CvFont cyrillic(IntPointer intPointer);

    public native float dx();

    public native CvFont dx(float f);

    public native int font_face();

    public native CvFont font_face(int i);

    @Const
    public native IntPointer greek();

    public native CvFont greek(IntPointer intPointer);

    public native float hscale();

    public native CvFont hscale(float f);

    public native int line_type();

    public native CvFont line_type(int i);

    @Cast({"const char*"})
    public native BytePointer nameFont();

    public native CvFont nameFont(BytePointer bytePointer);

    public native float shear();

    public native CvFont shear(float f);

    public native int thickness();

    public native CvFont thickness(int i);

    public native float vscale();

    public native CvFont vscale(float f);

    static {
        Loader.load();
    }

    public CvFont() {
        super((Pointer) null);
        allocate();
    }

    public CvFont(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvFont(Pointer pointer) {
        super(pointer);
    }

    public CvFont position(long j) {
        return (CvFont) super.position(j);
    }

    public CvFont getPointer(long j) {
        return new CvFont((Pointer) this).position(this.position + j);
    }
}
