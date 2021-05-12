package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::ocl")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class PlatformInfo extends Pointer {
    private native void allocate();

    private native void allocate(Pointer pointer);

    private native void allocate(@ByRef @Const PlatformInfo platformInfo);

    private native void allocateArray(long j);

    public native int deviceNumber();

    public native void getDevice(@ByRef Device device, int i);

    @opencv_core.Str
    public native BytePointer name();

    @ByRef
    @Name({"operator ="})
    public native PlatformInfo put(@ByRef @Const PlatformInfo platformInfo);

    @opencv_core.Str
    public native BytePointer vendor();

    @opencv_core.Str
    public native BytePointer version();

    static {
        Loader.load();
    }

    public PlatformInfo(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public PlatformInfo position(long j) {
        return (PlatformInfo) super.position(j);
    }

    public PlatformInfo getPointer(long j) {
        return new PlatformInfo(this).position(this.position + j);
    }

    public PlatformInfo() {
        super((Pointer) null);
        allocate();
    }

    public PlatformInfo(Pointer pointer) {
        super((Pointer) null);
        allocate(pointer);
    }

    public PlatformInfo(@ByRef @Const PlatformInfo platformInfo) {
        super((Pointer) null);
        allocate(platformInfo);
    }
}
