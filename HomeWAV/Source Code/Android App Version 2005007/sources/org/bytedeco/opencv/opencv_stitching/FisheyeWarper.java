package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
public class FisheyeWarper extends WarperCreator {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public FisheyeWarper() {
        super((Pointer) null);
        allocate();
    }

    public FisheyeWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FisheyeWarper(Pointer pointer) {
        super(pointer);
    }

    public FisheyeWarper position(long j) {
        return (FisheyeWarper) super.position(j);
    }

    public FisheyeWarper getPointer(long j) {
        return new FisheyeWarper((Pointer) this).position(this.position + j);
    }
}
