package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class TickMeter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native double getAvgTimeMilli();

    public native double getAvgTimeSec();

    @Cast({"int64"})
    public native long getCounter();

    public native double getFPS();

    public native double getTimeMicro();

    public native double getTimeMilli();

    public native double getTimeSec();

    @Cast({"int64"})
    public native long getTimeTicks();

    public native void reset();

    public native void start();

    public native void stop();

    static {
        Loader.load();
    }

    public TickMeter(Pointer pointer) {
        super(pointer);
    }

    public TickMeter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TickMeter position(long j) {
        return (TickMeter) super.position(j);
    }

    public TickMeter getPointer(long j) {
        return new TickMeter((Pointer) this).position(this.position + j);
    }

    public TickMeter() {
        super((Pointer) null);
        allocate();
    }
}
