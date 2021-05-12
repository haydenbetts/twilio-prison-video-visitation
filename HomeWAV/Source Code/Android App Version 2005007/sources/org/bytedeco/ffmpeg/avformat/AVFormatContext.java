package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVFormatContext extends Pointer {
    public static final int AVFMT_AVOID_NEG_TS_AUTO = -1;
    public static final int AVFMT_AVOID_NEG_TS_MAKE_NON_NEGATIVE = 1;
    public static final int AVFMT_AVOID_NEG_TS_MAKE_ZERO = 2;
    public static final int AVFMT_EVENT_FLAG_METADATA_UPDATED = 1;
    public static final int AVFMT_FLAG_AUTO_BSF = 2097152;
    public static final int AVFMT_FLAG_BITEXACT = 1024;
    public static final int AVFMT_FLAG_CUSTOM_IO = 128;
    public static final int AVFMT_FLAG_DISCARD_CORRUPT = 256;
    public static final int AVFMT_FLAG_FAST_SEEK = 524288;
    public static final int AVFMT_FLAG_FLUSH_PACKETS = 512;
    public static final int AVFMT_FLAG_GENPTS = 1;
    public static final int AVFMT_FLAG_IGNDTS = 8;
    public static final int AVFMT_FLAG_IGNIDX = 2;
    public static final int AVFMT_FLAG_KEEP_SIDE_DATA = 262144;
    public static final int AVFMT_FLAG_MP4A_LATM = 32768;
    public static final int AVFMT_FLAG_NOBUFFER = 64;
    public static final int AVFMT_FLAG_NOFILLIN = 16;
    public static final int AVFMT_FLAG_NONBLOCK = 4;
    public static final int AVFMT_FLAG_NOPARSE = 32;
    public static final int AVFMT_FLAG_PRIV_OPT = 131072;
    public static final int AVFMT_FLAG_SHORTEST = 1048576;
    public static final int AVFMT_FLAG_SORT_DTS = 65536;
    public static final int FF_FDEBUG_TS = 1;

    private native void allocate();

    private native void allocateArray(long j);

    public native AVCodec audio_codec();

    public native AVFormatContext audio_codec(AVCodec aVCodec);

    @Cast({"AVCodecID"})
    public native int audio_codec_id();

    public native AVFormatContext audio_codec_id(int i);

    public native int audio_preload();

    public native AVFormatContext audio_preload(int i);

    public native AVFormatContext av_class(AVClass aVClass);

    @Const
    public native AVClass av_class();

    public native int avio_flags();

    public native AVFormatContext avio_flags(int i);

    public native int avoid_negative_ts();

    public native AVFormatContext avoid_negative_ts(int i);

    @Cast({"int64_t"})
    public native long bit_rate();

    public native AVFormatContext bit_rate(long j);

    public native AVChapter chapters(int i);

    public native AVFormatContext chapters(int i, AVChapter aVChapter);

    public native AVFormatContext chapters(PointerPointer pointerPointer);

    @Cast({"AVChapter**"})
    public native PointerPointer chapters();

    public native AVFormatContext codec_whitelist(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer codec_whitelist();

    public native AVFormatContext control_message_cb(av_format_control_message av_format_control_message);

    public native av_format_control_message control_message_cb();

    @Cast({"unsigned int"})
    public native int correct_ts_overflow();

    public native AVFormatContext correct_ts_overflow(int i);

    public native int ctx_flags();

    public native AVFormatContext ctx_flags(int i);

    public native AVCodec data_codec();

    public native AVFormatContext data_codec(AVCodec aVCodec);

    @Cast({"AVCodecID"})
    public native int data_codec_id();

    public native AVFormatContext data_codec_id(int i);

    public native int debug();

    public native AVFormatContext debug(int i);

    public native AVFormatContext dump_separator(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer dump_separator();

    @Cast({"int64_t"})
    public native long duration();

    public native AVFormatContext duration(long j);

    @Cast({"AVDurationEstimationMethod"})
    public native int duration_estimation_method();

    public native AVFormatContext duration_estimation_method(int i);

    public native int error_recognition();

    public native AVFormatContext error_recognition(int i);

    public native int event_flags();

    public native AVFormatContext event_flags(int i);

    @Deprecated
    @Cast({"char"})
    public native byte filename(int i);

    public native AVFormatContext filename(int i, byte b);

    @MemberGetter
    @Deprecated
    @Cast({"char*"})
    public native BytePointer filename();

    public native int flags();

    public native AVFormatContext flags(int i);

    public native int flush_packets();

    public native AVFormatContext flush_packets(int i);

    public native int format_probesize();

    public native AVFormatContext format_probesize(int i);

    public native AVFormatContext format_whitelist(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer format_whitelist();

    public native int fps_probe_size();

    public native AVFormatContext fps_probe_size(int i);

    public native AVFormatContext iformat(AVInputFormat aVInputFormat);

    public native AVInputFormat iformat();

    public native AVFormatContext internal(AVFormatInternal aVFormatInternal);

    public native AVFormatInternal internal();

    public native AVFormatContext interrupt_callback(AVIOInterruptCB aVIOInterruptCB);

    @ByRef
    public native AVIOInterruptCB interrupt_callback();

    public native Io_close_AVFormatContext_AVIOContext io_close();

    public native AVFormatContext io_close(Io_close_AVFormatContext_AVIOContext io_close_AVFormatContext_AVIOContext);

    public native Io_open_AVFormatContext_PointerPointer_BytePointer_int_PointerPointer io_open();

    public native AVFormatContext io_open(Io_open_AVFormatContext_PointerPointer_BytePointer_int_PointerPointer io_open_AVFormatContext_PointerPointer_BytePointer_int_PointerPointer);

    public native int io_repositioned();

    public native AVFormatContext io_repositioned(int i);

    public native AVFormatContext key(BytePointer bytePointer);

    @Cast({"const uint8_t*"})
    public native BytePointer key();

    public native int keylen();

    public native AVFormatContext keylen(int i);

    @Cast({"int64_t"})
    public native long max_analyze_duration();

    public native AVFormatContext max_analyze_duration(long j);

    public native int max_chunk_duration();

    public native AVFormatContext max_chunk_duration(int i);

    public native int max_chunk_size();

    public native AVFormatContext max_chunk_size(int i);

    public native int max_delay();

    public native AVFormatContext max_delay(int i);

    @Cast({"unsigned int"})
    public native int max_index_size();

    public native AVFormatContext max_index_size(int i);

    @Cast({"int64_t"})
    public native long max_interleave_delta();

    public native AVFormatContext max_interleave_delta(long j);

    @Cast({"unsigned int"})
    public native int max_picture_buffer();

    public native AVFormatContext max_picture_buffer(int i);

    public native int max_probe_packets();

    public native AVFormatContext max_probe_packets(int i);

    public native int max_streams();

    public native AVFormatContext max_streams(int i);

    public native int max_ts_probe();

    public native AVFormatContext max_ts_probe(int i);

    public native AVFormatContext metadata(AVDictionary aVDictionary);

    public native AVDictionary metadata();

    public native int metadata_header_padding();

    public native AVFormatContext metadata_header_padding(int i);

    @Cast({"unsigned int"})
    public native int nb_chapters();

    public native AVFormatContext nb_chapters(int i);

    @Cast({"unsigned int"})
    public native int nb_programs();

    public native AVFormatContext nb_programs(int i);

    @Cast({"unsigned int"})
    public native int nb_streams();

    public native AVFormatContext nb_streams(int i);

    public native AVFormatContext oformat(AVOutputFormat aVOutputFormat);

    public native AVOutputFormat oformat();

    public native AVFormatContext opaque(Pointer pointer);

    public native Pointer opaque();

    public native Open_cb_AVFormatContext_PointerPointer_BytePointer_int_AVIOInterruptCB_PointerPointer open_cb();

    public native AVFormatContext open_cb(Open_cb_AVFormatContext_PointerPointer_BytePointer_int_AVIOInterruptCB_PointerPointer open_cb_AVFormatContext_PointerPointer_BytePointer_int_AVIOInterruptCB_PointerPointer);

    @Cast({"int64_t"})
    public native long output_ts_offset();

    public native AVFormatContext output_ts_offset(long j);

    @Cast({"unsigned int"})
    public native int packet_size();

    public native AVFormatContext packet_size(int i);

    public native AVFormatContext pb(AVIOContext aVIOContext);

    public native AVIOContext pb();

    public native AVFormatContext priv_data(Pointer pointer);

    public native Pointer priv_data();

    public native int probe_score();

    public native AVFormatContext probe_score(int i);

    @Cast({"int64_t"})
    public native long probesize();

    public native AVFormatContext probesize(long j);

    public native AVFormatContext programs(int i, AVProgram aVProgram);

    public native AVFormatContext programs(PointerPointer pointerPointer);

    public native AVProgram programs(int i);

    @Cast({"AVProgram**"})
    public native PointerPointer programs();

    public native AVFormatContext protocol_blacklist(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer protocol_blacklist();

    public native AVFormatContext protocol_whitelist(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer protocol_whitelist();

    public native int seek2any();

    public native AVFormatContext seek2any(int i);

    public native int skip_estimate_duration_from_pts();

    public native AVFormatContext skip_estimate_duration_from_pts(int i);

    @Cast({"int64_t"})
    public native long skip_initial_bytes();

    public native AVFormatContext skip_initial_bytes(long j);

    @Cast({"int64_t"})
    public native long start_time();

    public native AVFormatContext start_time(long j);

    @Cast({"int64_t"})
    public native long start_time_realtime();

    public native AVFormatContext start_time_realtime(long j);

    public native AVFormatContext streams(int i, AVStream aVStream);

    public native AVFormatContext streams(PointerPointer pointerPointer);

    public native AVStream streams(int i);

    @Cast({"AVStream**"})
    public native PointerPointer streams();

    public native int strict_std_compliance();

    public native AVFormatContext strict_std_compliance(int i);

    public native AVCodec subtitle_codec();

    public native AVFormatContext subtitle_codec(AVCodec aVCodec);

    @Cast({"AVCodecID"})
    public native int subtitle_codec_id();

    public native AVFormatContext subtitle_codec_id(int i);

    public native int ts_id();

    public native AVFormatContext ts_id(int i);

    public native AVFormatContext url(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer url();

    public native int use_wallclock_as_timestamps();

    public native AVFormatContext use_wallclock_as_timestamps(int i);

    public native AVCodec video_codec();

    public native AVFormatContext video_codec(AVCodec aVCodec);

    @Cast({"AVCodecID"})
    public native int video_codec_id();

    public native AVFormatContext video_codec_id(int i);

    static {
        Loader.load();
    }

    public AVFormatContext() {
        super((Pointer) null);
        allocate();
    }

    public AVFormatContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVFormatContext(Pointer pointer) {
        super(pointer);
    }

    public AVFormatContext position(long j) {
        return (AVFormatContext) super.position(j);
    }

    public AVFormatContext getPointer(long j) {
        return new AVFormatContext((Pointer) this).position(this.position + j);
    }

    public static class Open_cb_AVFormatContext_PointerPointer_BytePointer_int_AVIOInterruptCB_PointerPointer extends FunctionPointer {
        private native void allocate();

        @Deprecated
        public native int call(AVFormatContext aVFormatContext, @Cast({"AVIOContext**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Const AVIOInterruptCB aVIOInterruptCB, @Cast({"AVDictionary**"}) PointerPointer pointerPointer2);

        static {
            Loader.load();
        }

        public Open_cb_AVFormatContext_PointerPointer_BytePointer_int_AVIOInterruptCB_PointerPointer(Pointer pointer) {
            super(pointer);
        }

        protected Open_cb_AVFormatContext_PointerPointer_BytePointer_int_AVIOInterruptCB_PointerPointer() {
            allocate();
        }
    }

    public static class Io_open_AVFormatContext_PointerPointer_BytePointer_int_PointerPointer extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, @Cast({"AVIOContext**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"AVDictionary**"}) PointerPointer pointerPointer2);

        static {
            Loader.load();
        }

        public Io_open_AVFormatContext_PointerPointer_BytePointer_int_PointerPointer(Pointer pointer) {
            super(pointer);
        }

        protected Io_open_AVFormatContext_PointerPointer_BytePointer_int_PointerPointer() {
            allocate();
        }
    }

    public static class Io_close_AVFormatContext_AVIOContext extends FunctionPointer {
        private native void allocate();

        public native void call(AVFormatContext aVFormatContext, AVIOContext aVIOContext);

        static {
            Loader.load();
        }

        public Io_close_AVFormatContext_AVIOContext(Pointer pointer) {
            super(pointer);
        }

        protected Io_close_AVFormatContext_AVIOContext() {
            allocate();
        }
    }
}
