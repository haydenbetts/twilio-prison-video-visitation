package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVCodecParserContext extends Pointer {
    public static final int AV_PARSER_PTS_NB = 4;
    public static final int PARSER_FLAG_COMPLETE_FRAMES = 1;
    public static final int PARSER_FLAG_FETCHED_OFFSET = 4;
    public static final int PARSER_FLAG_ONCE = 2;
    public static final int PARSER_FLAG_USE_CODEC_TS = 4096;

    private native void allocate();

    private native void allocateArray(long j);

    public native int coded_height();

    public native AVCodecParserContext coded_height(int i);

    public native int coded_width();

    public native AVCodecParserContext coded_width(int i);

    @Deprecated
    @Cast({"int64_t"})
    public native long convergence_duration();

    public native AVCodecParserContext convergence_duration(long j);

    @Cast({"int64_t"})
    public native long cur_frame_dts(int i);

    public native AVCodecParserContext cur_frame_dts(int i, long j);

    @MemberGetter
    @Cast({"int64_t*"})
    public native LongPointer cur_frame_dts();

    @Cast({"int64_t"})
    public native long cur_frame_end(int i);

    public native AVCodecParserContext cur_frame_end(int i, long j);

    @MemberGetter
    @Cast({"int64_t*"})
    public native LongPointer cur_frame_end();

    @Cast({"int64_t"})
    public native long cur_frame_offset(int i);

    public native AVCodecParserContext cur_frame_offset(int i, long j);

    @MemberGetter
    @Cast({"int64_t*"})
    public native LongPointer cur_frame_offset();

    @Cast({"int64_t"})
    public native long cur_frame_pos(int i);

    public native AVCodecParserContext cur_frame_pos(int i, long j);

    @MemberGetter
    @Cast({"int64_t*"})
    public native LongPointer cur_frame_pos();

    @Cast({"int64_t"})
    public native long cur_frame_pts(int i);

    public native AVCodecParserContext cur_frame_pts(int i, long j);

    @MemberGetter
    @Cast({"int64_t*"})
    public native LongPointer cur_frame_pts();

    public native int cur_frame_start_index();

    public native AVCodecParserContext cur_frame_start_index(int i);

    @Cast({"int64_t"})
    public native long cur_offset();

    public native AVCodecParserContext cur_offset(long j);

    @Cast({"int64_t"})
    public native long dts();

    public native AVCodecParserContext dts(long j);

    public native int dts_ref_dts_delta();

    public native AVCodecParserContext dts_ref_dts_delta(int i);

    public native int dts_sync_point();

    public native AVCodecParserContext dts_sync_point(int i);

    public native int duration();

    public native AVCodecParserContext duration(int i);

    public native int fetch_timestamp();

    public native AVCodecParserContext fetch_timestamp(int i);

    @Cast({"AVFieldOrder"})
    public native int field_order();

    public native AVCodecParserContext field_order(int i);

    public native int flags();

    public native AVCodecParserContext flags(int i);

    public native int format();

    public native AVCodecParserContext format(int i);

    @Cast({"int64_t"})
    public native long frame_offset();

    public native AVCodecParserContext frame_offset(long j);

    public native int height();

    public native AVCodecParserContext height(int i);

    public native int key_frame();

    public native AVCodecParserContext key_frame(int i);

    @Cast({"int64_t"})
    public native long last_dts();

    public native AVCodecParserContext last_dts(long j);

    @Cast({"int64_t"})
    public native long last_pos();

    public native AVCodecParserContext last_pos(long j);

    @Cast({"int64_t"})
    public native long last_pts();

    public native AVCodecParserContext last_pts(long j);

    @Cast({"int64_t"})
    public native long next_frame_offset();

    public native AVCodecParserContext next_frame_offset(long j);

    @Cast({"int64_t"})
    public native long offset();

    public native AVCodecParserContext offset(long j);

    public native int output_picture_number();

    public native AVCodecParserContext output_picture_number(int i);

    public native AVCodecParser parser();

    public native AVCodecParserContext parser(AVCodecParser aVCodecParser);

    public native int pict_type();

    public native AVCodecParserContext pict_type(int i);

    @Cast({"AVPictureStructure"})
    public native int picture_structure();

    public native AVCodecParserContext picture_structure(int i);

    @Cast({"int64_t"})
    public native long pos();

    public native AVCodecParserContext pos(long j);

    public native AVCodecParserContext priv_data(Pointer pointer);

    public native Pointer priv_data();

    @Cast({"int64_t"})
    public native long pts();

    public native AVCodecParserContext pts(long j);

    public native int pts_dts_delta();

    public native AVCodecParserContext pts_dts_delta(int i);

    public native int repeat_pict();

    public native AVCodecParserContext repeat_pict(int i);

    public native int width();

    public native AVCodecParserContext width(int i);

    static {
        Loader.load();
    }

    public AVCodecParserContext() {
        super((Pointer) null);
        allocate();
    }

    public AVCodecParserContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVCodecParserContext(Pointer pointer) {
        super(pointer);
    }

    public AVCodecParserContext position(long j) {
        return (AVCodecParserContext) super.position(j);
    }

    public AVCodecParserContext getPointer(long j) {
        return new AVCodecParserContext((Pointer) this).position(this.position + j);
    }
}
