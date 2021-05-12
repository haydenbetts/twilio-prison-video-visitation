package org.bytedeco.ffmpeg.avdevice;

import org.bytedeco.ffmpeg.presets.avdevice;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avdevice.class})
public class AVDeviceInfoList extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int default_device();

    public native AVDeviceInfoList default_device(int i);

    public native AVDeviceInfo devices(int i);

    public native AVDeviceInfoList devices(int i, AVDeviceInfo aVDeviceInfo);

    public native AVDeviceInfoList devices(PointerPointer pointerPointer);

    @Cast({"AVDeviceInfo**"})
    public native PointerPointer devices();

    public native int nb_devices();

    public native AVDeviceInfoList nb_devices(int i);

    static {
        Loader.load();
    }

    public AVDeviceInfoList() {
        super((Pointer) null);
        allocate();
    }

    public AVDeviceInfoList(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVDeviceInfoList(Pointer pointer) {
        super(pointer);
    }

    public AVDeviceInfoList position(long j) {
        return (AVDeviceInfoList) super.position(j);
    }

    public AVDeviceInfoList getPointer(long j) {
        return new AVDeviceInfoList((Pointer) this).position(this.position + j);
    }
}
