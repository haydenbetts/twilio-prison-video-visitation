package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvMemStoragePos extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int free_space();

    public native CvMemStoragePos free_space(int i);

    public native CvMemBlock top();

    public native CvMemStoragePos top(CvMemBlock cvMemBlock);

    static {
        Loader.load();
    }

    public CvMemStoragePos() {
        super((Pointer) null);
        allocate();
    }

    public CvMemStoragePos(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvMemStoragePos(Pointer pointer) {
        super(pointer);
    }

    public CvMemStoragePos position(long j) {
        return (CvMemStoragePos) super.position(j);
    }

    public CvMemStoragePos getPointer(long j) {
        return new CvMemStoragePos((Pointer) this).position(this.position + j);
    }
}
