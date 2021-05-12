package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
public class CylindricalWarper extends WarperCreator {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public CylindricalWarper() {
        super((Pointer) null);
        allocate();
    }

    public CylindricalWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CylindricalWarper(Pointer pointer) {
        super(pointer);
    }

    public CylindricalWarper position(long j) {
        return (CylindricalWarper) super.position(j);
    }

    public CylindricalWarper getPointer(long j) {
        return new CylindricalWarper((Pointer) this).position(this.position + j);
    }
}
