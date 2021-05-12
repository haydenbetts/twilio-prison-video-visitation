package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVHWFramesConstraints extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int max_height();

    public native AVHWFramesConstraints max_height(int i);

    public native int max_width();

    public native AVHWFramesConstraints max_width(int i);

    public native int min_height();

    public native AVHWFramesConstraints min_height(int i);

    public native int min_width();

    public native AVHWFramesConstraints min_width(int i);

    public native AVHWFramesConstraints valid_hw_formats(IntPointer intPointer);

    @Cast({"AVPixelFormat*"})
    public native IntPointer valid_hw_formats();

    public native AVHWFramesConstraints valid_sw_formats(IntPointer intPointer);

    @Cast({"AVPixelFormat*"})
    public native IntPointer valid_sw_formats();

    static {
        Loader.load();
    }

    public AVHWFramesConstraints() {
        super((Pointer) null);
        allocate();
    }

    public AVHWFramesConstraints(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVHWFramesConstraints(Pointer pointer) {
        super(pointer);
    }

    public AVHWFramesConstraints position(long j) {
        return (AVHWFramesConstraints) super.position(j);
    }

    public AVHWFramesConstraints getPointer(long j) {
        return new AVHWFramesConstraints((Pointer) this).position(this.position + j);
    }
}
