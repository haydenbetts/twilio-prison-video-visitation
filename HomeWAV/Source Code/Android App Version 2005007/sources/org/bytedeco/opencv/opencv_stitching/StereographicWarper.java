package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
public class StereographicWarper extends WarperCreator {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public StereographicWarper() {
        super((Pointer) null);
        allocate();
    }

    public StereographicWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public StereographicWarper(Pointer pointer) {
        super(pointer);
    }

    public StereographicWarper position(long j) {
        return (StereographicWarper) super.position(j);
    }

    public StereographicWarper getPointer(long j) {
        return new StereographicWarper((Pointer) this).position(this.position + j);
    }
}
