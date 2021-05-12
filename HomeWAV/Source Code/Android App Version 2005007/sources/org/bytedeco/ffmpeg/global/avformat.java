package org.bytedeco.ffmpeg.global;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.bytedeco.ffmpeg.avcodec.AVBitStreamFilterContext;
import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.AVCodecParserContext;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVCodecTag;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVIOContext;
import org.bytedeco.ffmpeg.avformat.AVIODirContext;
import org.bytedeco.ffmpeg.avformat.AVIODirEntry;
import org.bytedeco.ffmpeg.avformat.AVIOInterruptCB;
import org.bytedeco.ffmpeg.avformat.AVInputFormat;
import org.bytedeco.ffmpeg.avformat.AVOpenCallback;
import org.bytedeco.ffmpeg.avformat.AVOutputFormat;
import org.bytedeco.ffmpeg.avformat.AVProbeData;
import org.bytedeco.ffmpeg.avformat.AVProgram;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.avformat.Read_packet_Pointer_ByteBuffer_int;
import org.bytedeco.ffmpeg.avformat.Read_packet_Pointer_BytePointer_int;
import org.bytedeco.ffmpeg.avformat.Read_packet_Pointer_byte___int;
import org.bytedeco.ffmpeg.avformat.Seek_Pointer_long_int;
import org.bytedeco.ffmpeg.avformat.Write_packet_Pointer_ByteBuffer_int;
import org.bytedeco.ffmpeg.avformat.Write_packet_Pointer_BytePointer_int;
import org.bytedeco.ffmpeg.avformat.Write_packet_Pointer_byte___int;
import org.bytedeco.ffmpeg.avformat.av_format_control_message;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.NoException;

public class avformat extends org.bytedeco.ffmpeg.presets.avformat {
    public static final int AVFMTCTX_NOHEADER = 1;
    public static final int AVFMTCTX_UNSEEKABLE = 2;
    public static final int AVFMT_ALLOW_FLUSH = 65536;
    public static final int AVFMT_DURATION_FROM_BITRATE = 2;
    public static final int AVFMT_DURATION_FROM_PTS = 0;
    public static final int AVFMT_DURATION_FROM_STREAM = 1;
    public static final int AVFMT_GENERIC_INDEX = 256;
    public static final int AVFMT_GLOBALHEADER = 64;
    public static final int AVFMT_NEEDNUMBER = 2;
    public static final int AVFMT_NOBINSEARCH = 8192;
    public static final int AVFMT_NODIMENSIONS = 2048;
    public static final int AVFMT_NOFILE = 1;
    public static final int AVFMT_NOGENSEARCH = 16384;
    public static final int AVFMT_NOSTREAMS = 4096;
    public static final int AVFMT_NOTIMESTAMPS = 128;
    public static final int AVFMT_NO_BYTE_SEEK = 32768;
    public static final int AVFMT_SEEK_TO_PTS = 67108864;
    public static final int AVFMT_SHOW_IDS = 8;
    public static final int AVFMT_TBCF_AUTO = -1;
    public static final int AVFMT_TBCF_DECODER = 0;
    public static final int AVFMT_TBCF_DEMUXER = 1;
    public static final int AVFMT_TBCF_R_FRAMERATE = 2;
    public static final int AVFMT_TS_DISCONT = 512;
    public static final int AVFMT_TS_NEGATIVE = 262144;
    public static final int AVFMT_TS_NONSTRICT = 131072;
    public static final int AVFMT_VARIABLE_FPS = 1024;
    public static final int AVIO_DATA_MARKER_BOUNDARY_POINT = 2;
    public static final int AVIO_DATA_MARKER_FLUSH_POINT = 5;
    public static final int AVIO_DATA_MARKER_HEADER = 0;
    public static final int AVIO_DATA_MARKER_SYNC_POINT = 1;
    public static final int AVIO_DATA_MARKER_TRAILER = 4;
    public static final int AVIO_DATA_MARKER_UNKNOWN = 3;
    public static final int AVIO_ENTRY_BLOCK_DEVICE = 1;
    public static final int AVIO_ENTRY_CHARACTER_DEVICE = 2;
    public static final int AVIO_ENTRY_DIRECTORY = 3;
    public static final int AVIO_ENTRY_FILE = 7;
    public static final int AVIO_ENTRY_NAMED_PIPE = 4;
    public static final int AVIO_ENTRY_SERVER = 8;
    public static final int AVIO_ENTRY_SHARE = 9;
    public static final int AVIO_ENTRY_SOCKET = 6;
    public static final int AVIO_ENTRY_SYMBOLIC_LINK = 5;
    public static final int AVIO_ENTRY_UNKNOWN = 0;
    public static final int AVIO_ENTRY_WORKGROUP = 10;
    public static final int AVIO_FLAG_DIRECT = 32768;
    public static final int AVIO_FLAG_NONBLOCK = 8;
    public static final int AVIO_FLAG_READ = 1;
    public static final int AVIO_FLAG_READ_WRITE = 3;
    public static final int AVIO_FLAG_WRITE = 2;
    public static final int AVIO_SEEKABLE_NORMAL = 1;
    public static final int AVIO_SEEKABLE_TIME = 2;
    public static final int AVPROBE_PADDING_SIZE = 32;
    public static final int AVPROBE_SCORE_EXTENSION = 50;
    public static final int AVPROBE_SCORE_MAX = 100;
    public static final int AVPROBE_SCORE_MIME = 75;
    public static final int AVPROBE_SCORE_RETRY = AVPROBE_SCORE_RETRY();
    public static final int AVPROBE_SCORE_STREAM_RETRY = AVPROBE_SCORE_STREAM_RETRY();
    public static final int AVSEEK_FLAG_ANY = 4;
    public static final int AVSEEK_FLAG_BACKWARD = 1;
    public static final int AVSEEK_FLAG_BYTE = 2;
    public static final int AVSEEK_FLAG_FRAME = 8;
    public static final int AVSEEK_FORCE = 131072;
    public static final int AVSEEK_SIZE = 65536;
    public static final int AVSTREAM_INIT_IN_INIT_OUTPUT = 1;
    public static final int AVSTREAM_INIT_IN_WRITE_HEADER = 0;
    public static final int AVSTREAM_PARSE_FULL = 1;
    public static final int AVSTREAM_PARSE_FULL_ONCE = 4;
    public static final int AVSTREAM_PARSE_FULL_RAW = 5;
    public static final int AVSTREAM_PARSE_HEADERS = 2;
    public static final int AVSTREAM_PARSE_NONE = 0;
    public static final int AVSTREAM_PARSE_TIMESTAMPS = 3;
    public static final int AV_DISPOSITION_ATTACHED_PIC = 1024;
    public static final int AV_DISPOSITION_CAPTIONS = 65536;
    public static final int AV_DISPOSITION_CLEAN_EFFECTS = 512;
    public static final int AV_DISPOSITION_COMMENT = 8;
    public static final int AV_DISPOSITION_DEFAULT = 1;
    public static final int AV_DISPOSITION_DEPENDENT = 524288;
    public static final int AV_DISPOSITION_DESCRIPTIONS = 131072;
    public static final int AV_DISPOSITION_DUB = 2;
    public static final int AV_DISPOSITION_FORCED = 64;
    public static final int AV_DISPOSITION_HEARING_IMPAIRED = 128;
    public static final int AV_DISPOSITION_KARAOKE = 32;
    public static final int AV_DISPOSITION_LYRICS = 16;
    public static final int AV_DISPOSITION_METADATA = 262144;
    public static final int AV_DISPOSITION_ORIGINAL = 4;
    public static final int AV_DISPOSITION_STILL_IMAGE = 1048576;
    public static final int AV_DISPOSITION_TIMED_THUMBNAILS = 2048;
    public static final int AV_DISPOSITION_VISUAL_IMPAIRED = 256;
    public static final int AV_FRAME_FILENAME_FLAGS_MULTIPLE = 1;
    public static final int AV_PROGRAM_RUNNING = 1;
    public static final int AV_PTS_WRAP_ADD_OFFSET = 1;
    public static final int AV_PTS_WRAP_IGNORE = 0;
    public static final int AV_PTS_WRAP_SUB_OFFSET = -1;

