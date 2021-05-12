package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVRegionOfInterest extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int bottom();

    public native AVRegionOfInterest bottom(int i);

    public native int left();

    public native AVRegionOfInterest left(int i);

    @ByRef
    public native AVRational qoffset();

    public native AVRegionOfInterest qoffset(AVRational aVRational);

    public native int right();

    public native AVRegionOfInterest right(int i);

    @Cast({"uint32_t"})
    public native int self_size();

    public native AVRegionOfInterest self_size(int i);

    public native int top();

    public native AVRegionOfInterest top(int i);

    static {
        Loader.load();
    }

    public AVRegionOfInterest() {
        super((Pointer) null);
        allocate();
    }

    public AVRegionOfInterest(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVRegionOfInterest(Pointer pointer) {
        super(pointer);
    }

    public AVRegionOfInterest position(long j) {
        return (AVRegionOfInterest) super.position(j);
    }

    public AVRegionOfInterest getPointer(long j) {
        return new AVRegionOfInterest((Pointer) this).position(this.position + j);
    }
}
