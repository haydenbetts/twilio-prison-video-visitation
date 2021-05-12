package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvScalar extends AbstractCvScalar {
    private native void allocate();

    private native void allocateArray(long j);

    public native double val(int i);

    @MemberGetter
    public native DoublePointer val();

    public native CvScalar val(int i, double d);

    static {
        Loader.load();
    }

    public CvScalar(Pointer pointer) {
        super(pointer);
    }

    public CvScalar(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvScalar position(long j) {
        return (CvScalar) super.position(j);
    }

    public CvScalar getPointer(long j) {
        return new CvScalar((Pointer) this).position(this.position + j);
    }

    public CvScalar() {
        super((Pointer) null);
        allocate();
    }
}
