package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvLineIterator extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int err();

    public native CvLineIterator err(int i);

    public native int minus_delta();

    public native CvLineIterator minus_delta(int i);

    public native int minus_step();

    public native CvLineIterator minus_step(int i);

    public native int plus_delta();

    public native CvLineIterator plus_delta(int i);

    public native int plus_step();

    public native CvLineIterator plus_step(int i);

    @Cast({"uchar*"})
    public native BytePointer ptr();

    public native CvLineIterator ptr(BytePointer bytePointer);

    static {
        Loader.load();
    }

    public CvLineIterator() {
        super((Pointer) null);
        allocate();
    }

    public CvLineIterator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvLineIterator(Pointer pointer) {
        super(pointer);
    }

    public CvLineIterator position(long j) {
        return (CvLineIterator) super.position(j);
    }

    public CvLineIterator getPointer(long j) {
        return new CvLineIterator((Pointer) this).position(this.position + j);
    }
}
