package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::ocl::Platform"})
@NoOffset
public class OclPlatform extends Pointer {
    private native void allocate();

    private native void allocate(@ByRef @Const OclPlatform oclPlatform);

    private native void allocateArray(long j);

    @ByRef
    public static native OclPlatform getDefault();

    public native Pointer ptr();

    @ByRef
    @Name({"operator ="})
    public native OclPlatform put(@ByRef @Const OclPlatform oclPlatform);

    static {
        Loader.load();
    }

    public OclPlatform(Pointer pointer) {
        super(pointer);
    }

    public OclPlatform(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public OclPlatform position(long j) {
        return (OclPlatform) super.position(j);
    }

    public OclPlatform getPointer(long j) {
        return new OclPlatform(this).position(this.position + j);
    }

    public OclPlatform() {
        super((Pointer) null);
        allocate();
    }

    public OclPlatform(@ByRef @Const OclPlatform oclPlatform) {
        super((Pointer) null);
        allocate(oclPlatform);
    }
}
