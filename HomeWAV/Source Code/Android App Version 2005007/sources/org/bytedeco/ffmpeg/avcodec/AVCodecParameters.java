package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVCodecParameters extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"int64_t"})
    public native long bit_rate();

    public native AVCodecParameters bit_rate(long j);

    public native int bits_per_coded_sample();

    public native AVCodecParameters bits_per_coded_sample(int i);

    public native int bits_per_raw_sample();

    public native AVCodecParameters bits_per_raw_sample(int i);

    public native int block_align();

    public native AVCodecParameters block_align(int i);

    @Cast({"uint64_t"})
    public native long channel_layout();

    public native AVCodecParameters channel_layout(long j);

    public native int channels();

    public native AVCodecParameters channels(int i);

    @Cast({"AVChromaLocation"})
    public native int chroma_location();

    public native AVCodecParameters chroma_location(int i);

    @Cast({"AVCodecID"})
    public native int codec_id();

    public native AVCodecParameters codec_id(int i);

    @Cast({"uint32_t"})
    public native int codec_tag();

    public native AVCodecParameters codec_tag(int i);

    @Cast({"AVMediaType"})
    public native int codec_type();

    public native AVCodecParameters codec_type(int i);

    @Cast({"AVColorPrimaries"})
    public native int color_primaries();

    public native AVCodecParameters color_primaries(int i);

    @Cast({"AVColorRange"})
    public native int color_range();

    public native AVCodecParameters color_range(int i);

    @Cast({"AVColorSpace"})
    public native int color_space();

    public native AVCodecParameters color_space(int i);

    @Cast({"AVColorTransferCharacteristic"})
    public native int color_trc();

    public native AVCodecParameters color_trc(int i);

    public native AVCodecParameters extradata(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer extradata();

    public native int extradata_size();

    public native AVCodecParameters extradata_size(int i);

    @Cast({"AVFieldOrder"})
    public native int field_order();

    public native AVCodecParameters field_order(int i);

    public native int format();

    public native AVCodecParameters format(int i);

    public native int frame_size();

    public native AVCodecParameters frame_size(int i);

    public native int height();

    public native AVCodecParameters height(int i);

    public native int initial_padding();

    public native AVCodecParameters initial_padding(int i);

    public native int level();

    public native AVCodecParameters level(int i);

    public native int profile();

    public native AVCodecParameters profile(int i);

    public native AVCodecParameters sample_aspect_ratio(AVRational aVRational);

    @ByRef
    public native AVRational sample_aspect_ratio();

    public native int sample_rate();

    public native AVCodecParameters sample_rate(int i);

    public native int seek_preroll();

    public native AVCodecParameters seek_preroll(int i);

    public native int trailing_padding();

    public native AVCodecParameters trailing_padding(int i);

    public native int video_delay();

    public native AVCodecParameters video_delay(int i);

    public native int width();

    public native AVCodecParameters width(int i);

    static {
        Loader.load();
    }

    public AVCodecParameters() {
        super((Pointer) null);
        allocate();
    }

    public AVCodecParameters(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVCodecParameters(Pointer pointer) {
        super(pointer);
    }

    public AVCodecParameters position(long j) {
        return (AVCodecParameters) super.position(j);
    }

    public AVCodecParameters getPointer(long j) {
        return new AVCodecParameters((Pointer) this).position(this.position + j);
    }
}
