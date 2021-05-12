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
public class PaniniWarper extends WarperCreator {
    private native void allocate();

    private native void allocate(float f, float f2);

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public PaniniWarper(Pointer pointer) {
        super(pointer);
    }

    public PaniniWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public PaniniWarper position(long j) {
        return (PaniniWarper) super.position(j);
    }

    public PaniniWarper getPointer(long j) {
        return new PaniniWarper((Pointer) this).position(this.position + j);
    }

    public PaniniWarper(float f, float f2) {
        super((Pointer) null);
        allocate(f, f2);
    }

    public PaniniWarper() {
        super((Pointer) null);
        allocate();
    }
}
