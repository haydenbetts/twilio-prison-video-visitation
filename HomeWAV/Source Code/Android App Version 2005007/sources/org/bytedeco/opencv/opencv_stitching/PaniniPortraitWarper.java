package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class PaniniPortraitWarper extends WarperCreator {
    private native void allocate();

    private native void allocate(float f, float f2);

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public PaniniPortraitWarper(Pointer pointer) {
        super(pointer);
    }

    public PaniniPortraitWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public PaniniPortraitWarper position(long j) {
        return (PaniniPortraitWarper) super.position(j);
    }

    public PaniniPortraitWarper getPointer(long j) {
        return new PaniniPortraitWarper((Pointer) this).position(this.position + j);
    }

    public PaniniPortraitWarper(float f, float f2) {
        super((Pointer) null);
        allocate(f, f2);
    }

    public PaniniPortraitWarper() {
        super((Pointer) null);
        allocate();
    }
}
