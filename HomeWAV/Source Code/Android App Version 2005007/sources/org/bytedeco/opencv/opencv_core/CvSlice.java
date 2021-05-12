package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvSlice extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int end_index();

    public native CvSlice end_index(int i);

    public native int start_index();

    public native CvSlice start_index(int i);

    static {
        Loader.load();
    }

    public CvSlice(Pointer pointer) {
        super(pointer);
    }

    public CvSlice(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSlice position(long j) {
        return (CvSlice) super.position(j);
    }

    public CvSlice getPointer(long j) {
        return new CvSlice((Pointer) this).position(this.position + j);
    }

    public CvSlice() {
        super((Pointer) null);
        allocate();
    }
}
