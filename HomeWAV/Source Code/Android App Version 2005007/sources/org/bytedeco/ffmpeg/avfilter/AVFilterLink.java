package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class AVFilterLink extends Pointer {
    public static final int AVLINK_INIT = 2;
    public static final int AVLINK_STARTINIT = 1;
    public static final int AVLINK_UNINIT = 0;

    private native void allocate();

    private native void allocateArray(long j);

    public native int age_index();

    public native AVFilterLink age_index(int i);

    @Cast({"uint64_t"})
    public native long channel_layout();

    public native AVFilterLink channel_layout(long j);

    public native int channels();

    public native AVFilterLink channels(int i);

    @Cast({"int64_t"})
    public native long current_pts();

    public native AVFilterLink current_pts(long j);

    @Cast({"int64_t"})
    public native long current_pts_us();

    public native AVFilterLink current_pts_us(long j);

    public native AVFilterContext dst();

    public native AVFilterLink dst(AVFilterContext aVFilterContext);

    public native AVFilterLink dstpad(AVFilterPad aVFilterPad);

    public native AVFilterPad dstpad();

    @Cast({"unsigned"})
    public native int flags();

    public native AVFilterLink flags(int i);

    public native int format();

    public native AVFilterLink format(int i);

    @Cast({"int64_t"})
    public native long frame_count_in();

    public native AVFilterLink frame_count_in(long j);

    @Cast({"int64_t"})
    public native long frame_count_out();

    public native AVFilterLink frame_count_out(long j);

    public native AVFilterLink frame_pool(Pointer pointer);

    public native Pointer frame_pool();

    public native AVFilterLink frame_rate(AVRational aVRational);

    @ByRef
    public native AVRational frame_rate();

    public native int frame_wanted_out();

    public native AVFilterLink frame_wanted_out(int i);

    public native AVFilterGraph graph();

    public native AVFilterLink graph(AVFilterGraph aVFilterGraph);

    public native int h();

    public native AVFilterLink h(int i);

    public native AVFilterLink hw_frames_ctx(AVBufferRef aVBufferRef);

    public native AVBufferRef hw_frames_ctx();

    public native AVFilterLink in_channel_layouts(Pointer pointer);

    @Cast({"AVFilterChannelLayouts*"})
    public native Pointer in_channel_layouts();

    public native AVFilterFormats in_formats();

    public native AVFilterLink in_formats(AVFilterFormats aVFilterFormats);

    public native AVFilterFormats in_samplerates();

    public native AVFilterLink in_samplerates(AVFilterFormats aVFilterFormats);

    public native int max_samples();

    public native AVFilterLink max_samples(int i);

    public native int min_samples();

    public native AVFilterLink min_samples(int i);

    public native AVFilterLink out_channel_layouts(Pointer pointer);

    @Cast({"AVFilterChannelLayouts*"})
    public native Pointer out_channel_layouts();

    public native AVFilterFormats out_formats();

    public native AVFilterLink out_formats(AVFilterFormats aVFilterFormats);

    public native AVFilterFormats out_samplerates();

    public native AVFilterLink out_samplerates(AVFilterFormats aVFilterFormats);

    public native AVFilterLink partial_buf(AVFrame aVFrame);

    public native AVFrame partial_buf();

    public native int partial_buf_size();

    public native AVFilterLink partial_buf_size(int i);

    public native int request_samples();

    public native AVFilterLink request_samples(int i);

    @Cast({"char"})
    public native byte reserved(int i);

    public native AVFilterLink reserved(int i, byte b);

    @MemberGetter
    @Cast({"char*"})
    public native BytePointer reserved();

    public native AVFilterLink sample_aspect_ratio(AVRational aVRational);

    @ByRef
    public native AVRational sample_aspect_ratio();

    public native int sample_rate();

    public native AVFilterLink sample_rate(int i);

    public native AVFilterContext src();

    public native AVFilterLink src(AVFilterContext aVFilterContext);

    public native AVFilterLink srcpad(AVFilterPad aVFilterPad);

    public native AVFilterPad srcpad();

    public native AVFilterLink time_base(AVRational aVRational);

    @ByRef
    public native AVRational time_base();

    @Cast({"AVMediaType"})
    public native int type();

    public native AVFilterLink type(int i);

    public native int w();

    public native AVFilterLink w(int i);

    static {
        Loader.load();
    }

    public AVFilterLink() {
        super((Pointer) null);
        allocate();
    }

    public AVFilterLink(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVFilterLink(Pointer pointer) {
        super(pointer);
    }

    public AVFilterLink position(long j) {
        return (AVFilterLink) super.position(j);
    }

    public AVFilterLink getPointer(long j) {
        return new AVFilterLink((Pointer) this).position(this.position + j);
    }
}
