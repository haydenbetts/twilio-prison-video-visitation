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
public class CompressedRectilinearPortraitWarper extends WarperCreator {
    private native void allocate();

    private native void allocate(float f, float f2);

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public CompressedRectilinearPortraitWarper(Pointer pointer) {
        super(pointer);
    }

    public CompressedRectilinearPortraitWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CompressedRectilinearPortraitWarper position(long j) {
        return (CompressedRectilinearPortraitWarper) super.position(j);
    }

    public CompressedRectilinearPortraitWarper getPointer(long j) {
        return new CompressedRectilinearPortraitWarper((Pointer) this).position(this.position + j);
    }

    public CompressedRectilinearPortraitWarper(float f, float f2) {
        super((Pointer) null);
        allocate(f, f2);
    }

    public CompressedRectilinearPortraitWarper() {
        super((Pointer) null);
        allocate();
    }
}
