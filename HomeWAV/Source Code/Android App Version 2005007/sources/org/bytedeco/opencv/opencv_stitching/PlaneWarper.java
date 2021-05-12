package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
public class PlaneWarper extends WarperCreator {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public PlaneWarper() {
        super((Pointer) null);
        allocate();
    }

    public PlaneWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public PlaneWarper(Pointer pointer) {
        super(pointer);
    }

    public PlaneWarper position(long j) {
        return (PlaneWarper) super.position(j);
    }

    public PlaneWarper getPointer(long j) {
        return new PlaneWarper((Pointer) this).position(this.position + j);
    }
}
