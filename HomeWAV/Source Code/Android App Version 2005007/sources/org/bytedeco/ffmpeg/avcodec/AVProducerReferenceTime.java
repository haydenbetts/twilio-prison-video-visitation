package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVProducerReferenceTime extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int flags();

    public native AVProducerReferenceTime flags(int i);

    @Cast({"int64_t"})
    public native long wallclock();

    public native AVProducerReferenceTime wallclock(long j);

    static {
        Loader.load();
    }

    public AVProducerReferenceTime() {
        super((Pointer) null);
        allocate();
    }

    public AVProducerReferenceTime(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVProducerReferenceTime(Pointer pointer) {
        super(pointer);
    }

    public AVProducerReferenceTime position(long j) {
        return (AVProducerReferenceTime) super.position(j);
    }

    public AVProducerReferenceTime getPointer(long j) {
        return new AVProducerReferenceTime((Pointer) this).position(this.position + j);
    }
}
