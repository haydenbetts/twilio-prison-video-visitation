package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVReplayGain extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int album_gain();

    public native AVReplayGain album_gain(int i);

    @Cast({"uint32_t"})
    public native int album_peak();

    public native AVReplayGain album_peak(int i);

    public native int track_gain();

    public native AVReplayGain track_gain(int i);

    @Cast({"uint32_t"})
    public native int track_peak();

    public native AVReplayGain track_peak(int i);

    static {
        Loader.load();
    }

    public AVReplayGain() {
        super((Pointer) null);
        allocate();
    }

    public AVReplayGain(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVReplayGain(Pointer pointer) {
        super(pointer);
    }

    public AVReplayGain position(long j) {
        return (AVReplayGain) super.position(j);
    }

    public AVReplayGain getPointer(long j) {
        return new AVReplayGain((Pointer) this).position(this.position + j);
    }
}
