package org.bytedeco.ffmpeg.avdevice;

import org.bytedeco.ffmpeg.presets.avdevice;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avdevice.class})
public class AVDeviceInfo extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVDeviceInfo device_description(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer device_description();

    public native AVDeviceInfo device_name(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer device_name();

    static {
        Loader.load();
    }

    public AVDeviceInfo() {
        super((Pointer) null);
        allocate();
    }

    public AVDeviceInfo(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVDeviceInfo(Pointer pointer) {
        super(pointer);
    }

    public AVDeviceInfo position(long j) {
        return (AVDeviceInfo) super.position(j);
    }

    public AVDeviceInfo getPointer(long j) {
        return new AVDeviceInfo((Pointer) this).position(this.position + j);
    }
}
