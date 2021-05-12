package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class AVABufferSinkParams extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int all_channel_counts();

    public native AVABufferSinkParams all_channel_counts(int i);

    public native AVABufferSinkParams channel_counts(IntPointer intPointer);

    @Const
    public native IntPointer channel_counts();

    public native AVABufferSinkParams channel_layouts(LongPointer longPointer);

    @Cast({"const int64_t*"})
    public native LongPointer channel_layouts();

    public native AVABufferSinkParams sample_fmts(IntPointer intPointer);

    @Cast({"const AVSampleFormat*"})
    public native IntPointer sample_fmts();

    public native AVABufferSinkParams sample_rates(IntPointer intPointer);

    public native IntPointer sample_rates();

    static {
        Loader.load();
    }

    public AVABufferSinkParams() {
        super((Pointer) null);
        allocate();
    }

    public AVABufferSinkParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVABufferSinkParams(Pointer pointer) {
        super(pointer);
    }

    public AVABufferSinkParams position(long j) {
        return (AVABufferSinkParams) super.position(j);
    }

    public AVABufferSinkParams getPointer(long j) {
        return new AVABufferSinkParams((Pointer) this).position(this.position + j);
    }
}
