package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVCodecContext extends Pointer {
    public static final int AV_EF_AGGRESSIVE = 262144;
    public static final int AV_EF_BITSTREAM = 2;
    public static final int AV_EF_BUFFER = 4;
    public static final int AV_EF_CAREFUL = 65536;
    public static final int AV_EF_COMPLIANT = 131072;
    public static final int AV_EF_CRCCHECK = 1;
    public static final int AV_EF_EXPLODE = 8;
    public static final int AV_EF_IGNORE_ERR = 32768;
    public static final int FF_BUG_AMV = 32;
    public static final int FF_BUG_AUTODETECT = 1;
    public static final int FF_BUG_DC_CLIP = 4096;
    public static final int FF_BUG_DIRECT_BLOCKSIZE = 512;
    public static final int FF_BUG_EDGE = 1024;
    public static final int FF_BUG_HPEL_CHROMA = 2048;
    public static final int FF_BUG_IEDGE = 32768;
    public static final int FF_BUG_MS = 8192;
    public static final int FF_BUG_NO_PADDING = 16;
    public static final int FF_BUG_QPEL_CHROMA = 64;
    public static final int FF_BUG_QPEL_CHROMA2 = 256;
    public static final int FF_BUG_STD_QPEL = 128;
    public static final int FF_BUG_TRUNCATED = 16384;
    public static final int FF_BUG_UMP4 = 8;
    public static final int FF_BUG_XVID_ILACE = 4;
    public static final int FF_CMP_BIT = 5;
    public static final int FF_CMP_CHROMA = 256;
    public static final int FF_CMP_DCT = 3;
    public static final int FF_CMP_DCT264 = 14;
    public static final int FF_CMP_DCTMAX = 13;
    public static final int FF_CMP_MEDIAN_SAD = 15;
    public static final int FF_CMP_NSSE = 10;
    public static final int FF_CMP_PSNR = 4;
    public static final int FF_CMP_RD = 6;
    public static final int FF_CMP_SAD = 0;
    public static final int FF_CMP_SATD = 2;
    public static final int FF_CMP_SSE = 1;
    public static final int FF_CMP_VSAD = 8;
    public static final int FF_CMP_VSSE = 9;
    public static final int FF_CMP_W53 = 11;
    public static final int FF_CMP_W97 = 12;
    public static final int FF_CMP_ZERO = 7;
    public static final int FF_CODEC_PROPERTY_CLOSED_CAPTIONS = 2;
    public static final int FF_CODEC_PROPERTY_LOSSLESS = 1;
    public static final int FF_CODER_TYPE_AC = 1;
    public static final int FF_CODER_TYPE_RAW = 2;
    public static final int FF_CODER_TYPE_RLE = 3;
    public static final int FF_CODER_TYPE_VLC = 0;
    public static final int FF_COMPLIANCE_EXPERIMENTAL = -2;
    public static final int FF_COMPLIANCE_NORMAL = 0;
    public static final int FF_COMPLIANCE_STRICT = 1;
    public static final int FF_COMPLIANCE_UNOFFICIAL = -1;
    public static final int FF_COMPLIANCE_VERY_STRICT = 2;
    public static final int FF_COMPRESSION_DEFAULT = -1;
    public static final int FF_DCT_ALTIVEC = 5;
    public static final int FF_DCT_AUTO = 0;
    public static final int FF_DCT_FAAN = 6;
    public static final int FF_DCT_FASTINT = 1;
    public static final int FF_DCT_INT = 2;
    public static final int FF_DCT_MMX = 3;
    public static final int FF_DEBUG_BITSTREAM = 4;
    public static final int FF_DEBUG_BUFFERS = 32768;
    public static final int FF_DEBUG_BUGS = 4096;
    public static final int FF_DEBUG_DCT_COEFF = 64;
    public static final int FF_DEBUG_ER = 1024;
    public static final int FF_DEBUG_GREEN_MD = 8388608;
    public static final int FF_DEBUG_MB_TYPE = 8;
    public static final int FF_DEBUG_MMCO = 2048;
    public static final int FF_DEBUG_MV = 32;
    public static final int FF_DEBUG_NOMC = 16777216;
    public static final int FF_DEBUG_PICT_INFO = 1;
    public static final int FF_DEBUG_QP = 16;
    public static final int FF_DEBUG_RC = 2;
    public static final int FF_DEBUG_SKIP = 128;
    public static final int FF_DEBUG_STARTCODE = 256;
    public static final int FF_DEBUG_THREADS = 65536;
    public static final int FF_DEBUG_VIS_MB_TYPE = 16384;
    public static final int FF_DEBUG_VIS_MV_B_BACK = 4;
    public static final int FF_DEBUG_VIS_MV_B_FOR = 2;
    public static final int FF_DEBUG_VIS_MV_P_FOR = 1;
    public static final int FF_DEBUG_VIS_QP = 8192;
    public static final int FF_EC_DEBLOCK = 2;
    public static final int FF_EC_FAVOR_INTER = 256;
    public static final int FF_EC_GUESS_MVS = 1;
    public static final int FF_IDCT_ALTIVEC = 8;
    public static final int FF_IDCT_ARM = 7;
    public static final int FF_IDCT_AUTO = 0;
    public static final int FF_IDCT_FAAN = 20;
    public static final int FF_IDCT_INT = 1;
    public static final int FF_IDCT_NONE = 24;
    public static final int FF_IDCT_SIMPLE = 2;
    public static final int FF_IDCT_SIMPLEARM = 10;
    public static final int FF_IDCT_SIMPLEARMV5TE = 16;
    public static final int FF_IDCT_SIMPLEARMV6 = 17;
    public static final int FF_IDCT_SIMPLEAUTO = 128;
    public static final int FF_IDCT_SIMPLEMMX = 3;
    public static final int FF_IDCT_SIMPLENEON = 22;
    public static final int FF_IDCT_XVID = 14;
    public static final int FF_LEVEL_UNKNOWN = -99;
    public static final int FF_MB_DECISION_BITS = 1;
    public static final int FF_MB_DECISION_RD = 2;
    public static final int FF_MB_DECISION_SIMPLE = 0;
    public static final int FF_PRED_LEFT = 0;
    public static final int FF_PRED_MEDIAN = 2;
    public static final int FF_PRED_PLANE = 1;
    public static final int FF_PROFILE_AAC_ELD = 38;
    public static final int FF_PROFILE_AAC_HE = 4;
    public static final int FF_PROFILE_AAC_HE_V2 = 28;
    public static final int FF_PROFILE_AAC_LD = 22;
    public static final int FF_PROFILE_AAC_LOW = 1;
    public static final int FF_PROFILE_AAC_LTP = 3;
    public static final int FF_PROFILE_AAC_MAIN = 0;
    public static final int FF_PROFILE_AAC_SSR = 2;
    public static final int FF_PROFILE_ARIB_PROFILE_A = 0;
    public static final int FF_PROFILE_ARIB_PROFILE_C = 1;
    public static final int FF_PROFILE_AV1_HIGH = 1;
    public static final int FF_PROFILE_AV1_MAIN = 0;
    public static final int FF_PROFILE_AV1_PROFESSIONAL = 2;
    public static final int FF_PROFILE_DNXHD = 0;
    public static final int FF_PROFILE_DNXHR_444 = 5;
    public static final int FF_PROFILE_DNXHR_HQ = 3;
    public static final int FF_PROFILE_DNXHR_HQX = 4;
    public static final int FF_PROFILE_DNXHR_LB = 1;
    public static final int FF_PROFILE_DNXHR_SQ = 2;
    public static final int FF_PROFILE_DTS = 20;
    public static final int FF_PROFILE_DTS_96_24 = 40;
    public static final int FF_PROFILE_DTS_ES = 30;
    public static final int FF_PROFILE_DTS_EXPRESS = 70;
    public static final int FF_PROFILE_DTS_HD_HRA = 50;
    public static final int FF_PROFILE_DTS_HD_MA = 60;
    public static final int FF_PROFILE_H264_BASELINE = 66;
    public static final int FF_PROFILE_H264_CAVLC_444 = 44;
    public static final int FF_PROFILE_H264_CONSTRAINED = 512;
    public static final int FF_PROFILE_H264_CONSTRAINED_BASELINE = 578;
    public static final int FF_PROFILE_H264_EXTENDED = 88;
    public static final int FF_PROFILE_H264_HIGH = 100;
    public static final int FF_PROFILE_H264_HIGH_10 = 110;
    public static final int FF_PROFILE_H264_HIGH_10_INTRA = 2158;
    public static final int FF_PROFILE_H264_HIGH_422 = 122;
    public static final int FF_PROFILE_H264_HIGH_422_INTRA = 2170;
    public static final int FF_PROFILE_H264_HIGH_444 = 144;
    public static final int FF_PROFILE_H264_HIGH_444_INTRA = 2292;
    public static final int FF_PROFILE_H264_HIGH_444_PREDICTIVE = 244;
    public static final int FF_PROFILE_H264_INTRA = 2048;
    public static final int FF_PROFILE_H264_MAIN = 77;
    public static final int FF_PROFILE_H264_MULTIVIEW_HIGH = 118;
    public static final int FF_PROFILE_H264_STEREO_HIGH = 128;
    public static final int FF_PROFILE_HEVC_MAIN = 1;
    public static final int FF_PROFILE_HEVC_MAIN_10 = 2;
    public static final int FF_PROFILE_HEVC_MAIN_STILL_PICTURE = 3;
    public static final int FF_PROFILE_HEVC_REXT = 4;
    public static final int FF_PROFILE_JPEG2000_CSTREAM_NO_RESTRICTION = 32768;
    public static final int FF_PROFILE_JPEG2000_CSTREAM_RESTRICTION_0 = 1;
    public static final int FF_PROFILE_JPEG2000_CSTREAM_RESTRICTION_1 = 2;
    public static final int FF_PROFILE_JPEG2000_DCINEMA_2K = 3;
    public static final int FF_PROFILE_JPEG2000_DCINEMA_4K = 4;
    public static final int FF_PROFILE_KLVA_ASYNC = 1;
    public static final int FF_PROFILE_KLVA_SYNC = 0;
    public static final int FF_PROFILE_MJPEG_HUFFMAN_BASELINE_DCT = 192;
    public static final int FF_PROFILE_MJPEG_HUFFMAN_EXTENDED_SEQUENTIAL_DCT = 193;
    public static final int FF_PROFILE_MJPEG_HUFFMAN_LOSSLESS = 195;
    public static final int FF_PROFILE_MJPEG_HUFFMAN_PROGRESSIVE_DCT = 194;
    public static final int FF_PROFILE_MJPEG_JPEG_LS = 247;
    public static final int FF_PROFILE_MPEG2_422 = 0;
    public static final int FF_PROFILE_MPEG2_AAC_HE = 131;
    public static final int FF_PROFILE_MPEG2_AAC_LOW = 128;
    public static final int FF_PROFILE_MPEG2_HIGH = 1;
    public static final int FF_PROFILE_MPEG2_MAIN = 4;
    public static final int FF_PROFILE_MPEG2_SIMPLE = 5;
    public static final int FF_PROFILE_MPEG2_SNR_SCALABLE = 3;
    public static final int FF_PROFILE_MPEG2_SS = 2;
    public static final int FF_PROFILE_MPEG4_ADVANCED_CODING = 11;
    public static final int FF_PROFILE_MPEG4_ADVANCED_CORE = 12;
    public static final int FF_PROFILE_MPEG4_ADVANCED_REAL_TIME = 9;
    public static final int FF_PROFILE_MPEG4_ADVANCED_SCALABLE_TEXTURE = 13;
    public static final int FF_PROFILE_MPEG4_ADVANCED_SIMPLE = 15;
    public static final int FF_PROFILE_MPEG4_BASIC_ANIMATED_TEXTURE = 7;
    public static final int FF_PROFILE_MPEG4_CORE = 2;
    public static final int FF_PROFILE_MPEG4_CORE_SCALABLE = 10;
    public static final int FF_PROFILE_MPEG4_HYBRID = 8;
    public static final int FF_PROFILE_MPEG4_MAIN = 3;
    public static final int FF_PROFILE_MPEG4_N_BIT = 4;
    public static final int FF_PROFILE_MPEG4_SCALABLE_TEXTURE = 5;
    public static final int FF_PROFILE_MPEG4_SIMPLE = 0;
    public static final int FF_PROFILE_MPEG4_SIMPLE_FACE_ANIMATION = 6;
    public static final int FF_PROFILE_MPEG4_SIMPLE_SCALABLE = 1;
    public static final int FF_PROFILE_MPEG4_SIMPLE_STUDIO = 14;
    public static final int FF_PROFILE_PRORES_4444 = 4;
    public static final int FF_PROFILE_PRORES_HQ = 3;
    public static final int FF_PROFILE_PRORES_LT = 1;
    public static final int FF_PROFILE_PRORES_PROXY = 0;
    public static final int FF_PROFILE_PRORES_STANDARD = 2;
    public static final int FF_PROFILE_PRORES_XQ = 5;
    public static final int FF_PROFILE_RESERVED = -100;
    public static final int FF_PROFILE_SBC_MSBC = 1;
    public static final int FF_PROFILE_UNKNOWN = -99;
    public static final int FF_PROFILE_VC1_ADVANCED = 3;
    public static final int FF_PROFILE_VC1_COMPLEX = 2;
    public static final int FF_PROFILE_VC1_MAIN = 1;
    public static final int FF_PROFILE_VC1_SIMPLE = 0;
    public static final int FF_PROFILE_VP9_0 = 0;
    public static final int FF_PROFILE_VP9_1 = 1;
    public static final int FF_PROFILE_VP9_2 = 2;
    public static final int FF_PROFILE_VP9_3 = 3;
    public static final int FF_SUB_CHARENC_MODE_AUTOMATIC = 0;
    public static final int FF_SUB_CHARENC_MODE_DO_NOTHING = -1;
    public static final int FF_SUB_CHARENC_MODE_IGNORE = 2;
    public static final int FF_SUB_CHARENC_MODE_PRE_DECODER = 1;
    public static final int FF_SUB_TEXT_FMT_ASS = 0;
    public static final int FF_SUB_TEXT_FMT_ASS_WITH_TIMINGS = 1;
    public static final int FF_THREAD_FRAME = 1;
    public static final int FF_THREAD_SLICE = 2;
    public static final int SLICE_FLAG_ALLOW_FIELD = 2;
    public static final int SLICE_FLAG_ALLOW_PLANE = 4;
    public static final int SLICE_FLAG_CODED_ORDER = 1;

    private native void allocate();

    private native void allocateArray(long j);

    public native int active_thread_type();

    public native AVCodecContext active_thread_type(int i);

    public native int apply_cropping();

    public native AVCodecContext apply_cropping(int i);

    @Cast({"AVAudioServiceType"})
    public native int audio_service_type();

    public native AVCodecContext audio_service_type(int i);

    public native AVCodecContext av_class(AVClass aVClass);

    @Const
    public native AVClass av_class();

    @Deprecated
    public native int b_frame_strategy();

    public native AVCodecContext b_frame_strategy(int i);

    public native float b_quant_factor();

    public native AVCodecContext b_quant_factor(float f);

    public native float b_quant_offset();

    public native AVCodecContext b_quant_offset(float f);

    @Deprecated
    public native int b_sensitivity();

    public native AVCodecContext b_sensitivity(int i);

    public native int bidir_refine();

    public native AVCodecContext bidir_refine(int i);

    @Cast({"int64_t"})
    public native long bit_rate();

    public native AVCodecContext bit_rate(long j);

    public native int bit_rate_tolerance();

    public native AVCodecContext bit_rate_tolerance(int i);

    public native int bits_per_coded_sample();

    public native AVCodecContext bits_per_coded_sample(int i);

    public native int bits_per_raw_sample();

    public native AVCodecContext bits_per_raw_sample(int i);

    public native int block_align();

    public native AVCodecContext block_align(int i);

    @Deprecated
    public native int brd_scale();

    public native AVCodecContext brd_scale(int i);

    @Cast({"uint64_t"})
    public native long channel_layout();

    public native AVCodecContext channel_layout(long j);

    public native int channels();

    public native AVCodecContext channels(int i);

    public native AVCodecContext chroma_intra_matrix(ShortPointer shortPointer);

    @Cast({"uint16_t*"})
    public native ShortPointer chroma_intra_matrix();

    @Cast({"AVChromaLocation"})
    public native int chroma_sample_location();

    public native AVCodecContext chroma_sample_location(int i);

    @Deprecated
    public native int chromaoffset();

    public native AVCodecContext chromaoffset(int i);

    @Const
    public native AVCodec codec();

    public native AVCodecContext codec(AVCodec aVCodec);

    public native AVCodecContext codec_descriptor(AVCodecDescriptor aVCodecDescriptor);

    @Const
    public native AVCodecDescriptor codec_descriptor();

    @Cast({"AVCodecID"})
    public native int codec_id();

    public native AVCodecContext codec_id(int i);

    @Cast({"unsigned int"})
    public native int codec_tag();

    public native AVCodecContext codec_tag(int i);

    @Cast({"AVMediaType"})
    public native int codec_type();

    public native AVCodecContext codec_type(int i);

    public native AVCodecContext codec_whitelist(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer codec_whitelist();

    public native AVCodecContext coded_frame(AVFrame aVFrame);

    @Deprecated
    public native AVFrame coded_frame();

    public native int coded_height();

    public native AVCodecContext coded_height(int i);

    public native AVCodecContext coded_side_data(AVPacketSideData aVPacketSideData);

    public native AVPacketSideData coded_side_data();

    public native int coded_width();

    public native AVCodecContext coded_width(int i);

    @Deprecated
    public native int coder_type();

    public native AVCodecContext coder_type(int i);

    @Cast({"AVColorPrimaries"})
    public native int color_primaries();

    public native AVCodecContext color_primaries(int i);

    @Cast({"AVColorRange"})
    public native int color_range();

    public native AVCodecContext color_range(int i);

    @Cast({"AVColorTransferCharacteristic"})
    public native int color_trc();

    public native AVCodecContext color_trc(int i);

    @Cast({"AVColorSpace"})
    public native int colorspace();

    public native AVCodecContext colorspace(int i);

    public native int compression_level();

    public native AVCodecContext compression_level(int i);

    @Deprecated
    public native int context_model();

    public native AVCodecContext context_model(int i);

    public native int cutoff();

    public native AVCodecContext cutoff(int i);

    public native float dark_masking();

    public native AVCodecContext dark_masking(float f);

    public native int dct_algo();

    public native AVCodecContext dct_algo(int i);

    public native int debug();

    public native AVCodecContext debug(int i);

    public native int debug_mv();

    public native AVCodecContext debug_mv(int i);

    public native int delay();

    public native AVCodecContext delay(int i);

    public native int dia_size();

    public native AVCodecContext dia_size(int i);

    public native int discard_damaged_percentage();

    public native AVCodecContext discard_damaged_percentage(int i);

    public native Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int draw_horiz_band();

    public native AVCodecContext draw_horiz_band(Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int);

    public native AVCodecContext dump_separator(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer dump_separator();

    public native int err_recognition();

    public native AVCodecContext err_recognition(int i);

    @Cast({"uint64_t"})
    public native long error(int i);

    public native AVCodecContext error(int i, long j);

    @MemberGetter
    @Cast({"uint64_t*"})
    public native LongPointer error();

    public native int error_concealment();

    public native AVCodecContext error_concealment(int i);

    public native Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int execute();

    public native AVCodecContext execute(Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int);

    public native Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int execute2();

    public native AVCodecContext execute2(Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int);

    public native int export_side_data();

    public native AVCodecContext export_side_data(int i);

    public native int extra_hw_frames();

    public native AVCodecContext extra_hw_frames(int i);

    public native AVCodecContext extradata(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer extradata();

    public native int extradata_size();

    public native AVCodecContext extradata_size(int i);

    @Cast({"AVFieldOrder"})
    public native int field_order();

    public native AVCodecContext field_order(int i);

    public native int flags();

    public native AVCodecContext flags(int i);

    public native int flags2();

    public native AVCodecContext flags2(int i);

    @Deprecated
    public native int frame_bits();

    public native AVCodecContext frame_bits(int i);

    public native int frame_number();

    public native AVCodecContext frame_number(int i);

    public native int frame_size();

    public native AVCodecContext frame_size(int i);

    @Deprecated
    public native int frame_skip_cmp();

    public native AVCodecContext frame_skip_cmp(int i);

    @Deprecated
    public native int frame_skip_exp();

    public native AVCodecContext frame_skip_exp(int i);

    @Deprecated
    public native int frame_skip_factor();

    public native AVCodecContext frame_skip_factor(int i);

    @Deprecated
    public native int frame_skip_threshold();

    public native AVCodecContext frame_skip_threshold(int i);

    public native AVCodecContext framerate(AVRational aVRational);

    @ByRef
    public native AVRational framerate();

    public native Get_buffer2_AVCodecContext_AVFrame_int get_buffer2();

    public native AVCodecContext get_buffer2(Get_buffer2_AVCodecContext_AVFrame_int get_buffer2_AVCodecContext_AVFrame_int);

    public native Get_format_AVCodecContext_IntPointer get_format();

    public native AVCodecContext get_format(Get_format_AVCodecContext_IntPointer get_format_AVCodecContext_IntPointer);

    public native int global_quality();

    public native AVCodecContext global_quality(int i);

    public native int gop_size();

    public native AVCodecContext gop_size(int i);

    public native int has_b_frames();

    public native AVCodecContext has_b_frames(int i);

    @Deprecated
    public native int header_bits();

    public native AVCodecContext header_bits(int i);

    public native int height();

    public native AVCodecContext height(int i);

    public native AVCodecContext hw_device_ctx(AVBufferRef aVBufferRef);

    public native AVBufferRef hw_device_ctx();

    public native AVCodecContext hw_frames_ctx(AVBufferRef aVBufferRef);

    public native AVBufferRef hw_frames_ctx();

    public native AVCodecContext hwaccel(AVHWAccel aVHWAccel);

    @Const
    public native AVHWAccel hwaccel();

    public native AVCodecContext hwaccel_context(Pointer pointer);

    public native Pointer hwaccel_context();

    public native int hwaccel_flags();

    public native AVCodecContext hwaccel_flags(int i);

    @Deprecated
    public native int i_count();

    public native AVCodecContext i_count(int i);

    public native float i_quant_factor();

    public native AVCodecContext i_quant_factor(float f);

    public native float i_quant_offset();

    public native AVCodecContext i_quant_offset(float f);

    @Deprecated
    public native int i_tex_bits();

    public native AVCodecContext i_tex_bits(int i);

    public native int idct_algo();

    public native AVCodecContext idct_algo(int i);

    public native int ildct_cmp();

    public native AVCodecContext ildct_cmp(int i);

    public native int initial_padding();

    public native AVCodecContext initial_padding(int i);

    public native AVCodecContext inter_matrix(ShortPointer shortPointer);

    @Cast({"uint16_t*"})
    public native ShortPointer inter_matrix();

    public native AVCodecContext internal(AVCodecInternal aVCodecInternal);

    public native AVCodecInternal internal();

    public native int intra_dc_precision();

    public native AVCodecContext intra_dc_precision(int i);

    public native AVCodecContext intra_matrix(ShortPointer shortPointer);

    @Cast({"uint16_t*"})
    public native ShortPointer intra_matrix();

    public native int keyint_min();

    public native AVCodecContext keyint_min(int i);

    public native int last_predictor_count();

    public native AVCodecContext last_predictor_count(int i);

    public native int level();

    public native AVCodecContext level(int i);

    public native int log_level_offset();

    public native AVCodecContext log_level_offset(int i);

    public native int lowres();

    public native AVCodecContext lowres(int i);

    public native float lumi_masking();

    public native AVCodecContext lumi_masking(float f);

    public native int max_b_frames();

    public native AVCodecContext max_b_frames(int i);

    @Cast({"int64_t"})
    public native long max_pixels();

    public native AVCodecContext max_pixels(long j);

    @Deprecated
    public native int max_prediction_order();

    public native AVCodecContext max_prediction_order(int i);

    public native int max_qdiff();

    public native AVCodecContext max_qdiff(int i);

    @Cast({"int64_t"})
    public native long max_samples();

    public native AVCodecContext max_samples(long j);

    public native int mb_cmp();

    public native AVCodecContext mb_cmp(int i);

    public native int mb_decision();

    public native AVCodecContext mb_decision(int i);

    public native int mb_lmax();

    public native AVCodecContext mb_lmax(int i);

    public native int mb_lmin();

    public native AVCodecContext mb_lmin(int i);

    public native int me_cmp();

    public native AVCodecContext me_cmp(int i);

    @Deprecated
    public native int me_penalty_compensation();

    public native AVCodecContext me_penalty_compensation(int i);

    public native int me_pre_cmp();

    public native AVCodecContext me_pre_cmp(int i);

    public native int me_range();

    public native AVCodecContext me_range(int i);

    public native int me_sub_cmp();

    public native AVCodecContext me_sub_cmp(int i);

    public native int me_subpel_quality();

    public native AVCodecContext me_subpel_quality(int i);

    @Deprecated
    public native int min_prediction_order();

    public native AVCodecContext min_prediction_order(int i);

    @Deprecated
    public native int misc_bits();

    public native AVCodecContext misc_bits(int i);

    @Deprecated
    public native int mpeg_quant();

    public native AVCodecContext mpeg_quant(int i);

    public native int mv0_threshold();

    public native AVCodecContext mv0_threshold(int i);

    @Deprecated
    public native int mv_bits();

    public native AVCodecContext mv_bits(int i);

    public native int nb_coded_side_data();

    public native AVCodecContext nb_coded_side_data(int i);

    @Deprecated
    public native int noise_reduction();

    public native AVCodecContext noise_reduction(int i);

    public native int nsse_weight();

    public native AVCodecContext nsse_weight(int i);

    public native AVCodecContext opaque(Pointer pointer);

    public native Pointer opaque();

    @Deprecated
    public native int p_count();

    public native AVCodecContext p_count(int i);

    public native float p_masking();

    public native AVCodecContext p_masking(float f);

    @Deprecated
    public native int p_tex_bits();

    public native AVCodecContext p_tex_bits(int i);

    @Cast({"AVPixelFormat"})
    public native int pix_fmt();

    public native AVCodecContext pix_fmt(int i);

    public native AVCodecContext pkt_timebase(AVRational aVRational);

    @ByRef
    public native AVRational pkt_timebase();

    public native int pre_dia_size();

    public native AVCodecContext pre_dia_size(int i);

    @Deprecated
    public native int pre_me();

    public native AVCodecContext pre_me(int i);

    @Deprecated
    public native int prediction_method();

    public native AVCodecContext prediction_method(int i);

    public native AVCodecContext priv_data(Pointer pointer);

    public native Pointer priv_data();

    public native int profile();

    public native AVCodecContext profile(int i);

    @Cast({"unsigned"})
    public native int properties();

    public native AVCodecContext properties(int i);

    @Cast({"int64_t"})
    public native long pts_correction_last_dts();

    public native AVCodecContext pts_correction_last_dts(long j);

    @Cast({"int64_t"})
    public native long pts_correction_last_pts();

    public native AVCodecContext pts_correction_last_pts(long j);

    @Cast({"int64_t"})
    public native long pts_correction_num_faulty_dts();

    public native AVCodecContext pts_correction_num_faulty_dts(long j);

    @Cast({"int64_t"})
    public native long pts_correction_num_faulty_pts();

    public native AVCodecContext pts_correction_num_faulty_pts(long j);

    public native float qblur();

    public native AVCodecContext qblur(float f);

    public native float qcompress();

    public native AVCodecContext qcompress(float f);

    public native int qmax();

    public native AVCodecContext qmax(int i);

    public native int qmin();

    public native AVCodecContext qmin(int i);

    public native int rc_buffer_size();

    public native AVCodecContext rc_buffer_size(int i);

    public native int rc_initial_buffer_occupancy();

    public native AVCodecContext rc_initial_buffer_occupancy(int i);

    public native float rc_max_available_vbv_use();

    public native AVCodecContext rc_max_available_vbv_use(float f);

    @Cast({"int64_t"})
    public native long rc_max_rate();

    public native AVCodecContext rc_max_rate(long j);

    @Cast({"int64_t"})
    public native long rc_min_rate();

    public native AVCodecContext rc_min_rate(long j);

    public native float rc_min_vbv_overflow_use();

    public native AVCodecContext rc_min_vbv_overflow_use(float f);

    public native AVCodecContext rc_override(RcOverride rcOverride);

    public native RcOverride rc_override();

    public native int rc_override_count();

    public native AVCodecContext rc_override_count(int i);

    @Deprecated
    public native int refcounted_frames();

    public native AVCodecContext refcounted_frames(int i);

    public native int refs();

    public native AVCodecContext refs(int i);

    @Cast({"int64_t"})
    public native long reordered_opaque();

    public native AVCodecContext reordered_opaque(long j);

    @Cast({"uint64_t"})
    public native long request_channel_layout();

    public native AVCodecContext request_channel_layout(long j);

    @Cast({"AVSampleFormat"})
    public native int request_sample_fmt();

    public native AVCodecContext request_sample_fmt(int i);

    public native Rtp_callback_AVCodecContext_Pointer_int_int rtp_callback();

    public native AVCodecContext rtp_callback(Rtp_callback_AVCodecContext_Pointer_int_int rtp_callback_AVCodecContext_Pointer_int_int);

    @Deprecated
    public native int rtp_payload_size();

    public native AVCodecContext rtp_payload_size(int i);

    public native AVCodecContext sample_aspect_ratio(AVRational aVRational);

    @ByRef
    public native AVRational sample_aspect_ratio();

    @Cast({"AVSampleFormat"})
    public native int sample_fmt();

    public native AVCodecContext sample_fmt(int i);

    public native int sample_rate();

    public native AVCodecContext sample_rate(int i);

    @Deprecated
    public native int scenechange_threshold();

    public native AVCodecContext scenechange_threshold(int i);

    public native int seek_preroll();

    public native AVCodecContext seek_preroll(int i);

    @Deprecated
    public native int side_data_only_packets();

    public native AVCodecContext side_data_only_packets(int i);

    public native int skip_alpha();

    public native AVCodecContext skip_alpha(int i);

    public native int skip_bottom();

    public native AVCodecContext skip_bottom(int i);

    @Deprecated
    public native int skip_count();

    public native AVCodecContext skip_count(int i);

    @Cast({"AVDiscard"})
    public native int skip_frame();

    public native AVCodecContext skip_frame(int i);

    @Cast({"AVDiscard"})
    public native int skip_idct();

    public native AVCodecContext skip_idct(int i);

    @Cast({"AVDiscard"})
    public native int skip_loop_filter();

    public native AVCodecContext skip_loop_filter(int i);

    public native int skip_top();

    public native AVCodecContext skip_top(int i);

    public native int slice_count();

    public native AVCodecContext slice_count(int i);

    public native int slice_flags();

    public native AVCodecContext slice_flags(int i);

    public native AVCodecContext slice_offset(IntPointer intPointer);

    public native IntPointer slice_offset();

    public native int slices();

    public native AVCodecContext slices(int i);

    public native float spatial_cplx_masking();

    public native AVCodecContext spatial_cplx_masking(float f);

    public native AVCodecContext stats_in(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer stats_in();

    public native AVCodecContext stats_out(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer stats_out();

    public native int strict_std_compliance();

    public native AVCodecContext strict_std_compliance(int i);

    public native AVCodecContext sub_charenc(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer sub_charenc();

    public native int sub_charenc_mode();

    public native AVCodecContext sub_charenc_mode(int i);

    public native int sub_text_format();

    public native AVCodecContext sub_text_format(int i);

    public native AVCodecContext subtitle_header(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer subtitle_header();

    public native int subtitle_header_size();

    public native AVCodecContext subtitle_header_size(int i);

    @Cast({"AVPixelFormat"})
    public native int sw_pix_fmt();

    public native AVCodecContext sw_pix_fmt(int i);

    public native float temporal_cplx_masking();

    public native AVCodecContext temporal_cplx_masking(float f);

    public native int thread_count();

    public native AVCodecContext thread_count(int i);

    public native int thread_safe_callbacks();

    public native AVCodecContext thread_safe_callbacks(int i);

    public native int thread_type();

    public native AVCodecContext thread_type(int i);

    public native int ticks_per_frame();

    public native AVCodecContext ticks_per_frame(int i);

    public native AVCodecContext time_base(AVRational aVRational);

    @ByRef
    public native AVRational time_base();

    @Deprecated
    @Cast({"int64_t"})
    public native long timecode_frame_start();

    public native AVCodecContext timecode_frame_start(long j);

    public native int trailing_padding();

    public native AVCodecContext trailing_padding(int i);

    public native int trellis();

    public native AVCodecContext trellis(int i);

    @Deprecated
    @Cast({"uint64_t"})
    public native long vbv_delay();

    public native AVCodecContext vbv_delay(long j);

    public native int width();

    public native AVCodecContext width(int i);

    public native int workaround_bugs();

    public native AVCodecContext workaround_bugs(int i);

    static {
        Loader.load();
    }

    public AVCodecContext() {
        super((Pointer) null);
        allocate();
    }

    public AVCodecContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVCodecContext(Pointer pointer) {
        super(pointer);
    }

    public AVCodecContext position(long j) {
        return (AVCodecContext) super.position(j);
    }

    public AVCodecContext getPointer(long j) {
        return new AVCodecContext((Pointer) this).position(this.position + j);
    }

    public static class Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int extends FunctionPointer {
        private native void allocate();

        public native void call(AVCodecContext aVCodecContext, @Const AVFrame aVFrame, IntPointer intPointer, int i, int i2, int i3);

        static {
            Loader.load();
        }

        public Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int(Pointer pointer) {
            super(pointer);
        }

        protected Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int() {
            allocate();
        }
    }

    public static class Get_format_AVCodecContext_IntPointer extends FunctionPointer {
        private native void allocate();

        @Cast({"AVPixelFormat"})
        public native int call(AVCodecContext aVCodecContext, @Cast({"const AVPixelFormat*"}) IntPointer intPointer);

        static {
            Loader.load();
        }

        public Get_format_AVCodecContext_IntPointer(Pointer pointer) {
            super(pointer);
        }

        protected Get_format_AVCodecContext_IntPointer() {
            allocate();
        }
    }

    public static class Get_buffer2_AVCodecContext_AVFrame_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, AVFrame aVFrame, int i);

        static {
            Loader.load();
        }

        public Get_buffer2_AVCodecContext_AVFrame_int(Pointer pointer) {
            super(pointer);
        }

        protected Get_buffer2_AVCodecContext_AVFrame_int() {
            allocate();
        }
    }

    public static class Rtp_callback_AVCodecContext_Pointer_int_int extends FunctionPointer {
        private native void allocate();

        @Deprecated
        public native void call(AVCodecContext aVCodecContext, Pointer pointer, int i, int i2);

        static {
            Loader.load();
        }

        public Rtp_callback_AVCodecContext_Pointer_int_int(Pointer pointer) {
            super(pointer);
        }

        protected Rtp_callback_AVCodecContext_Pointer_int_int() {
            allocate();
        }
    }

    public static class Func_AVCodecContext_Pointer extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, Pointer pointer);

        static {
            Loader.load();
        }

        public Func_AVCodecContext_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Func_AVCodecContext_Pointer() {
            allocate();
        }
    }

    public static class Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, Func_AVCodecContext_Pointer func_AVCodecContext_Pointer, Pointer pointer, IntPointer intPointer, int i, int i2);

        static {
            Loader.load();
        }

        public Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int(Pointer pointer) {
            super(pointer);
        }

        protected Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int() {
            allocate();
        }
    }

    public static class Func_AVCodecContext_Pointer_int_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, Pointer pointer, int i, int i2);

        static {
            Loader.load();
        }

        public Func_AVCodecContext_Pointer_int_int(Pointer pointer) {
            super(pointer);
        }

        protected Func_AVCodecContext_Pointer_int_int() {
            allocate();
        }
    }

    public static class Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, Func_AVCodecContext_Pointer_int_int func_AVCodecContext_Pointer_int_int, Pointer pointer, IntPointer intPointer, int i);

        static {
            Loader.load();
        }

        public Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int() {
            allocate();
        }
    }
}
