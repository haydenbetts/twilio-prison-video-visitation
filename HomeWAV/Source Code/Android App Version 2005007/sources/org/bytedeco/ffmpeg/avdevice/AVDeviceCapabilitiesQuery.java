package org.bytedeco.ffmpeg.avdevice;

import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avdevice;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avdevice.class})
public class AVDeviceCapabilitiesQuery extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVDeviceCapabilitiesQuery av_class(AVClass aVClass);

    @Const
    public native AVClass av_class();

    @Cast({"int64_t"})
    public native long channel_layout();

    public native AVDeviceCapabilitiesQuery channel_layout(long j);

    public native int channels();

    public native AVDeviceCapabilitiesQuery channels(int i);

    @Cast({"AVCodecID"})
    public native int codec();

    public native AVDeviceCapabilitiesQuery codec(int i);

    public native AVDeviceCapabilitiesQuery device_context(AVFormatContext aVFormatContext);

    public native AVFormatContext device_context();

    public native AVDeviceCapabilitiesQuery fps(AVRational aVRational);

    @ByRef
    public native AVRational fps();

    public native int frame_height();

    public native AVDeviceCapabilitiesQuery frame_height(int i);

    public native int frame_width();

    public native AVDeviceCapabilitiesQuery frame_width(int i);

    @Cast({"AVPixelFormat"})
    public native int pixel_format();

    public native AVDeviceCapabilitiesQuery pixel_format(int i);

    @Cast({"AVSampleFormat"})
    public native int sample_format();

    public native AVDeviceCapabilitiesQuery sample_format(int i);

    public native int sample_rate();

    public native AVDeviceCapabilitiesQuery sample_rate(int i);

    public native int window_height();

    public native AVDeviceCapabilitiesQuery window_height(int i);

    public native int window_width();

    public native AVDeviceCapabilitiesQuery window_width(int i);

    static {
        Loader.load();
    }

    public AVDeviceCapabilitiesQuery() {
        super((Pointer) null);
        allocate();
    }

    public AVDeviceCapabilitiesQuery(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVDeviceCapabilitiesQuery(Pointer pointer) {
        super(pointer);
    }

    public AVDeviceCapabilitiesQuery position(long j) {
        return (AVDeviceCapabilitiesQuery) super.position(j);
    }

    public AVDeviceCapabilitiesQuery getPointer(long j) {
        return new AVDeviceCapabilitiesQuery((Pointer) this).position(this.position + j);
    }
}
