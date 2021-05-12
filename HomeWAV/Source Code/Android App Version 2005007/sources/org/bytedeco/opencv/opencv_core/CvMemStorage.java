package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvMemStorage extends AbstractCvMemStorage {
    private native void allocate();

    private native void allocateArray(long j);

    public native int block_size();

    public native CvMemStorage block_size(int i);

    public native CvMemBlock bottom();

    public native CvMemStorage bottom(CvMemBlock cvMemBlock);

    public native int free_space();

    public native CvMemStorage free_space(int i);

    public native CvMemStorage parent();

    public native CvMemStorage parent(CvMemStorage cvMemStorage);

    public native int signature();

    public native CvMemStorage signature(int i);

    public native CvMemBlock top();

    public native CvMemStorage top(CvMemBlock cvMemBlock);

    static {
        Loader.load();
    }

    public CvMemStorage() {
        super((Pointer) null);
        allocate();
    }

    public CvMemStorage(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvMemStorage(Pointer pointer) {
        super(pointer);
    }

    public CvMemStorage position(long j) {
        return (CvMemStorage) super.position(j);
    }

    public CvMemStorage getPointer(long j) {
        return new CvMemStorage((Pointer) this).position(this.position + j);
    }
}
