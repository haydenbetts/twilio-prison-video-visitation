package org.bytedeco.ffmpeg.avdevice;

import org.bytedeco.ffmpeg.presets.avdevice;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avdevice.class})
public class AVDeviceRect extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int height();

    public native AVDeviceRect height(int i);

    public native int width();

    public native AVDeviceRect width(int i);

    public native int x();

    public native AVDeviceRect x(int i);

    public native int y();

    public native AVDeviceRect y(int i);

    static {
        Loader.load();
    }

    public AVDeviceRect() {
        super((Pointer) null);
        allocate();
    }

    public AVDeviceRect(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVDeviceRect(Pointer pointer) {
        super(pointer);
    }

    public AVDeviceRect position(long j) {
        return (AVDeviceRect) super.position(j);
    }

    public AVDeviceRect getPointer(long j) {
        return new AVDeviceRect((Pointer) this).position(this.position + j);
    }
}
