package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class AVBufferSrcParameters extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint64_t"})
    public native long channel_layout();

    public native AVBufferSrcParameters channel_layout(long j);

    public native int format();

    public native AVBufferSrcParameters format(int i);

    public native AVBufferSrcParameters frame_rate(AVRational aVRational);

    @ByRef
    public native AVRational frame_rate();

    public native int height();

    public native AVBufferSrcParameters height(int i);

    public native AVBufferSrcParameters hw_frames_ctx(AVBufferRef aVBufferRef);

    public native AVBufferRef hw_frames_ctx();

    public native AVBufferSrcParameters sample_aspect_ratio(AVRational aVRational);

    @ByRef
    public native AVRational sample_aspect_ratio();

    public native int sample_rate();

    public native AVBufferSrcParameters sample_rate(int i);

    public native AVBufferSrcParameters time_base(AVRational aVRational);

    @ByRef
    public native AVRational time_base();

    public native int width();

    public native AVBufferSrcParameters width(int i);

    static {
        Loader.load();
    }

    public AVBufferSrcParameters() {
        super((Pointer) null);
        allocate();
    }

    public AVBufferSrcParameters(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVBufferSrcParameters(Pointer pointer) {
        super(pointer);
    }

    public AVBufferSrcParameters position(long j) {
        return (AVBufferSrcParameters) super.position(j);
    }

    public AVBufferSrcParameters getPointer(long j) {
        return new AVBufferSrcParameters((Pointer) this).position(this.position + j);
    }
}
