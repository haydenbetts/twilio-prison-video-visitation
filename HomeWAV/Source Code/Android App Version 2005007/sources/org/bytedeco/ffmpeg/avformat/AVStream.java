package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avcodec.AVCodecParserContext;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avcodec.AVPacketSideData;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVStream extends Pointer {
    public static final int AVSTREAM_EVENT_FLAG_METADATA_UPDATED = 1;
    public static final int MAX_REORDER_DELAY = 16;
    public static final int MAX_STD_TIMEBASES = 399;

    private native void allocate();

    private native void allocateArray(long j);

    @ByRef
    public native AVPacket attached_pic();

    public native AVStream attached_pic(AVPacket aVPacket);

    public native AVStream avg_frame_rate(AVRational aVRational);

    @ByRef
    public native AVRational avg_frame_rate();

    @Deprecated
    public native AVCodecContext codec();

    public native AVStream codec(AVCodecContext aVCodecContext);

    public native int codec_info_nb_frames();

    public native AVStream codec_info_nb_frames(int i);

    public native AVCodecParameters codecpar();

    public native AVStream codecpar(AVCodecParameters aVCodecParameters);

    @Cast({"int64_t"})
    public native long cur_dts();

    public native AVStream cur_dts(long j);

    @Cast({"AVDiscard"})
    public native int discard();

    public native AVStream discard(int i);

    public native AVStream display_aspect_ratio(AVRational aVRational);

    @ByRef
    public native AVRational display_aspect_ratio();

    public native int disposition();

    public native AVStream disposition(int i);

    @Cast({"uint8_t"})
    public native byte dts_misordered();

    public native AVStream dts_misordered(byte b);

    @Cast({"uint8_t"})
    public native byte dts_ordered();

    public native AVStream dts_ordered(byte b);

    @Cast({"int64_t"})
    public native long duration();

    public native AVStream duration(long j);

    public native int event_flags();

    public native AVStream event_flags(int i);

    @Cast({"int64_t"})
    public native long first_discard_sample();

    public native AVStream first_discard_sample(long j);

    @Cast({"int64_t"})
    public native long first_dts();

    public native AVStream first_dts(long j);

    public native int id();

    public native AVStream id(int i);

    public native int index();

    public native AVStream index(int i);

    public native AVIndexEntry index_entries();

    public native AVStream index_entries(AVIndexEntry aVIndexEntry);

    @Cast({"unsigned int"})
    public native int index_entries_allocated_size();

    public native AVStream index_entries_allocated_size(int i);

    @Cast({"int64_t"})
    @Name({"info", ".codec_info_duration"})
    public native long info_codec_info_duration(int i);

    public native AVStream info_codec_info_duration(int i, long j);

    @Cast({"int64_t"})
    @Name({"info", ".codec_info_duration_fields"})
    public native long info_codec_info_duration_fields(int i);

    public native AVStream info_codec_info_duration_fields(int i, long j);

    @Name({"info", ".duration_count"})
    public native int info_duration_count(int i);

    public native AVStream info_duration_count(int i, int i2);

    @MemberGetter
    @Cast({"double*"})
    @Name({"info", ".duration_error"})
    public native DoublePointer info_duration_error(int i);

    @Cast({"int64_t"})
    @Name({"info", ".duration_gcd"})
    public native long info_duration_gcd(int i);

    public native AVStream info_duration_gcd(int i, long j);

    @Name({"info", ".found_decoder"})
    public native int info_found_decoder(int i);

    public native AVStream info_found_decoder(int i, int i2);

    @Cast({"int64_t"})
    @Name({"info", ".fps_first_dts"})
    public native long info_fps_first_dts(int i);

    public native AVStream info_fps_first_dts(int i, long j);

    @Name({"info", ".fps_first_dts_idx"})
    public native int info_fps_first_dts_idx(int i);

    public native AVStream info_fps_first_dts_idx(int i, int i2);

    @Cast({"int64_t"})
    @Name({"info", ".fps_last_dts"})
    public native long info_fps_last_dts(int i);

    public native AVStream info_fps_last_dts(int i, long j);

    @Name({"info", ".fps_last_dts_idx"})
    public native int info_fps_last_dts_idx(int i);

    public native AVStream info_fps_last_dts_idx(int i, int i2);

    @Name({"info", ".frame_delay_evidence"})
    public native int info_frame_delay_evidence(int i);

    public native AVStream info_frame_delay_evidence(int i, int i2);

    @Cast({"int64_t"})
    @Name({"info", ".last_dts"})
    public native long info_last_dts(int i);

    public native AVStream info_last_dts(int i, long j);

    @Cast({"int64_t"})
    @Name({"info", ".last_duration"})
    public native long info_last_duration(int i);

    public native AVStream info_last_duration(int i, long j);

    @Cast({"int64_t"})
    @Name({"info", ".rfps_duration_sum"})
    public native long info_rfps_duration_sum(int i);

    public native AVStream info_rfps_duration_sum(int i, long j);

    public native int inject_global_side_data();

    public native AVStream inject_global_side_data(int i);

    @Cast({"int64_t"})
    public native long interleaver_chunk_duration();

    public native AVStream interleaver_chunk_duration(long j);

    @Cast({"int64_t"})
    public native long interleaver_chunk_size();

    public native AVStream interleaver_chunk_size(long j);

    public native AVStream internal(AVStreamInternal aVStreamInternal);

    public native AVStreamInternal internal();

    public native int last_IP_duration();

    public native AVStream last_IP_duration(int i);

    @Cast({"int64_t"})
    public native long last_IP_pts();

    public native AVStream last_IP_pts(long j);

    @Cast({"int64_t"})
    public native long last_discard_sample();

    public native AVStream last_discard_sample(long j);

    @Cast({"int64_t"})
    public native long last_dts_for_order_check();

    public native AVStream last_dts_for_order_check(long j);

    public native AVPacketList last_in_packet_buffer();

    public native AVStream last_in_packet_buffer(AVPacketList aVPacketList);

    public native AVStream metadata(AVDictionary aVDictionary);

    public native AVDictionary metadata();

    @Cast({"int64_t"})
    public native long mux_ts_offset();

    public native AVStream mux_ts_offset(long j);

    public native int nb_decoded_frames();

    public native AVStream nb_decoded_frames(int i);

    @Cast({"int64_t"})
    public native long nb_frames();

    public native AVStream nb_frames(long j);

    public native int nb_index_entries();

    public native AVStream nb_index_entries(int i);

    public native int nb_side_data();

    public native AVStream nb_side_data(int i);

    @Cast({"AVStreamParseType"})
    public native int need_parsing();

    public native AVStream need_parsing(int i);

    public native AVCodecParserContext parser();

    public native AVStream parser(AVCodecParserContext aVCodecParserContext);

    public native int pmt_stream_idx();

    public native AVStream pmt_stream_idx(int i);

    public native int pmt_version();

    public native AVStream pmt_version(int i);

    public native AVStream priv_data(Pointer pointer);

    public native Pointer priv_data();

    @ByRef
    public native AVProbeData probe_data();

    public native AVStream probe_data(AVProbeData aVProbeData);

    public native int probe_packets();

    public native AVStream probe_packets(int i);

    public native int program_num();

    public native AVStream program_num(int i);

    @Cast({"int64_t"})
    public native long pts_buffer(int i);

    public native AVStream pts_buffer(int i, long j);

    @MemberGetter
    @Cast({"int64_t*"})
    public native LongPointer pts_buffer();

    @Cast({"int64_t"})
    public native long pts_reorder_error(int i);

    public native AVStream pts_reorder_error(int i, long j);

    @MemberGetter
    @Cast({"int64_t*"})
    public native LongPointer pts_reorder_error();

    @Cast({"uint8_t"})
    public native byte pts_reorder_error_count(int i);

    public native AVStream pts_reorder_error_count(int i, byte b);

    @MemberGetter
    @Cast({"uint8_t*"})
    public native BytePointer pts_reorder_error_count();

    public native int pts_wrap_behavior();

    public native AVStream pts_wrap_behavior(int i);

    public native int pts_wrap_bits();

    public native AVStream pts_wrap_bits(int i);

    @Cast({"int64_t"})
    public native long pts_wrap_reference();

    public native AVStream pts_wrap_reference(long j);

    public native AVStream r_frame_rate(AVRational aVRational);

    @ByRef
    public native AVRational r_frame_rate();

    public native AVStream recommended_encoder_configuration(BytePointer bytePointer);

    @Deprecated
    @Cast({"char*"})
    public native BytePointer recommended_encoder_configuration();

    public native int request_probe();

    public native AVStream request_probe(int i);

    public native AVStream sample_aspect_ratio(AVRational aVRational);

    @ByRef
    public native AVRational sample_aspect_ratio();

    public native AVPacketSideData side_data();

    public native AVStream side_data(AVPacketSideData aVPacketSideData);

    public native int skip_samples();

    public native AVStream skip_samples(int i);

    public native int skip_to_keyframe();

    public native AVStream skip_to_keyframe(int i);

    @Cast({"int64_t"})
    public native long start_skip_samples();

    public native AVStream start_skip_samples(long j);

    @Cast({"int64_t"})
    public native long start_time();

    public native AVStream start_time(long j);

    public native int stream_identifier();

    public native AVStream stream_identifier(int i);

    public native AVStream time_base(AVRational aVRational);

    @ByRef
    public native AVRational time_base();

    public native int update_initial_durations_done();

    public native AVStream update_initial_durations_done(int i);

    static {
        Loader.load();
    }

    public AVStream() {
        super((Pointer) null);
        allocate();
    }

    public AVStream(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVStream(Pointer pointer) {
        super(pointer);
    }

    public AVStream position(long j) {
        return (AVStream) super.position(j);
    }

    public AVStream getPointer(long j) {
        return new AVStream((Pointer) this).position(this.position + j);
    }
}