    @MemberGetter
    public static native int AVPROBE_SCORE_RETRY();

    @MemberGetter
    public static native int AVPROBE_SCORE_STREAM_RETRY();

    @NoException
    public static native int av_add_index_entry(AVStream aVStream, @Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2, int i, int i2, int i3);

    @NoException
    public static native int av_append_packet(AVIOContext aVIOContext, AVPacket aVPacket, int i);

    @NoException
    @Deprecated
    public static native int av_apply_bitstream_filters(AVCodecContext aVCodecContext, AVPacket aVPacket, AVBitStreamFilterContext aVBitStreamFilterContext);

    @NoException
    @Cast({"AVCodecID"})
    public static native int av_codec_get_id(@ByPtrPtr @Const AVCodecTag aVCodecTag, @Cast({"unsigned int"}) int i);

    @NoException
    @Cast({"AVCodecID"})
    public static native int av_codec_get_id(@Cast({"const AVCodecTag*const*"}) PointerPointer pointerPointer, @Cast({"unsigned int"}) int i);

    @NoException
    @Cast({"unsigned int"})
    public static native int av_codec_get_tag(@ByPtrPtr @Const AVCodecTag aVCodecTag, @Cast({"AVCodecID"}) int i);

    @NoException
    @Cast({"unsigned int"})
    public static native int av_codec_get_tag(@Cast({"const AVCodecTag*const*"}) PointerPointer pointerPointer, @Cast({"AVCodecID"}) int i);

    @NoException
    public static native int av_codec_get_tag2(@ByPtrPtr @Const AVCodecTag aVCodecTag, @Cast({"AVCodecID"}) int i, @Cast({"unsigned int*"}) IntBuffer intBuffer);

    @NoException
    public static native int av_codec_get_tag2(@ByPtrPtr @Const AVCodecTag aVCodecTag, @Cast({"AVCodecID"}) int i, @Cast({"unsigned int*"}) IntPointer intPointer);

    @NoException
    public static native int av_codec_get_tag2(@ByPtrPtr @Const AVCodecTag aVCodecTag, @Cast({"AVCodecID"}) int i, @Cast({"unsigned int*"}) int[] iArr);

    @NoException
    public static native int av_codec_get_tag2(@Cast({"const AVCodecTag*const*"}) PointerPointer pointerPointer, @Cast({"AVCodecID"}) int i, @Cast({"unsigned int*"}) IntPointer intPointer);

    @NoException
    @Const
    public static native AVInputFormat av_demuxer_iterate(@ByPtrPtr @Cast({"void**"}) Pointer pointer);

    @NoException
    @Const
    public static native AVInputFormat av_demuxer_iterate(@Cast({"void**"}) PointerPointer pointerPointer);

    @NoException
    @Deprecated
    public static native int av_demuxer_open(AVFormatContext aVFormatContext);

    @NoException
    public static native void av_dump_format(AVFormatContext aVFormatContext, int i, String str, int i2);

