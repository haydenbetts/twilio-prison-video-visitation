package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::DefaultDeleter<CvMat>"})
public class CvMatDefaultDeleter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"operator ()"})
    public native void apply(CvMat cvMat);

    static {
        Loader.load();
    }

    public CvMatDefaultDeleter() {
        super((Pointer) null);
        allocate();
    }

    public CvMatDefaultDeleter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvMatDefaultDeleter(Pointer pointer) {
        super(pointer);
    }

    public CvMatDefaultDeleter position(long j) {
        return (CvMatDefaultDeleter) super.position(j);
    }

    public CvMatDefaultDeleter getPointer(long j) {
        return new CvMatDefaultDeleter((Pointer) this).position(this.position + j);
    }
}
