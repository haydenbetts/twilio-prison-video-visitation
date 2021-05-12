package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVSubtitle extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint32_t"})
    public native int end_display_time();

    public native AVSubtitle end_display_time(int i);

    public native AVSubtitle format(short s);

    @Cast({"uint16_t"})
    public native short format();

    @Cast({"unsigned"})
    public native int num_rects();

    public native AVSubtitle num_rects(int i);

    @Cast({"int64_t"})
    public native long pts();

    public native AVSubtitle pts(long j);

    public native AVSubtitle rects(int i, AVSubtitleRect aVSubtitleRect);

    public native AVSubtitle rects(PointerPointer pointerPointer);

    public native AVSubtitleRect rects(int i);

    @Cast({"AVSubtitleRect**"})
    public native PointerPointer rects();

    @Cast({"uint32_t"})
    public native int start_display_time();

    public native AVSubtitle start_display_time(int i);

    static {
        Loader.load();
    }

    public AVSubtitle() {
        super((Pointer) null);
        allocate();
    }

    public AVSubtitle(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVSubtitle(Pointer pointer) {
        super(pointer);
    }

    public AVSubtitle position(long j) {
        return (AVSubtitle) super.position(j);
    }

    public AVSubtitle getPointer(long j) {
        return new AVSubtitle((Pointer) this).position(this.position + j);
    }
}
