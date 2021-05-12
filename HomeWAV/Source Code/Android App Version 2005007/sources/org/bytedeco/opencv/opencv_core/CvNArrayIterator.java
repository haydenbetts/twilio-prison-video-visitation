package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvNArrayIterator extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int count();

    public native CvNArrayIterator count(int i);

    public native int dims();

    public native CvNArrayIterator dims(int i);

    @MemberGetter
    @Cast({"CvMatND**"})
    public native PointerPointer hdr();

    public native CvMatND hdr(int i);

    public native CvNArrayIterator hdr(int i, CvMatND cvMatND);

    @Cast({"uchar*"})
    public native BytePointer ptr(int i);

    @MemberGetter
    @Cast({"uchar**"})
    public native PointerPointer ptr();

    public native CvNArrayIterator ptr(int i, BytePointer bytePointer);

    public native CvNArrayIterator size(CvSize cvSize);

    @ByRef
    public native CvSize size();

    public native int stack(int i);

    @MemberGetter
    public native IntPointer stack();

    public native CvNArrayIterator stack(int i, int i2);

    static {
        Loader.load();
    }

    public CvNArrayIterator() {
        super((Pointer) null);
        allocate();
    }

    public CvNArrayIterator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvNArrayIterator(Pointer pointer) {
        super(pointer);
    }

    public CvNArrayIterator position(long j) {
        return (CvNArrayIterator) super.position(j);
    }

    public CvNArrayIterator getPointer(long j) {
        return new CvNArrayIterator((Pointer) this).position(this.position + j);
    }
}
