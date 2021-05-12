package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
public class TransverseMercatorWarper extends WarperCreator {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public TransverseMercatorWarper() {
        super((Pointer) null);
        allocate();
    }

    public TransverseMercatorWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TransverseMercatorWarper(Pointer pointer) {
        super(pointer);
    }

    public TransverseMercatorWarper position(long j) {
        return (TransverseMercatorWarper) super.position(j);
    }

    public TransverseMercatorWarper getPointer(long j) {
        return new TransverseMercatorWarper((Pointer) this).position(this.position + j);
    }
}
