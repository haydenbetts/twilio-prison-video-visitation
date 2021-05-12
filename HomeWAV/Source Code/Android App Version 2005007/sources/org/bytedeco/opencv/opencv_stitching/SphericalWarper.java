package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
public class SphericalWarper extends WarperCreator {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public SphericalWarper() {
        super((Pointer) null);
        allocate();
    }

    public SphericalWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SphericalWarper(Pointer pointer) {
        super(pointer);
    }

    public SphericalWarper position(long j) {
        return (SphericalWarper) super.position(j);
    }

    public SphericalWarper getPointer(long j) {
        return new SphericalWarper((Pointer) this).position(this.position + j);
    }
}
