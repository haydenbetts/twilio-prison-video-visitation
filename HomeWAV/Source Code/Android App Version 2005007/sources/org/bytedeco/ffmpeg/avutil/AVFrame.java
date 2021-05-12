package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVFrame extends Pointer {
    public static final int AV_FRAME_FLAG_CORRUPT = 1;
    public static final int AV_FRAME_FLAG_DISCARD = 4;
    public static final int AV_NUM_DATA_POINTERS = 8;
    public static final int FF_DECODE_ERROR_CONCEALMENT_ACTIVE = 4;
    public static final int FF_DECODE_ERROR_DECODE_SLICES = 8;
    public static final int FF_DECODE_ERROR_INVALID_BITSTREAM = 1;
    public static final int FF_DECODE_ERROR_MISSING_REFERENCE = 2;

    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"int64_t"})
    public native long best_effort_timestamp();

    public native AVFrame best_effort_timestamp(long j);

    public native AVBufferRef buf(int i);

    public native AVFrame buf(int i, AVBufferRef aVBufferRef);

    @MemberGetter
    @Cast({"AVBufferRef**"})
    public native PointerPointer buf();

    @Cast({"uint64_t"})
    public native long channel_layout();

    public native AVFrame channel_layout(long j);

    public native int channels();

    public native AVFrame channels(int i);

    @Cast({"AVChromaLocation"})
    public native int chroma_location();

    public native AVFrame chroma_location(int i);

    public native int coded_picture_number();

    public native AVFrame coded_picture_number(int i);

    @Cast({"AVColorPrimaries"})
    public native int color_primaries();

    public native AVFrame color_primaries(int i);

    @Cast({"AVColorRange"})
    public native int color_range();

    public native AVFrame color_range(int i);

    @Cast({"AVColorTransferCharacteristic"})
    public native int color_trc();

    public native AVFrame color_trc(int i);

    @Cast({"AVColorSpace"})
    public native int colorspace();

    public native AVFrame colorspace(int i);

    @Cast({"size_t"})
    public native long crop_bottom();

    public native AVFrame crop_bottom(long j);

    @Cast({"size_t"})
    public native long crop_left();

    public native AVFrame crop_left(long j);

    @Cast({"size_t"})
    public native long crop_right();

    public native AVFrame crop_right(long j);

    @Cast({"size_t"})
    public native long crop_top();

    public native AVFrame crop_top(long j);

    public native AVFrame data(int i, BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer data(int i);

    @MemberGetter
    @Cast({"uint8_t**"})
    public native PointerPointer data();

    public native int decode_error_flags();

    public native AVFrame decode_error_flags(int i);

    public native int display_picture_number();

    public native AVFrame display_picture_number(int i);

    @Deprecated
    @Cast({"uint64_t"})
    public native long error(int i);

    public native AVFrame error(int i, long j);

    @MemberGetter
    @Deprecated
    @Cast({"uint64_t*"})
    public native LongPointer error();

    public native AVBufferRef extended_buf(int i);

    public native AVFrame extended_buf(int i, AVBufferRef aVBufferRef);

    public native AVFrame extended_buf(PointerPointer pointerPointer);

    @Cast({"AVBufferRef**"})
    public native PointerPointer extended_buf();

    public native AVFrame extended_data(int i, BytePointer bytePointer);

    public native AVFrame extended_data(PointerPointer pointerPointer);

    @Cast({"uint8_t*"})
    public native BytePointer extended_data(int i);

    @Cast({"uint8_t**"})
    public native PointerPointer extended_data();

    public native int flags();

    public native AVFrame flags(int i);

    public native int format();

    public native AVFrame format(int i);

    public native int height();

    public native AVFrame height(int i);

    public native AVBufferRef hw_frames_ctx();

    public native AVFrame hw_frames_ctx(AVBufferRef aVBufferRef);

    public native int interlaced_frame();

    public native AVFrame interlaced_frame(int i);

    public native int key_frame();

    public native AVFrame key_frame(int i);

    public native int linesize(int i);

    public native AVFrame linesize(int i, int i2);

    @MemberGetter
    public native IntPointer linesize();

    public native AVDictionary metadata();

    public native AVFrame metadata(AVDictionary aVDictionary);

    public native int nb_extended_buf();

    public native AVFrame nb_extended_buf(int i);

    public native int nb_samples();

    public native AVFrame nb_samples(int i);

    public native int nb_side_data();

    public native AVFrame nb_side_data(int i);

    public native AVFrame opaque(Pointer pointer);

    public native Pointer opaque();

    public native AVBufferRef opaque_ref();

    public native AVFrame opaque_ref(AVBufferRef aVBufferRef);

    public native int palette_has_changed();

    public native AVFrame palette_has_changed(int i);

    @Cast({"AVPictureType"})
    public native int pict_type();

    public native AVFrame pict_type(int i);

    @Cast({"int64_t"})
    public native long pkt_dts();

    public native AVFrame pkt_dts(long j);

    @Cast({"int64_t"})
    public native long pkt_duration();

    public native AVFrame pkt_duration(long j);

    @Cast({"int64_t"})
    public native long pkt_pos();

    public native AVFrame pkt_pos(long j);

    @Deprecated
    @Cast({"int64_t"})
    public native long pkt_pts();

    public native AVFrame pkt_pts(long j);

    public native int pkt_size();

    public native AVFrame pkt_size(int i);

    public native AVBufferRef private_ref();

    public native AVFrame private_ref(AVBufferRef aVBufferRef);

    @Cast({"int64_t"})
    public native long pts();

    public native AVFrame pts(long j);

    @Deprecated
    public native AVBufferRef qp_table_buf();

    public native AVFrame qp_table_buf(AVBufferRef aVBufferRef);

    public native AVFrame qscale_table(BytePointer bytePointer);

    @Deprecated
    public native BytePointer qscale_table();

    @Deprecated
    public native int qscale_type();

    public native AVFrame qscale_type(int i);

    @Deprecated
    public native int qstride();

    public native AVFrame qstride(int i);

    public native int quality();

    public native AVFrame quality(int i);

    @Cast({"int64_t"})
    public native long reordered_opaque();

    public native AVFrame reordered_opaque(long j);

    public native int repeat_pict();

    public native AVFrame repeat_pict(int i);

    public native AVFrame sample_aspect_ratio(AVRational aVRational);

    @ByRef
    public native AVRational sample_aspect_ratio();

    public native int sample_rate();

    public native AVFrame sample_rate(int i);

    public native AVFrame side_data(int i, AVFrameSideData aVFrameSideData);

    public native AVFrame side_data(PointerPointer pointerPointer);

    public native AVFrameSideData side_data(int i);

    @Cast({"AVFrameSideData**"})
    public native PointerPointer side_data();

    public native int top_field_first();

    public native AVFrame top_field_first(int i);

    public native int width();

    public native AVFrame width(int i);

    static {
        Loader.load();
    }

    public AVFrame() {
        super((Pointer) null);
        allocate();
    }

    public AVFrame(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVFrame(Pointer pointer) {
        super(pointer);
    }

    public AVFrame position(long j) {
        return (AVFrame) super.position(j);
    }

    public AVFrame getPointer(long j) {
        return new AVFrame((Pointer) this).position(this.position + j);
    }
}