    @NoException
    public static native void av_dump_format(AVFormatContext aVFormatContext, int i, @Cast({"const char*"}) BytePointer bytePointer, int i2);

    @NoException
    public static native int av_filename_number_test(String str);

    @NoException
    public static native int av_filename_number_test(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_find_best_stream(AVFormatContext aVFormatContext, @Cast({"AVMediaType"}) int i, int i2, int i3, @ByPtrPtr AVCodec aVCodec, int i4);

    @NoException
    public static native int av_find_best_stream(AVFormatContext aVFormatContext, @Cast({"AVMediaType"}) int i, int i2, int i3, @Cast({"AVCodec**"}) PointerPointer pointerPointer, int i4);

    @NoException
    public static native int av_find_default_stream_index(AVFormatContext aVFormatContext);

    @NoException
    public static native AVInputFormat av_find_input_format(String str);

    @NoException
    public static native AVInputFormat av_find_input_format(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native AVProgram av_find_program_from_stream(AVFormatContext aVFormatContext, AVProgram aVProgram, int i);

    @NoException
    @Cast({"AVDurationEstimationMethod"})
    public static native int av_fmt_ctx_get_duration_estimation_method(@Const AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native AVCodec av_format_get_audio_codec(@Const AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native av_format_control_message av_format_get_control_message_cb(@Const AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native AVCodec av_format_get_data_codec(@Const AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native int av_format_get_metadata_header_padding(@Const AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native Pointer av_format_get_opaque(@Const AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native AVOpenCallback av_format_get_open_cb(@Const AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native int av_format_get_probe_score(@Const AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native AVCodec av_format_get_subtitle_codec(@Const AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native AVCodec av_format_get_video_codec(@Const AVFormatContext aVFormatContext);

    @NoException
    public static native void av_format_inject_global_side_data(AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native void av_format_set_audio_codec(AVFormatContext aVFormatContext, AVCodec aVCodec);

    @NoException
    @Deprecated
    public static native void av_format_set_control_message_cb(AVFormatContext aVFormatContext, av_format_control_message av_format_control_message);

    @NoException
    @Deprecated
    public static native void av_format_set_data_codec(AVFormatContext aVFormatContext, AVCodec aVCodec);

    @NoException
    @Deprecated
    public static native void av_format_set_metadata_header_padding(AVFormatContext aVFormatContext, int i);

    @NoException
    @Deprecated
    public static native void av_format_set_opaque(AVFormatContext aVFormatContext, Pointer pointer);

    @NoException
    @Deprecated
    public static native void av_format_set_open_cb(AVFormatContext aVFormatContext, AVOpenCallback aVOpenCallback);

    @NoException
    @Deprecated
    public static native void av_format_set_subtitle_codec(AVFormatContext aVFormatContext, AVCodec aVCodec);

    @NoException
    @Deprecated
    public static native void av_format_set_video_codec(AVFormatContext aVFormatContext, AVCodec aVCodec);

    @NoException
    public static native int av_get_frame_filename(@Cast({"char*"}) ByteBuffer byteBuffer, int i, String str, int i2);

    @NoException
    public static native int av_get_frame_filename(@Cast({"char*"}) ByteBuffer byteBuffer, int i, @Cast({"const char*"}) BytePointer bytePointer, int i2);

    @NoException
    public static native int av_get_frame_filename(@Cast({"char*"}) BytePointer bytePointer, int i, String str, int i2);

    @NoException
    public static native int av_get_frame_filename(@Cast({"char*"}) BytePointer bytePointer, int i, @Cast({"const char*"}) BytePointer bytePointer2, int i2);

    @NoException
    public static native int av_get_frame_filename(@Cast({"char*"}) byte[] bArr, int i, String str, int i2);

    @NoException
    public static native int av_get_frame_filename(@Cast({"char*"}) byte[] bArr, int i, @Cast({"const char*"}) BytePointer bytePointer, int i2);

    @NoException
    public static native int av_get_frame_filename2(@Cast({"char*"}) ByteBuffer byteBuffer, int i, String str, int i2, int i3);

    @NoException
    public static native int av_get_frame_filename2(@Cast({"char*"}) ByteBuffer byteBuffer, int i, @Cast({"const char*"}) BytePointer bytePointer, int i2, int i3);

    @NoException
    public static native int av_get_frame_filename2(@Cast({"char*"}) BytePointer bytePointer, int i, String str, int i2, int i3);

    @NoException
    public static native int av_get_frame_filename2(@Cast({"char*"}) BytePointer bytePointer, int i, @Cast({"const char*"}) BytePointer bytePointer2, int i2, int i3);

    @NoException
    public static native int av_get_frame_filename2(@Cast({"char*"}) byte[] bArr, int i, String str, int i2, int i3);

    @NoException
    public static native int av_get_frame_filename2(@Cast({"char*"}) byte[] bArr, int i, @Cast({"const char*"}) BytePointer bytePointer, int i2, int i3);

    @NoException
    public static native int av_get_output_timestamp(AVFormatContext aVFormatContext, int i, @Cast({"int64_t*"}) LongBuffer longBuffer, @Cast({"int64_t*"}) LongBuffer longBuffer2);

    @NoException
    public static native int av_get_output_timestamp(AVFormatContext aVFormatContext, int i, @Cast({"int64_t*"}) LongPointer longPointer, @Cast({"int64_t*"}) LongPointer longPointer2);

    @NoException
    public static native int av_get_output_timestamp(AVFormatContext aVFormatContext, int i, @Cast({"int64_t*"}) long[] jArr, @Cast({"int64_t*"}) long[] jArr2);

    @NoException
    public static native int av_get_packet(AVIOContext aVIOContext, AVPacket aVPacket, int i);

    @NoException
    @Cast({"AVCodecID"})
    public static native int av_guess_codec(AVOutputFormat aVOutputFormat, String str, String str2, String str3, @Cast({"AVMediaType"}) int i);

    @NoException
    @Cast({"AVCodecID"})
    public static native int av_guess_codec(AVOutputFormat aVOutputFormat, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, @Cast({"AVMediaType"}) int i);

    @NoException
    public static native AVOutputFormat av_guess_format(String str, String str2, String str3);

    @NoException
    public static native AVOutputFormat av_guess_format(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3);

    @NoException
    @ByVal
    public static native AVRational av_guess_frame_rate(AVFormatContext aVFormatContext, AVStream aVStream, AVFrame aVFrame);

    @NoException
    @ByVal
    public static native AVRational av_guess_sample_aspect_ratio(AVFormatContext aVFormatContext, AVStream aVStream, AVFrame aVFrame);

    @NoException
    public static native void av_hex_dump(@Cast({"FILE*"}) Pointer pointer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void av_hex_dump(@Cast({"FILE*"}) Pointer pointer, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void av_hex_dump(@Cast({"FILE*"}) Pointer pointer, @Cast({"const uint8_t*"}) byte[] bArr, int i);

    @NoException
    public static native void av_hex_dump_log(Pointer pointer, int i, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i2);

    @NoException
    public static native void av_hex_dump_log(Pointer pointer, int i, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i2);

    @NoException
    public static native void av_hex_dump_log(Pointer pointer, int i, @Cast({"const uint8_t*"}) byte[] bArr, int i2);

    @NoException
    @Deprecated
    public static native AVInputFormat av_iformat_next(@Const AVInputFormat aVInputFormat);

    @NoException
    public static native int av_index_search_timestamp(AVStream aVStream, @Cast({"int64_t"}) long j, int i);

    @NoException
    public static native int av_interleaved_write_frame(AVFormatContext aVFormatContext, AVPacket aVPacket);

    @NoException
    public static native int av_interleaved_write_uncoded_frame(AVFormatContext aVFormatContext, int i, AVFrame aVFrame);

    @NoException
    public static native int av_match_ext(String str, String str2);

    @NoException
    public static native int av_match_ext(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    @Const
    public static native AVOutputFormat av_muxer_iterate(@ByPtrPtr @Cast({"void**"}) Pointer pointer);

    @NoException
    @Const
    public static native AVOutputFormat av_muxer_iterate(@Cast({"void**"}) PointerPointer pointerPointer);

    @NoException
    public static native AVProgram av_new_program(AVFormatContext aVFormatContext, int i);

    @NoException
    @Deprecated
    public static native AVOutputFormat av_oformat_next(@Const AVOutputFormat aVOutputFormat);

    @NoException
    public static native void av_pkt_dump2(@Cast({"FILE*"}) Pointer pointer, @Const AVPacket aVPacket, int i, @Const AVStream aVStream);

    @NoException
    public static native void av_pkt_dump_log2(Pointer pointer, int i, @Const AVPacket aVPacket, int i2, @Const AVStream aVStream);

    @NoException
    public static native int av_probe_input_buffer(AVIOContext aVIOContext, @ByPtrPtr AVInputFormat aVInputFormat, String str, Pointer pointer, @Cast({"unsigned int"}) int i, @Cast({"unsigned int"}) int i2);

    @NoException
    public static native int av_probe_input_buffer(AVIOContext aVIOContext, @ByPtrPtr AVInputFormat aVInputFormat, @Cast({"const char*"}) BytePointer bytePointer, Pointer pointer, @Cast({"unsigned int"}) int i, @Cast({"unsigned int"}) int i2);

    @NoException
    public static native int av_probe_input_buffer(AVIOContext aVIOContext, @Cast({"AVInputFormat**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, Pointer pointer, @Cast({"unsigned int"}) int i, @Cast({"unsigned int"}) int i2);

    @NoException
    public static native int av_probe_input_buffer2(AVIOContext aVIOContext, @ByPtrPtr AVInputFormat aVInputFormat, String str, Pointer pointer, @Cast({"unsigned int"}) int i, @Cast({"unsigned int"}) int i2);

    @NoException
    public static native int av_probe_input_buffer2(AVIOContext aVIOContext, @ByPtrPtr AVInputFormat aVInputFormat, @Cast({"const char*"}) BytePointer bytePointer, Pointer pointer, @Cast({"unsigned int"}) int i, @Cast({"unsigned int"}) int i2);

    @NoException
    public static native int av_probe_input_buffer2(AVIOContext aVIOContext, @Cast({"AVInputFormat**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, Pointer pointer, @Cast({"unsigned int"}) int i, @Cast({"unsigned int"}) int i2);

    @NoException
    public static native AVInputFormat av_probe_input_format(AVProbeData aVProbeData, int i);

    @NoException
    public static native AVInputFormat av_probe_input_format2(AVProbeData aVProbeData, int i, IntBuffer intBuffer);

    @NoException
    public static native AVInputFormat av_probe_input_format2(AVProbeData aVProbeData, int i, IntPointer intPointer);

    @NoException
    public static native AVInputFormat av_probe_input_format2(AVProbeData aVProbeData, int i, int[] iArr);

    @NoException
    public static native AVInputFormat av_probe_input_format3(AVProbeData aVProbeData, int i, IntBuffer intBuffer);

    @NoException
    public static native AVInputFormat av_probe_input_format3(AVProbeData aVProbeData, int i, IntPointer intPointer);

    @NoException
    public static native AVInputFormat av_probe_input_format3(AVProbeData aVProbeData, int i, int[] iArr);

    @NoException
    public static native void av_program_add_stream_index(AVFormatContext aVFormatContext, int i, @Cast({"unsigned int"}) int i2);

    @NoException
    public static native int av_read_frame(AVFormatContext aVFormatContext, AVPacket aVPacket);

    @NoException
    public static native int av_read_pause(AVFormatContext aVFormatContext);

    @NoException
    public static native int av_read_play(AVFormatContext aVFormatContext);

    @NoException
    @Deprecated
    public static native void av_register_all();

    @NoException
    @Deprecated
    public static native void av_register_input_format(AVInputFormat aVInputFormat);

    @NoException
    @Deprecated
    public static native void av_register_output_format(AVOutputFormat aVOutputFormat);

    @NoException
    public static native int av_sdp_create(@ByPtrPtr AVFormatContext aVFormatContext, int i, @Cast({"char*"}) ByteBuffer byteBuffer, int i2);

    @NoException
    public static native int av_sdp_create(@ByPtrPtr AVFormatContext aVFormatContext, int i, @Cast({"char*"}) BytePointer bytePointer, int i2);

    @NoException
    public static native int av_sdp_create(@ByPtrPtr AVFormatContext aVFormatContext, int i, @Cast({"char*"}) byte[] bArr, int i2);

    @NoException
    public static native int av_sdp_create(@Cast({"AVFormatContext**"}) PointerPointer pointerPointer, int i, @Cast({"char*"}) BytePointer bytePointer, int i2);

    @NoException
    public static native int av_seek_frame(AVFormatContext aVFormatContext, int i, @Cast({"int64_t"}) long j, int i2);

    @NoException
    public static native int av_stream_add_side_data(AVStream aVStream, @Cast({"AVPacketSideDataType"}) int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_stream_add_side_data(AVStream aVStream, @Cast({"AVPacketSideDataType"}) int i, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_stream_add_side_data(AVStream aVStream, @Cast({"AVPacketSideDataType"}) int i, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    @ByVal
    public static native AVRational av_stream_get_codec_timebase(@Const AVStream aVStream);

    @NoException
    @Cast({"int64_t"})
    public static native long av_stream_get_end_pts(@Const AVStream aVStream);

    @NoException
    public static native AVCodecParserContext av_stream_get_parser(@Const AVStream aVStream);

    @NoException
    @Deprecated
    @ByVal
    public static native AVRational av_stream_get_r_frame_rate(@Const AVStream aVStream);

    @NoException
    @Deprecated
    @Cast({"char*"})
    public static native BytePointer av_stream_get_recommended_encoder_configuration(@Const AVStream aVStream);

    @NoException
    @Cast({"uint8_t*"})
    public static native ByteBuffer av_stream_get_side_data(@Const AVStream aVStream, @Cast({"AVPacketSideDataType"}) int i, IntBuffer intBuffer);

    @NoException
    @Cast({"uint8_t*"})
    public static native BytePointer av_stream_get_side_data(@Const AVStream aVStream, @Cast({"AVPacketSideDataType"}) int i, IntPointer intPointer);

    @NoException
    @Cast({"uint8_t*"})
    public static native byte[] av_stream_get_side_data(@Const AVStream aVStream, @Cast({"AVPacketSideDataType"}) int i, int[] iArr);

    @NoException
    @Cast({"uint8_t*"})
    public static native BytePointer av_stream_new_side_data(AVStream aVStream, @Cast({"AVPacketSideDataType"}) int i, int i2);

    @NoException
    @Deprecated
    public static native void av_stream_set_r_frame_rate(AVStream aVStream, @ByVal AVRational aVRational);

    @NoException
    @Deprecated
    public static native void av_stream_set_recommended_encoder_configuration(AVStream aVStream, @Cast({"char*"}) ByteBuffer byteBuffer);

    @NoException
    @Deprecated
    public static native void av_stream_set_recommended_encoder_configuration(AVStream aVStream, @Cast({"char*"}) BytePointer bytePointer);

    @NoException
    @Deprecated
    public static native void av_stream_set_recommended_encoder_configuration(AVStream aVStream, @Cast({"char*"}) byte[] bArr);

    @NoException
    public static native void av_url_split(@Cast({"char*"}) ByteBuffer byteBuffer, int i, @Cast({"char*"}) ByteBuffer byteBuffer2, int i2, @Cast({"char*"}) ByteBuffer byteBuffer3, int i3, IntBuffer intBuffer, @Cast({"char*"}) ByteBuffer byteBuffer4, int i4, String str);

    @NoException
    public static native void av_url_split(@Cast({"char*"}) ByteBuffer byteBuffer, int i, @Cast({"char*"}) ByteBuffer byteBuffer2, int i2, @Cast({"char*"}) ByteBuffer byteBuffer3, int i3, IntBuffer intBuffer, @Cast({"char*"}) ByteBuffer byteBuffer4, int i4, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native void av_url_split(@Cast({"char*"}) BytePointer bytePointer, int i, @Cast({"char*"}) BytePointer bytePointer2, int i2, @Cast({"char*"}) BytePointer bytePointer3, int i3, IntPointer intPointer, @Cast({"char*"}) BytePointer bytePointer4, int i4, String str);

    @NoException
    public static native void av_url_split(@Cast({"char*"}) BytePointer bytePointer, int i, @Cast({"char*"}) BytePointer bytePointer2, int i2, @Cast({"char*"}) BytePointer bytePointer3, int i3, IntPointer intPointer, @Cast({"char*"}) BytePointer bytePointer4, int i4, @Cast({"const char*"}) BytePointer bytePointer5);

    @NoException
    public static native void av_url_split(@Cast({"char*"}) byte[] bArr, int i, @Cast({"char*"}) byte[] bArr2, int i2, @Cast({"char*"}) byte[] bArr3, int i3, int[] iArr, @Cast({"char*"}) byte[] bArr4, int i4, String str);

    @NoException
    public static native void av_url_split(@Cast({"char*"}) byte[] bArr, int i, @Cast({"char*"}) byte[] bArr2, int i2, @Cast({"char*"}) byte[] bArr3, int i3, int[] iArr, @Cast({"char*"}) byte[] bArr4, int i4, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_write_frame(AVFormatContext aVFormatContext, AVPacket aVPacket);

    @NoException
    public static native int av_write_trailer(AVFormatContext aVFormatContext);

    @NoException
    public static native int av_write_uncoded_frame(AVFormatContext aVFormatContext, int i, AVFrame aVFrame);

    @NoException
    public static native int av_write_uncoded_frame_query(AVFormatContext aVFormatContext, int i);

    @NoException
    public static native AVFormatContext avformat_alloc_context();

    @NoException
    public static native int avformat_alloc_output_context2(@ByPtrPtr AVFormatContext aVFormatContext, AVOutputFormat aVOutputFormat, String str, String str2);

    @NoException
    public static native int avformat_alloc_output_context2(@ByPtrPtr AVFormatContext aVFormatContext, AVOutputFormat aVOutputFormat, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    public static native int avformat_alloc_output_context2(@Cast({"AVFormatContext**"}) PointerPointer pointerPointer, AVOutputFormat aVOutputFormat, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    public static native void avformat_close_input(@ByPtrPtr AVFormatContext aVFormatContext);

    @NoException
    public static native void avformat_close_input(@Cast({"AVFormatContext**"}) PointerPointer pointerPointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avformat_configuration();

    @NoException
    public static native int avformat_find_stream_info(AVFormatContext aVFormatContext, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avformat_find_stream_info(AVFormatContext aVFormatContext, @Cast({"AVDictionary**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avformat_flush(AVFormatContext aVFormatContext);

    @NoException
    public static native void avformat_free_context(AVFormatContext aVFormatContext);

    @NoException
    @Const
    public static native AVClass avformat_get_class();

    @NoException
    @Const
    public static native AVCodecTag avformat_get_mov_audio_tags();

    @NoException
    @Const
    public static native AVCodecTag avformat_get_mov_video_tags();

    @NoException
    @Const
    public static native AVCodecTag avformat_get_riff_audio_tags();

    @NoException
    @Const
    public static native AVCodecTag avformat_get_riff_video_tags();

    @NoException
    public static native int avformat_init_output(AVFormatContext aVFormatContext, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avformat_init_output(AVFormatContext aVFormatContext, @Cast({"AVDictionary**"}) PointerPointer pointerPointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avformat_license();

    @NoException
    public static native int avformat_match_stream_specifier(AVFormatContext aVFormatContext, AVStream aVStream, String str);

    @NoException
    public static native int avformat_match_stream_specifier(AVFormatContext aVFormatContext, AVStream aVStream, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int avformat_network_deinit();

    @NoException
    public static native int avformat_network_init();

    @NoException
    public static native AVStream avformat_new_stream(AVFormatContext aVFormatContext, @Const AVCodec aVCodec);

    @NoException
    public static native int avformat_open_input(@ByPtrPtr AVFormatContext aVFormatContext, String str, AVInputFormat aVInputFormat, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avformat_open_input(@ByPtrPtr AVFormatContext aVFormatContext, @Cast({"const char*"}) BytePointer bytePointer, AVInputFormat aVInputFormat, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avformat_open_input(@Cast({"AVFormatContext**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, AVInputFormat aVInputFormat, @Cast({"AVDictionary**"}) PointerPointer pointerPointer2);

    @NoException
    public static native int avformat_query_codec(@Const AVOutputFormat aVOutputFormat, @Cast({"AVCodecID"}) int i, int i2);

    @NoException
    public static native int avformat_queue_attached_pictures(AVFormatContext aVFormatContext);

    @NoException
    public static native int avformat_seek_file(AVFormatContext aVFormatContext, int i, @Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2, @Cast({"int64_t"}) long j3, int i2);

    @NoException
    public static native int avformat_transfer_internal_stream_timing_info(@Const AVOutputFormat aVOutputFormat, AVStream aVStream, @Const AVStream aVStream2, @Cast({"AVTimebaseSource"}) int i);

    @NoException
    @Cast({"unsigned"})
    public static native int avformat_version();

    @NoException
    public static native int avformat_write_header(AVFormatContext aVFormatContext, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avformat_write_header(AVFormatContext aVFormatContext, @Cast({"AVDictionary**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avio_accept(AVIOContext aVIOContext, @ByPtrPtr AVIOContext aVIOContext2);

    @NoException
    public static native int avio_accept(AVIOContext aVIOContext, @Cast({"AVIOContext**"}) PointerPointer pointerPointer);

    @NoException
    public static native AVIOContext avio_alloc_context(@Cast({"unsigned char*"}) ByteBuffer byteBuffer, int i, int i2, Pointer pointer, Read_packet_Pointer_ByteBuffer_int read_packet_Pointer_ByteBuffer_int, Write_packet_Pointer_ByteBuffer_int write_packet_Pointer_ByteBuffer_int, Seek_Pointer_long_int seek_Pointer_long_int);

    @NoException
    public static native AVIOContext avio_alloc_context(@Cast({"unsigned char*"}) BytePointer bytePointer, int i, int i2, Pointer pointer, Read_packet_Pointer_BytePointer_int read_packet_Pointer_BytePointer_int, Write_packet_Pointer_BytePointer_int write_packet_Pointer_BytePointer_int, Seek_Pointer_long_int seek_Pointer_long_int);

    @NoException
    public static native AVIOContext avio_alloc_context(@Cast({"unsigned char*"}) byte[] bArr, int i, int i2, Pointer pointer, Read_packet_Pointer_byte___int read_packet_Pointer_byte___int, Write_packet_Pointer_byte___int write_packet_Pointer_byte___int, Seek_Pointer_long_int seek_Pointer_long_int);

    @NoException
    public static native int avio_check(String str, int i);

    @NoException
    public static native int avio_check(@Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int avio_close(AVIOContext aVIOContext);

    @NoException
    public static native int avio_close_dir(@ByPtrPtr AVIODirContext aVIODirContext);

    @NoException
    public static native int avio_close_dir(@Cast({"AVIODirContext**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avio_close_dyn_buf(AVIOContext aVIOContext, @ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int avio_close_dyn_buf(AVIOContext aVIOContext, @ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer);

    @NoException
    public static native int avio_close_dyn_buf(AVIOContext aVIOContext, @Cast({"uint8_t**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avio_close_dyn_buf(AVIOContext aVIOContext, @ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr);

    @NoException
    public static native int avio_closep(@ByPtrPtr AVIOContext aVIOContext);

    @NoException
    public static native int avio_closep(@Cast({"AVIOContext**"}) PointerPointer pointerPointer);

    @NoException
    public static native void avio_context_free(@ByPtrPtr AVIOContext aVIOContext);

    @NoException
    public static native void avio_context_free(@Cast({"AVIOContext**"}) PointerPointer pointerPointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avio_enum_protocols(@ByPtrPtr @Cast({"void**"}) Pointer pointer, int i);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avio_enum_protocols(@Cast({"void**"}) PointerPointer pointerPointer, int i);

    @NoException
    public static native int avio_feof(AVIOContext aVIOContext);

    @NoException
    public static native String avio_find_protocol_name(String str);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avio_find_protocol_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native void avio_flush(AVIOContext aVIOContext);

    @NoException
    public static native void avio_free_directory_entry(@ByPtrPtr AVIODirEntry aVIODirEntry);

    @NoException
    public static native void avio_free_directory_entry(@Cast({"AVIODirEntry**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avio_get_dyn_buf(AVIOContext aVIOContext, @ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int avio_get_dyn_buf(AVIOContext aVIOContext, @ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer);

    @NoException
    public static native int avio_get_dyn_buf(AVIOContext aVIOContext, @Cast({"uint8_t**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avio_get_dyn_buf(AVIOContext aVIOContext, @ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr);

    @NoException
    public static native int avio_get_str(AVIOContext aVIOContext, int i, @Cast({"char*"}) ByteBuffer byteBuffer, int i2);

    @NoException
    public static native int avio_get_str(AVIOContext aVIOContext, int i, @Cast({"char*"}) BytePointer bytePointer, int i2);

    @NoException
    public static native int avio_get_str(AVIOContext aVIOContext, int i, @Cast({"char*"}) byte[] bArr, int i2);

    @NoException
    public static native int avio_get_str16be(AVIOContext aVIOContext, int i, @Cast({"char*"}) ByteBuffer byteBuffer, int i2);

    @NoException
    public static native int avio_get_str16be(AVIOContext aVIOContext, int i, @Cast({"char*"}) BytePointer bytePointer, int i2);

    @NoException
    public static native int avio_get_str16be(AVIOContext aVIOContext, int i, @Cast({"char*"}) byte[] bArr, int i2);

    @NoException
    public static native int avio_get_str16le(AVIOContext aVIOContext, int i, @Cast({"char*"}) ByteBuffer byteBuffer, int i2);

    @NoException
    public static native int avio_get_str16le(AVIOContext aVIOContext, int i, @Cast({"char*"}) BytePointer bytePointer, int i2);

    @NoException
    public static native int avio_get_str16le(AVIOContext aVIOContext, int i, @Cast({"char*"}) byte[] bArr, int i2);

    @NoException
    public static native int avio_handshake(AVIOContext aVIOContext);

    @NoException
    public static native int avio_open(@ByPtrPtr AVIOContext aVIOContext, String str, int i);

    @NoException
    public static native int avio_open(@ByPtrPtr AVIOContext aVIOContext, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int avio_open(@Cast({"AVIOContext**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int avio_open2(@ByPtrPtr AVIOContext aVIOContext, String str, int i, @Const AVIOInterruptCB aVIOInterruptCB, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avio_open2(@ByPtrPtr AVIOContext aVIOContext, @Cast({"const char*"}) BytePointer bytePointer, int i, @Const AVIOInterruptCB aVIOInterruptCB, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avio_open2(@Cast({"AVIOContext**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Const AVIOInterruptCB aVIOInterruptCB, @Cast({"AVDictionary**"}) PointerPointer pointerPointer2);

    @NoException
    public static native int avio_open_dir(@ByPtrPtr AVIODirContext aVIODirContext, String str, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avio_open_dir(@ByPtrPtr AVIODirContext aVIODirContext, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avio_open_dir(@Cast({"AVIODirContext**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"AVDictionary**"}) PointerPointer pointerPointer2);

    @NoException
    public static native int avio_open_dyn_buf(@ByPtrPtr AVIOContext aVIOContext);

    @NoException
    public static native int avio_open_dyn_buf(@Cast({"AVIOContext**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avio_pause(AVIOContext aVIOContext, int i);

    @NoException
    public static native void avio_print_string_array(AVIOContext aVIOContext, @ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer);

    @NoException
    public static native void avio_print_string_array(AVIOContext aVIOContext, @ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer);

    @NoException
    public static native void avio_print_string_array(AVIOContext aVIOContext, @Cast({"const char**"}) PointerPointer pointerPointer);

    @NoException
    public static native void avio_print_string_array(AVIOContext aVIOContext, @ByPtrPtr @Cast({"const char**"}) byte[] bArr);

    @NoException
    public static native int avio_printf(AVIOContext aVIOContext, String str);

    @NoException
    public static native int avio_printf(AVIOContext aVIOContext, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Const
    public static native AVClass avio_protocol_get_class(String str);

    @NoException
    @Const
    public static native AVClass avio_protocol_get_class(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int avio_put_str(AVIOContext aVIOContext, String str);

    @NoException
    public static native int avio_put_str(AVIOContext aVIOContext, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int avio_put_str16be(AVIOContext aVIOContext, String str);

    @NoException
    public static native int avio_put_str16be(AVIOContext aVIOContext, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int avio_put_str16le(AVIOContext aVIOContext, String str);

    @NoException
    public static native int avio_put_str16le(AVIOContext aVIOContext, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int avio_r8(AVIOContext aVIOContext);

    @NoException
    @Cast({"unsigned int"})
    public static native int avio_rb16(AVIOContext aVIOContext);

    @NoException
    @Cast({"unsigned int"})
    public static native int avio_rb24(AVIOContext aVIOContext);

    @NoException
    @Cast({"unsigned int"})
    public static native int avio_rb32(AVIOContext aVIOContext);

    @NoException
    @Cast({"uint64_t"})
    public static native long avio_rb64(AVIOContext aVIOContext);

    @NoException
    public static native int avio_read(AVIOContext aVIOContext, @Cast({"unsigned char*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native int avio_read(AVIOContext aVIOContext, @Cast({"unsigned char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int avio_read(AVIOContext aVIOContext, @Cast({"unsigned char*"}) byte[] bArr, int i);

    @NoException
    public static native int avio_read_dir(AVIODirContext aVIODirContext, @ByPtrPtr AVIODirEntry aVIODirEntry);

    @NoException
    public static native int avio_read_dir(AVIODirContext aVIODirContext, @Cast({"AVIODirEntry**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avio_read_partial(AVIOContext aVIOContext, @Cast({"unsigned char*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native int avio_read_partial(AVIOContext aVIOContext, @Cast({"unsigned char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int avio_read_partial(AVIOContext aVIOContext, @Cast({"unsigned char*"}) byte[] bArr, int i);

    @NoException
    public static native int avio_read_to_bprint(AVIOContext aVIOContext, @Cast({"AVBPrint*"}) Pointer pointer, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"unsigned int"})
    public static native int avio_rl16(AVIOContext aVIOContext);

    @NoException
    @Cast({"unsigned int"})
    public static native int avio_rl24(AVIOContext aVIOContext);

    @NoException
    @Cast({"unsigned int"})
    public static native int avio_rl32(AVIOContext aVIOContext);

    @NoException
    @Cast({"uint64_t"})
    public static native long avio_rl64(AVIOContext aVIOContext);

    @NoException
    @Cast({"int64_t"})
    public static native long avio_seek(AVIOContext aVIOContext, @Cast({"int64_t"}) long j, int i);

    @NoException
    @Cast({"int64_t"})
    public static native long avio_seek_time(AVIOContext aVIOContext, int i, @Cast({"int64_t"}) long j, int i2);

    @NoException
    @Cast({"int64_t"})
    public static native long avio_size(AVIOContext aVIOContext);

    @NoException
    @Cast({"int64_t"})
    public static native long avio_skip(AVIOContext aVIOContext, @Cast({"int64_t"}) long j);

    @NoException
    @Cast({"int64_t"})
    public static native long avio_tell(AVIOContext aVIOContext);

    @NoException
    public static native void avio_w8(AVIOContext aVIOContext, int i);

    @NoException
    public static native void avio_wb16(AVIOContext aVIOContext, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void avio_wb24(AVIOContext aVIOContext, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void avio_wb32(AVIOContext aVIOContext, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void avio_wb64(AVIOContext aVIOContext, @Cast({"uint64_t"}) long j);

    @NoException
    public static native void avio_wl16(AVIOContext aVIOContext, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void avio_wl24(AVIOContext aVIOContext, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void avio_wl32(AVIOContext aVIOContext, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void avio_wl64(AVIOContext aVIOContext, @Cast({"uint64_t"}) long j);

    @NoException
    public static native void avio_write(AVIOContext aVIOContext, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void avio_write(AVIOContext aVIOContext, @Cast({"const unsigned char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void avio_write(AVIOContext aVIOContext, @Cast({"const unsigned char*"}) byte[] bArr, int i);

    @NoException
    public static native void avio_write_marker(AVIOContext aVIOContext, @Cast({"int64_t"}) long j, @Cast({"AVIODataMarkerType"}) int i);

    @NoException
    public static native int avpriv_io_delete(String str);

    @NoException
    public static native int avpriv_io_delete(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int avpriv_io_move(String str, String str2);

    @NoException
    public static native int avpriv_io_move(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    static {
        Loader.load();
    }
}
