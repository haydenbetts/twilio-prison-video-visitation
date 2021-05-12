package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
public class MercatorWarper extends WarperCreator {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public MercatorWarper() {
        super((Pointer) null);
        allocate();
    }

    public MercatorWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MercatorWarper(Pointer pointer) {
        super(pointer);
    }

    public MercatorWarper position(long j) {
        return (MercatorWarper) super.position(j);
    }

    public MercatorWarper getPointer(long j) {
        return new MercatorWarper((Pointer) this).position(this.position + j);
    }
}
