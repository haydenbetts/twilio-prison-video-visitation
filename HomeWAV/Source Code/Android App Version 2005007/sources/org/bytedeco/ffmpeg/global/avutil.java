package org.bytedeco.ffmpeg.global;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.bytedeco.ffmpeg.avutil.AVAES;
import org.bytedeco.ffmpeg.avutil.AVAESCTR;
import org.bytedeco.ffmpeg.avutil.AVAudioFifo;
import org.bytedeco.ffmpeg.avutil.AVBPrint;
import org.bytedeco.ffmpeg.avutil.AVBlowfish;
import org.bytedeco.ffmpeg.avutil.AVBufferPool;
import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVCAMELLIA;
import org.bytedeco.ffmpeg.avutil.AVCAST5;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVContentLightMetadata;
import org.bytedeco.ffmpeg.avutil.AVDES;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVDictionaryEntry;
import org.bytedeco.ffmpeg.avutil.AVDownmixInfo;
import org.bytedeco.ffmpeg.avutil.AVDynamicHDRPlus;
import org.bytedeco.ffmpeg.avutil.AVEncryptionInfo;
import org.bytedeco.ffmpeg.avutil.AVEncryptionInitInfo;
import org.bytedeco.ffmpeg.avutil.AVExpr;
import org.bytedeco.ffmpeg.avutil.AVFifoBuffer;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVFrameSideData;
import org.bytedeco.ffmpeg.avutil.AVHMAC;
import org.bytedeco.ffmpeg.avutil.AVHWFramesConstraints;
import org.bytedeco.ffmpeg.avutil.AVHashContext;
import org.bytedeco.ffmpeg.avutil.AVLFG;
import org.bytedeco.ffmpeg.avutil.AVMD5;
import org.bytedeco.ffmpeg.avutil.AVMasteringDisplayMetadata;
import org.bytedeco.ffmpeg.avutil.AVOption;
import org.bytedeco.ffmpeg.avutil.AVOptionRanges;
import org.bytedeco.ffmpeg.avutil.AVPixFmtDescriptor;
import org.bytedeco.ffmpeg.avutil.AVRC4;
import org.bytedeco.ffmpeg.avutil.AVRIPEMD;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.avutil.AVSHA;
import org.bytedeco.ffmpeg.avutil.AVSHA512;
import org.bytedeco.ffmpeg.avutil.AVSphericalMapping;
import org.bytedeco.ffmpeg.avutil.AVStereo3D;
import org.bytedeco.ffmpeg.avutil.AVTEA;
import org.bytedeco.ffmpeg.avutil.AVTWOFISH;
import org.bytedeco.ffmpeg.avutil.AVTXContext;
import org.bytedeco.ffmpeg.avutil.AVThreadMessageQueue;
import org.bytedeco.ffmpeg.avutil.AVTimecode;
import org.bytedeco.ffmpeg.avutil.AVTreeNode;
import org.bytedeco.ffmpeg.avutil.AVXTEA;
import org.bytedeco.ffmpeg.avutil.Alloc_Pointer_int;
import org.bytedeco.ffmpeg.avutil.Alloc_int;
import org.bytedeco.ffmpeg.avutil.Callback_Pointer_int_BytePointer_Pointer;
import org.bytedeco.ffmpeg.avutil.Callback_Pointer_int_String_Pointer;
import org.bytedeco.ffmpeg.avutil.Cmp_Const_Pointer_Const_Pointer;
import org.bytedeco.ffmpeg.avutil.Cmp_Pointer_Pointer;
import org.bytedeco.ffmpeg.avutil.Enu_Pointer_Pointer;
import org.bytedeco.ffmpeg.avutil.Free_Pointer_ByteBuffer;
import org.bytedeco.ffmpeg.avutil.Free_Pointer_BytePointer;
import org.bytedeco.ffmpeg.avutil.Free_Pointer_byte__;
import org.bytedeco.ffmpeg.avutil.Free_func_Pointer;
import org.bytedeco.ffmpeg.avutil.Func_Pointer_Pointer_int;
import org.bytedeco.ffmpeg.avutil.Int_func_Pointer_Pointer_int;
import org.bytedeco.ffmpeg.avutil.LogCallback;
import org.bytedeco.ffmpeg.avutil.Pool_free_Pointer;
import org.bytedeco.ffmpeg.avutil.av_pixelutils_sad_fn;
import org.bytedeco.ffmpeg.avutil.av_tx_fn;
import org.bytedeco.ffmpeg.avutil.tm;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.NoException;

public class avutil extends org.bytedeco.ffmpeg.presets.avutil {
    public static final int AES_CTR_IV_SIZE = 8;
    public static final int AES_CTR_KEY_SIZE = 16;
    public static final int AVCHROMA_LOC_BOTTOM = 6;
    public static final int AVCHROMA_LOC_BOTTOMLEFT = 5;
    public static final int AVCHROMA_LOC_CENTER = 2;
    public static final int AVCHROMA_LOC_LEFT = 1;
    public static final int AVCHROMA_LOC_NB = 7;
    public static final int AVCHROMA_LOC_TOP = 4;
    public static final int AVCHROMA_LOC_TOPLEFT = 3;
    public static final int AVCHROMA_LOC_UNSPECIFIED = 0;
    public static final int AVCOL_PRI_BT2020 = 9;
    public static final int AVCOL_PRI_BT470BG = 5;
    public static final int AVCOL_PRI_BT470M = 4;
    public static final int AVCOL_PRI_BT709 = 1;
    public static final int AVCOL_PRI_EBU3213 = 22;
    public static final int AVCOL_PRI_FILM = 8;
    public static final int AVCOL_PRI_JEDEC_P22 = 22;
    public static final int AVCOL_PRI_NB = 23;
    public static final int AVCOL_PRI_RESERVED = 3;
    public static final int AVCOL_PRI_RESERVED0 = 0;
    public static final int AVCOL_PRI_SMPTE170M = 6;
    public static final int AVCOL_PRI_SMPTE240M = 7;
    public static final int AVCOL_PRI_SMPTE428 = 10;
    public static final int AVCOL_PRI_SMPTE431 = 11;
    public static final int AVCOL_PRI_SMPTE432 = 12;
    public static final int AVCOL_PRI_SMPTEST428_1 = 10;
    public static final int AVCOL_PRI_UNSPECIFIED = 2;
    public static final int AVCOL_RANGE_JPEG = 2;
    public static final int AVCOL_RANGE_MPEG = 1;
    public static final int AVCOL_RANGE_NB = 3;
    public static final int AVCOL_RANGE_UNSPECIFIED = 0;
    public static final int AVCOL_SPC_BT2020_CL = 10;
    public static final int AVCOL_SPC_BT2020_NCL = 9;
    public static final int AVCOL_SPC_BT470BG = 5;
    public static final int AVCOL_SPC_BT709 = 1;
    public static final int AVCOL_SPC_CHROMA_DERIVED_CL = 13;
    public static final int AVCOL_SPC_CHROMA_DERIVED_NCL = 12;
    public static final int AVCOL_SPC_FCC = 4;
    public static final int AVCOL_SPC_ICTCP = 14;
    public static final int AVCOL_SPC_NB = 15;
    public static final int AVCOL_SPC_RESERVED = 3;
    public static final int AVCOL_SPC_RGB = 0;
    public static final int AVCOL_SPC_SMPTE170M = 6;
    public static final int AVCOL_SPC_SMPTE2085 = 11;
    public static final int AVCOL_SPC_SMPTE240M = 7;
    public static final int AVCOL_SPC_UNSPECIFIED = 2;
    public static final int AVCOL_SPC_YCGCO = 8;
    public static final int AVCOL_SPC_YCOCG = 8;
    public static final int AVCOL_TRC_ARIB_STD_B67 = 18;
    public static final int AVCOL_TRC_BT1361_ECG = 12;
    public static final int AVCOL_TRC_BT2020_10 = 14;
    public static final int AVCOL_TRC_BT2020_12 = 15;
    public static final int AVCOL_TRC_BT709 = 1;
    public static final int AVCOL_TRC_GAMMA22 = 4;
    public static final int AVCOL_TRC_GAMMA28 = 5;
    public static final int AVCOL_TRC_IEC61966_2_1 = 13;
    public static final int AVCOL_TRC_IEC61966_2_4 = 11;
    public static final int AVCOL_TRC_LINEAR = 8;
    public static final int AVCOL_TRC_LOG = 9;
    public static final int AVCOL_TRC_LOG_SQRT = 10;
    public static final int AVCOL_TRC_NB = 19;
    public static final int AVCOL_TRC_RESERVED = 3;
    public static final int AVCOL_TRC_RESERVED0 = 0;
    public static final int AVCOL_TRC_SMPTE170M = 6;
    public static final int AVCOL_TRC_SMPTE2084 = 16;
    public static final int AVCOL_TRC_SMPTE240M = 7;
    public static final int AVCOL_TRC_SMPTE428 = 17;
    public static final int AVCOL_TRC_SMPTEST2084 = 16;
    public static final int AVCOL_TRC_SMPTEST428_1 = 17;
    public static final int AVCOL_TRC_UNSPECIFIED = 2;
    public static final int AVERROR_BSF_NOT_FOUND = AVERROR_BSF_NOT_FOUND();
    public static final int AVERROR_BUFFER_TOO_SMALL = AVERROR_BUFFER_TOO_SMALL();
    public static final int AVERROR_BUG = AVERROR_BUG();
    public static final int AVERROR_BUG2 = AVERROR_BUG2();
    public static final int AVERROR_DECODER_NOT_FOUND = AVERROR_DECODER_NOT_FOUND();
    public static final int AVERROR_DEMUXER_NOT_FOUND = AVERROR_DEMUXER_NOT_FOUND();
    public static final int AVERROR_ENCODER_NOT_FOUND = AVERROR_ENCODER_NOT_FOUND();
    public static final int AVERROR_EOF = AVERROR_EOF();
    public static final int AVERROR_EXIT = AVERROR_EXIT();
    public static final int AVERROR_EXPERIMENTAL = -733130664;
    public static final int AVERROR_EXTERNAL = AVERROR_EXTERNAL();
    public static final int AVERROR_FILTER_NOT_FOUND = AVERROR_FILTER_NOT_FOUND();
    public static final int AVERROR_HTTP_BAD_REQUEST = AVERROR_HTTP_BAD_REQUEST();
    public static final int AVERROR_HTTP_FORBIDDEN = AVERROR_HTTP_FORBIDDEN();
    public static final int AVERROR_HTTP_NOT_FOUND = AVERROR_HTTP_NOT_FOUND();
    public static final int AVERROR_HTTP_OTHER_4XX = AVERROR_HTTP_OTHER_4XX();
    public static final int AVERROR_HTTP_SERVER_ERROR = AVERROR_HTTP_SERVER_ERROR();
    public static final int AVERROR_HTTP_UNAUTHORIZED = AVERROR_HTTP_UNAUTHORIZED();
    public static final int AVERROR_INPUT_CHANGED = -1668179713;
    public static final int AVERROR_INVALIDDATA = AVERROR_INVALIDDATA();
    public static final int AVERROR_MUXER_NOT_FOUND = AVERROR_MUXER_NOT_FOUND();
    public static final int AVERROR_OPTION_NOT_FOUND = AVERROR_OPTION_NOT_FOUND();
    public static final int AVERROR_OUTPUT_CHANGED = -1668179714;
    public static final int AVERROR_PATCHWELCOME = AVERROR_PATCHWELCOME();
    public static final int AVERROR_PROTOCOL_NOT_FOUND = AVERROR_PROTOCOL_NOT_FOUND();
    public static final int AVERROR_STREAM_NOT_FOUND = AVERROR_STREAM_NOT_FOUND();
    public static final int AVERROR_UNKNOWN = AVERROR_UNKNOWN();
    public static final int AVMEDIA_TYPE_ATTACHMENT = 4;
    public static final int AVMEDIA_TYPE_AUDIO = 1;
    public static final int AVMEDIA_TYPE_DATA = 2;
    public static final int AVMEDIA_TYPE_NB = 5;
    public static final int AVMEDIA_TYPE_SUBTITLE = 3;
    public static final int AVMEDIA_TYPE_UNKNOWN = -1;
    public static final int AVMEDIA_TYPE_VIDEO = 0;
    public static final int AVPALETTE_COUNT = 256;
    public static final int AVPALETTE_SIZE = 1024;
    public static final int AV_AFD_14_9 = 11;
    public static final int AV_AFD_16_9 = 10;
    public static final int AV_AFD_16_9_SP_14_9 = 14;
    public static final int AV_AFD_4_3 = 9;
    public static final int AV_AFD_4_3_SP_14_9 = 13;
    public static final int AV_AFD_SAME = 8;
    public static final int AV_AFD_SP_4_3 = 15;
    public static final int AV_BF_ROUNDS = 16;
    public static final int AV_BPRINT_SIZE_AUTOMATIC = 1;
    public static final int AV_BPRINT_SIZE_COUNT_ONLY = 0;
    public static final int AV_BPRINT_SIZE_UNLIMITED = -1;
    public static final int AV_BUFFER_FLAG_READONLY = 1;
    public static final long AV_CH_BACK_CENTER = 256;
    public static final long AV_CH_BACK_LEFT = 16;
    public static final long AV_CH_BACK_RIGHT = 32;
    public static final long AV_CH_FRONT_CENTER = 4;
    public static final long AV_CH_FRONT_LEFT = 1;
    public static final long AV_CH_FRONT_LEFT_OF_CENTER = 64;
    public static final long AV_CH_FRONT_RIGHT = 2;
    public static final long AV_CH_FRONT_RIGHT_OF_CENTER = 128;
    public static final long AV_CH_LAYOUT_2POINT1 = 11;
    public static final long AV_CH_LAYOUT_2_1 = 259;
    public static final long AV_CH_LAYOUT_2_2 = 1539;
    public static final long AV_CH_LAYOUT_3POINT1 = 15;
    public static final long AV_CH_LAYOUT_4POINT0 = 263;
    public static final long AV_CH_LAYOUT_4POINT1 = 271;
    public static final long AV_CH_LAYOUT_5POINT0 = 1543;
    public static final long AV_CH_LAYOUT_5POINT0_BACK = 55;
    public static final long AV_CH_LAYOUT_5POINT1 = 1551;
    public static final long AV_CH_LAYOUT_5POINT1_BACK = 63;
    public static final long AV_CH_LAYOUT_6POINT0 = 1799;
    public static final long AV_CH_LAYOUT_6POINT0_FRONT = 1731;
    public static final long AV_CH_LAYOUT_6POINT1 = 1807;
    public static final long AV_CH_LAYOUT_6POINT1_BACK = 319;
    public static final long AV_CH_LAYOUT_6POINT1_FRONT = 1739;
    public static final long AV_CH_LAYOUT_7POINT0 = 1591;
    public static final long AV_CH_LAYOUT_7POINT0_FRONT = 1735;
    public static final long AV_CH_LAYOUT_7POINT1 = 1599;
    public static final long AV_CH_LAYOUT_7POINT1_WIDE = 1743;
    public static final long AV_CH_LAYOUT_7POINT1_WIDE_BACK = 255;
    public static final long AV_CH_LAYOUT_HEXADECAGONAL = 6442710839L;
    public static final long AV_CH_LAYOUT_HEXAGONAL = 311;
    public static final long AV_CH_LAYOUT_MONO = 4;
    public static final long AV_CH_LAYOUT_NATIVE = Long.MIN_VALUE;
    public static final long AV_CH_LAYOUT_OCTAGONAL = 1847;
    public static final long AV_CH_LAYOUT_QUAD = 51;
    public static final long AV_CH_LAYOUT_STEREO = 3;
    public static final long AV_CH_LAYOUT_STEREO_DOWNMIX = 1610612736;
    public static final long AV_CH_LAYOUT_SURROUND = 7;
    public static final long AV_CH_LOW_FREQUENCY = 8;
    public static final long AV_CH_LOW_FREQUENCY_2 = 34359738368L;
    public static final long AV_CH_SIDE_LEFT = 512;
    public static final long AV_CH_SIDE_RIGHT = 1024;
    public static final long AV_CH_STEREO_LEFT = 536870912;
    public static final long AV_CH_STEREO_RIGHT = 1073741824;
    public static final long AV_CH_SURROUND_DIRECT_LEFT = 8589934592L;
    public static final long AV_CH_SURROUND_DIRECT_RIGHT = 17179869184L;
    public static final long AV_CH_TOP_BACK_CENTER = 65536;
    public static final long AV_CH_TOP_BACK_LEFT = 32768;
    public static final long AV_CH_TOP_BACK_RIGHT = 131072;
    public static final long AV_CH_TOP_CENTER = 2048;
    public static final long AV_CH_TOP_FRONT_CENTER = 8192;
    public static final long AV_CH_TOP_FRONT_LEFT = 4096;
    public static final long AV_CH_TOP_FRONT_RIGHT = 16384;
    public static final long AV_CH_WIDE_LEFT = 2147483648L;
    public static final long AV_CH_WIDE_RIGHT = 4294967296L;
    public static final int AV_CLASS_CATEGORY_BITSTREAM_FILTER = 8;
    public static final int AV_CLASS_CATEGORY_DECODER = 6;
    public static final int AV_CLASS_CATEGORY_DEMUXER = 4;
    public static final int AV_CLASS_CATEGORY_DEVICE_AUDIO_INPUT = 43;
    public static final int AV_CLASS_CATEGORY_DEVICE_AUDIO_OUTPUT = 42;
    public static final int AV_CLASS_CATEGORY_DEVICE_INPUT = 45;
    public static final int AV_CLASS_CATEGORY_DEVICE_OUTPUT = 44;
    public static final int AV_CLASS_CATEGORY_DEVICE_VIDEO_INPUT = 41;
    public static final int AV_CLASS_CATEGORY_DEVICE_VIDEO_OUTPUT = 40;
    public static final int AV_CLASS_CATEGORY_ENCODER = 5;
    public static final int AV_CLASS_CATEGORY_FILTER = 7;
    public static final int AV_CLASS_CATEGORY_INPUT = 1;
    public static final int AV_CLASS_CATEGORY_MUXER = 3;
    public static final int AV_CLASS_CATEGORY_NA = 0;
    public static final int AV_CLASS_CATEGORY_NB = 46;
    public static final int AV_CLASS_CATEGORY_OUTPUT = 2;
    public static final int AV_CLASS_CATEGORY_SWRESAMPLER = 10;
    public static final int AV_CLASS_CATEGORY_SWSCALER = 9;
    public static final int AV_CPU_FLAG_3DNOW = 4;
    public static final int AV_CPU_FLAG_3DNOWEXT = 32;
    public static final int AV_CPU_FLAG_AESNI = 524288;
    public static final int AV_CPU_FLAG_ALTIVEC = 1;
    public static final int AV_CPU_FLAG_ARMV5TE = 1;
    public static final int AV_CPU_FLAG_ARMV6 = 2;
    public static final int AV_CPU_FLAG_ARMV6T2 = 4;
    public static final int AV_CPU_FLAG_ARMV8 = 64;
    public static final int AV_CPU_FLAG_ATOM = 268435456;
    public static final int AV_CPU_FLAG_AVX = 16384;
    public static final int AV_CPU_FLAG_AVX2 = 32768;
    public static final int AV_CPU_FLAG_AVX512 = 1048576;
    public static final int AV_CPU_FLAG_AVXSLOW = 134217728;
    public static final int AV_CPU_FLAG_BMI1 = 131072;
    public static final int AV_CPU_FLAG_BMI2 = 262144;
    public static final int AV_CPU_FLAG_CMOV = 4096;
    public static final int AV_CPU_FLAG_FMA3 = 65536;
    public static final int AV_CPU_FLAG_FMA4 = 2048;
    public static final int AV_CPU_FLAG_FORCE = Integer.MIN_VALUE;
    public static final int AV_CPU_FLAG_MMX = 1;
    public static final int AV_CPU_FLAG_MMX2 = 2;
    public static final int AV_CPU_FLAG_MMXEXT = 2;
    public static final int AV_CPU_FLAG_NEON = 32;
    public static final int AV_CPU_FLAG_POWER8 = 4;
    public static final int AV_CPU_FLAG_SETEND = 65536;
    public static final int AV_CPU_FLAG_SSE = 8;
    public static final int AV_CPU_FLAG_SSE2 = 16;
    public static final int AV_CPU_FLAG_SSE2SLOW = 1073741824;
    public static final int AV_CPU_FLAG_SSE3 = 64;
    public static final int AV_CPU_FLAG_SSE3SLOW = 536870912;
    public static final int AV_CPU_FLAG_SSE4 = 256;
    public static final int AV_CPU_FLAG_SSE42 = 512;
    public static final int AV_CPU_FLAG_SSSE3 = 128;
    public static final int AV_CPU_FLAG_SSSE3SLOW = 67108864;
    public static final int AV_CPU_FLAG_VFP = 8;
    public static final int AV_CPU_FLAG_VFPV3 = 16;
    public static final int AV_CPU_FLAG_VFP_VM = 128;
    public static final int AV_CPU_FLAG_VSX = 2;
    public static final int AV_CPU_FLAG_XOP = 1024;
    public static final int AV_CRC_16_ANSI = 1;
    public static final int AV_CRC_16_ANSI_LE = 5;
    public static final int AV_CRC_16_CCITT = 2;
    public static final int AV_CRC_24_IEEE = 6;
    public static final int AV_CRC_32_IEEE = 3;
    public static final int AV_CRC_32_IEEE_LE = 4;
    public static final int AV_CRC_8_ATM = 0;
    public static final int AV_CRC_8_EBU = 7;
    public static final int AV_CRC_MAX = 8;
    public static final int AV_DICT_APPEND = 32;
    public static final int AV_DICT_DONT_OVERWRITE = 16;
    public static final int AV_DICT_DONT_STRDUP_KEY = 4;
    public static final int AV_DICT_DONT_STRDUP_VAL = 8;
    public static final int AV_DICT_IGNORE_SUFFIX = 2;
    public static final int AV_DICT_MATCH_CASE = 1;
    public static final int AV_DICT_MULTIKEY = 64;
    public static final int AV_DOWNMIX_TYPE_DPLII = 3;
    public static final int AV_DOWNMIX_TYPE_LORO = 1;
    public static final int AV_DOWNMIX_TYPE_LTRT = 2;
    public static final int AV_DOWNMIX_TYPE_NB = 4;
    public static final int AV_DOWNMIX_TYPE_UNKNOWN = 0;
    public static final int AV_ERROR_MAX_STRING_SIZE = 64;
    public static final int AV_ESCAPE_FLAG_STRICT = 2;
    public static final int AV_ESCAPE_FLAG_WHITESPACE = 1;
    public static final int AV_ESCAPE_MODE_AUTO = 0;
    public static final int AV_ESCAPE_MODE_BACKSLASH = 1;
    public static final int AV_ESCAPE_MODE_QUOTE = 2;
    public static final int AV_FOURCC_MAX_STRING_SIZE = 32;
    public static final int AV_FRAME_CROP_UNALIGNED = 1;
    public static final int AV_FRAME_DATA_A53_CC = 1;
    public static final int AV_FRAME_DATA_AFD = 7;
    public static final int AV_FRAME_DATA_AUDIO_SERVICE_TYPE = 10;
    public static final int AV_FRAME_DATA_CONTENT_LIGHT_LEVEL = 14;
    public static final int AV_FRAME_DATA_DISPLAYMATRIX = 6;
    public static final int AV_FRAME_DATA_DOWNMIX_INFO = 4;
    public static final int AV_FRAME_DATA_DYNAMIC_HDR_PLUS = 19;
    public static final int AV_FRAME_DATA_GOP_TIMECODE = 12;
    public static final int AV_FRAME_DATA_ICC_PROFILE = 15;
    public static final int AV_FRAME_DATA_MASTERING_DISPLAY_METADATA = 11;
    public static final int AV_FRAME_DATA_MATRIXENCODING = 3;
    public static final int AV_FRAME_DATA_MOTION_VECTORS = 8;
    public static final int AV_FRAME_DATA_PANSCAN = 0;
    public static final int AV_FRAME_DATA_QP_TABLE_DATA = 17;
    public static final int AV_FRAME_DATA_QP_TABLE_PROPERTIES = 16;
    public static final int AV_FRAME_DATA_REGIONS_OF_INTEREST = 20;
    public static final int AV_FRAME_DATA_REPLAYGAIN = 5;
    public static final int AV_FRAME_DATA_S12M_TIMECODE = 18;
    public static final int AV_FRAME_DATA_SKIP_SAMPLES = 9;
    public static final int AV_FRAME_DATA_SPHERICAL = 13;
    public static final int AV_FRAME_DATA_STEREO3D = 2;
    public static final int AV_FRAME_DATA_VIDEO_ENC_PARAMS = 21;
    public static final int AV_HASH_MAX_SIZE = 64;
    public static final int AV_HDR_PLUS_OVERLAP_PROCESS_LAYERING = 1;
    public static final int AV_HDR_PLUS_OVERLAP_PROCESS_WEIGHTED_AVERAGING = 0;
    public static final int AV_HMAC_MD5 = 0;
    public static final int AV_HMAC_SHA1 = 1;
    public static final int AV_HMAC_SHA224 = 2;
    public static final int AV_HMAC_SHA256 = 3;
    public static final int AV_HMAC_SHA384 = 4;
    public static final int AV_HMAC_SHA512 = 5;
    public static final int AV_HWDEVICE_TYPE_CUDA = 2;
    public static final int AV_HWDEVICE_TYPE_D3D11VA = 7;
    public static final int AV_HWDEVICE_TYPE_DRM = 8;
    public static final int AV_HWDEVICE_TYPE_DXVA2 = 4;
    public static final int AV_HWDEVICE_TYPE_MEDIACODEC = 10;
    public static final int AV_HWDEVICE_TYPE_NONE = 0;
    public static final int AV_HWDEVICE_TYPE_OPENCL = 9;
    public static final int AV_HWDEVICE_TYPE_QSV = 5;
    public static final int AV_HWDEVICE_TYPE_VAAPI = 3;
    public static final int AV_HWDEVICE_TYPE_VDPAU = 1;
    public static final int AV_HWDEVICE_TYPE_VIDEOTOOLBOX = 6;
    public static final int AV_HWDEVICE_TYPE_VULKAN = 11;
    public static final int AV_HWFRAME_MAP_DIRECT = 8;
    public static final int AV_HWFRAME_MAP_OVERWRITE = 4;
    public static final int AV_HWFRAME_MAP_READ = 1;
    public static final int AV_HWFRAME_MAP_WRITE = 2;
    public static final int AV_HWFRAME_TRANSFER_DIRECTION_FROM = 0;
    public static final int AV_HWFRAME_TRANSFER_DIRECTION_TO = 1;
    public static final int AV_LOG_DEBUG = 48;
    public static final int AV_LOG_ERROR = 16;
    public static final int AV_LOG_FATAL = 8;
    public static final int AV_LOG_INFO = 32;
    public static final int AV_LOG_MAX_OFFSET = 64;
    public static final int AV_LOG_PANIC = 0;
    public static final int AV_LOG_PRINT_LEVEL = 2;
    public static final int AV_LOG_QUIET = -8;
    public static final int AV_LOG_SKIP_REPEATED = 1;
    public static final int AV_LOG_TRACE = 56;
    public static final int AV_LOG_VERBOSE = 40;
    public static final int AV_LOG_WARNING = 24;
    public static final int AV_MATRIX_ENCODING_DOLBY = 1;
    public static final int AV_MATRIX_ENCODING_DOLBYEX = 5;
    public static final int AV_MATRIX_ENCODING_DOLBYHEADPHONE = 6;
    public static final int AV_MATRIX_ENCODING_DPLII = 2;
    public static final int AV_MATRIX_ENCODING_DPLIIX = 3;
    public static final int AV_MATRIX_ENCODING_DPLIIZ = 4;
    public static final int AV_MATRIX_ENCODING_NB = 7;
    public static final int AV_MATRIX_ENCODING_NONE = 0;
    public static final long AV_NOPTS_VALUE = AV_NOPTS_VALUE();
    public static final int AV_OPT_ALLOW_NULL = 4;
    public static final int AV_OPT_FLAG_IMPLICIT_KEY = 1;
    public static final int AV_OPT_MULTI_COMPONENT_RANGE = 4096;
    public static final int AV_OPT_SEARCH_CHILDREN = 1;
    public static final int AV_OPT_SEARCH_FAKE_OBJ = 2;
    public static final int AV_OPT_SERIALIZE_OPT_FLAGS_EXACT = 2;
    public static final int AV_OPT_SERIALIZE_SKIP_DEFAULTS = 1;
    public static final int AV_OPT_TYPE_BINARY = 7;
    public static final int AV_OPT_TYPE_BOOL = 18;
    public static final int AV_OPT_TYPE_CHANNEL_LAYOUT = 17;
    public static final int AV_OPT_TYPE_COLOR = 16;
    public static final int AV_OPT_TYPE_CONST = 10;
    public static final int AV_OPT_TYPE_DICT = 8;
    public static final int AV_OPT_TYPE_DOUBLE = 3;
    public static final int AV_OPT_TYPE_DURATION = 15;
    public static final int AV_OPT_TYPE_FLAGS = 0;
    public static final int AV_OPT_TYPE_FLOAT = 4;
    public static final int AV_OPT_TYPE_IMAGE_SIZE = 11;
    public static final int AV_OPT_TYPE_INT = 1;
    public static final int AV_OPT_TYPE_INT64 = 2;
    public static final int AV_OPT_TYPE_PIXEL_FMT = 12;
    public static final int AV_OPT_TYPE_RATIONAL = 6;
    public static final int AV_OPT_TYPE_SAMPLE_FMT = 13;
    public static final int AV_OPT_TYPE_STRING = 5;
    public static final int AV_OPT_TYPE_UINT64 = 9;
    public static final int AV_OPT_TYPE_VIDEO_RATE = 14;
    public static final int AV_PICTURE_TYPE_B = 3;
    public static final int AV_PICTURE_TYPE_BI = 7;
    public static final int AV_PICTURE_TYPE_I = 1;
    public static final int AV_PICTURE_TYPE_NONE = 0;
    public static final int AV_PICTURE_TYPE_P = 2;
    public static final int AV_PICTURE_TYPE_S = 4;
    public static final int AV_PICTURE_TYPE_SI = 5;
    public static final int AV_PICTURE_TYPE_SP = 6;
    public static final int AV_PIX_FMT_0BGR = 122;
    public static final int AV_PIX_FMT_0BGR32 = AV_PIX_FMT_0BGR32();
    public static final int AV_PIX_FMT_0RGB = 120;
    public static final int AV_PIX_FMT_0RGB32 = AV_PIX_FMT_0RGB32();
    public static final int AV_PIX_FMT_ABGR = 27;
    public static final int AV_PIX_FMT_ARGB = 25;
    public static final int AV_PIX_FMT_AYUV64 = AV_PIX_FMT_AYUV64();
    public static final int AV_PIX_FMT_AYUV64BE = 159;
    public static final int AV_PIX_FMT_AYUV64LE = 158;
    public static final int AV_PIX_FMT_BAYER_BGGR16 = AV_PIX_FMT_BAYER_BGGR16();
    public static final int AV_PIX_FMT_BAYER_BGGR16BE = 146;
    public static final int AV_PIX_FMT_BAYER_BGGR16LE = 145;
    public static final int AV_PIX_FMT_BAYER_BGGR8 = 141;
    public static final int AV_PIX_FMT_BAYER_GBRG16 = AV_PIX_FMT_BAYER_GBRG16();
    public static final int AV_PIX_FMT_BAYER_GBRG16BE = 150;
    public static final int AV_PIX_FMT_BAYER_GBRG16LE = 149;
    public static final int AV_PIX_FMT_BAYER_GBRG8 = 143;
    public static final int AV_PIX_FMT_BAYER_GRBG16 = AV_PIX_FMT_BAYER_GRBG16();
    public static final int AV_PIX_FMT_BAYER_GRBG16BE = 152;
    public static final int AV_PIX_FMT_BAYER_GRBG16LE = 151;
    public static final int AV_PIX_FMT_BAYER_GRBG8 = 144;
    public static final int AV_PIX_FMT_BAYER_RGGB16 = AV_PIX_FMT_BAYER_RGGB16();
    public static final int AV_PIX_FMT_BAYER_RGGB16BE = 148;
    public static final int AV_PIX_FMT_BAYER_RGGB16LE = 147;
    public static final int AV_PIX_FMT_BAYER_RGGB8 = 142;
    public static final int AV_PIX_FMT_BGR0 = 123;
    public static final int AV_PIX_FMT_BGR24 = 3;
    public static final int AV_PIX_FMT_BGR32 = AV_PIX_FMT_BGR32();
    public static final int AV_PIX_FMT_BGR32_1 = AV_PIX_FMT_BGR32_1();
    public static final int AV_PIX_FMT_BGR4 = 18;
    public static final int AV_PIX_FMT_BGR444 = AV_PIX_FMT_BGR444();
    public static final int AV_PIX_FMT_BGR444BE = 57;
    public static final int AV_PIX_FMT_BGR444LE = 56;
    public static final int AV_PIX_FMT_BGR48 = AV_PIX_FMT_BGR48();
    public static final int AV_PIX_FMT_BGR48BE = 59;
    public static final int AV_PIX_FMT_BGR48LE = 60;
    public static final int AV_PIX_FMT_BGR4_BYTE = 19;
    public static final int AV_PIX_FMT_BGR555 = AV_PIX_FMT_BGR555();
    public static final int AV_PIX_FMT_BGR555BE = 42;
    public static final int AV_PIX_FMT_BGR555LE = 43;
    public static final int AV_PIX_FMT_BGR565 = AV_PIX_FMT_BGR565();
    public static final int AV_PIX_FMT_BGR565BE = 40;
    public static final int AV_PIX_FMT_BGR565LE = 41;
    public static final int AV_PIX_FMT_BGR8 = 17;
    public static final int AV_PIX_FMT_BGRA = 28;
    public static final int AV_PIX_FMT_BGRA64 = AV_PIX_FMT_BGRA64();
    public static final int AV_PIX_FMT_BGRA64BE = 108;
    public static final int AV_PIX_FMT_BGRA64LE = 109;
    public static final int AV_PIX_FMT_CUDA = 119;
    public static final int AV_PIX_FMT_D3D11 = 174;
    public static final int AV_PIX_FMT_D3D11VA_VLD = 118;
    public static final int AV_PIX_FMT_DRM_PRIME = 181;
    public static final int AV_PIX_FMT_DXVA2_VLD = 53;
    public static final int AV_PIX_FMT_FLAG_ALPHA = 128;
    public static final int AV_PIX_FMT_FLAG_BAYER = 256;
    public static final int AV_PIX_FMT_FLAG_BE = 1;
    public static final int AV_PIX_FMT_FLAG_BITSTREAM = 4;
    public static final int AV_PIX_FMT_FLAG_FLOAT = 512;
    public static final int AV_PIX_FMT_FLAG_HWACCEL = 8;
    public static final int AV_PIX_FMT_FLAG_PAL = 2;
    public static final int AV_PIX_FMT_FLAG_PLANAR = 16;
    public static final int AV_PIX_FMT_FLAG_PSEUDOPAL = 64;
    public static final int AV_PIX_FMT_FLAG_RGB = 32;
    public static final int AV_PIX_FMT_GBR24P = 73;
    public static final int AV_PIX_FMT_GBRAP = 113;
    public static final int AV_PIX_FMT_GBRAP10 = AV_PIX_FMT_GBRAP10();
    public static final int AV_PIX_FMT_GBRAP10BE = 165;
    public static final int AV_PIX_FMT_GBRAP10LE = 166;
    public static final int AV_PIX_FMT_GBRAP12 = AV_PIX_FMT_GBRAP12();
    public static final int AV_PIX_FMT_GBRAP12BE = 163;
    public static final int AV_PIX_FMT_GBRAP12LE = 164;
    public static final int AV_PIX_FMT_GBRAP16 = AV_PIX_FMT_GBRAP16();
    public static final int AV_PIX_FMT_GBRAP16BE = 114;
    public static final int AV_PIX_FMT_GBRAP16LE = 115;
    public static final int AV_PIX_FMT_GBRAPF32 = AV_PIX_FMT_GBRAPF32();
    public static final int AV_PIX_FMT_GBRAPF32BE = 179;
    public static final int AV_PIX_FMT_GBRAPF32LE = 180;
    public static final int AV_PIX_FMT_GBRP = 73;
    public static final int AV_PIX_FMT_GBRP10 = AV_PIX_FMT_GBRP10();
    public static final int AV_PIX_FMT_GBRP10BE = 76;
    public static final int AV_PIX_FMT_GBRP10LE = 77;
    public static final int AV_PIX_FMT_GBRP12 = AV_PIX_FMT_GBRP12();
    public static final int AV_PIX_FMT_GBRP12BE = 136;
    public static final int AV_PIX_FMT_GBRP12LE = 137;
    public static final int AV_PIX_FMT_GBRP14 = AV_PIX_FMT_GBRP14();
    public static final int AV_PIX_FMT_GBRP14BE = 138;
    public static final int AV_PIX_FMT_GBRP14LE = 139;
    public static final int AV_PIX_FMT_GBRP16 = AV_PIX_FMT_GBRP16();
    public static final int AV_PIX_FMT_GBRP16BE = 78;
    public static final int AV_PIX_FMT_GBRP16LE = 79;
    public static final int AV_PIX_FMT_GBRP9 = AV_PIX_FMT_GBRP9();
    public static final int AV_PIX_FMT_GBRP9BE = 74;
    public static final int AV_PIX_FMT_GBRP9LE = 75;
    public static final int AV_PIX_FMT_GBRPF32 = AV_PIX_FMT_GBRPF32();
    public static final int AV_PIX_FMT_GBRPF32BE = 177;
    public static final int AV_PIX_FMT_GBRPF32LE = 178;
    public static final int AV_PIX_FMT_GRAY10 = AV_PIX_FMT_GRAY10();
    public static final int AV_PIX_FMT_GRAY10BE = 170;
    public static final int AV_PIX_FMT_GRAY10LE = 171;
    public static final int AV_PIX_FMT_GRAY12 = AV_PIX_FMT_GRAY12();
    public static final int AV_PIX_FMT_GRAY12BE = 168;
    public static final int AV_PIX_FMT_GRAY12LE = 169;
    public static final int AV_PIX_FMT_GRAY14 = AV_PIX_FMT_GRAY14();
    public static final int AV_PIX_FMT_GRAY14BE = 183;
    public static final int AV_PIX_FMT_GRAY14LE = 184;
    public static final int AV_PIX_FMT_GRAY16 = AV_PIX_FMT_GRAY16();
    public static final int AV_PIX_FMT_GRAY16BE = 29;
    public static final int AV_PIX_FMT_GRAY16LE = 30;
    public static final int AV_PIX_FMT_GRAY8 = 8;
    public static final int AV_PIX_FMT_GRAY8A = 58;
    public static final int AV_PIX_FMT_GRAY9 = AV_PIX_FMT_GRAY9();
    public static final int AV_PIX_FMT_GRAY9BE = 175;
    public static final int AV_PIX_FMT_GRAY9LE = 176;
    public static final int AV_PIX_FMT_GRAYF32 = AV_PIX_FMT_GRAYF32();
    public static final int AV_PIX_FMT_GRAYF32BE = 185;
    public static final int AV_PIX_FMT_GRAYF32LE = 186;
    public static final int AV_PIX_FMT_MEDIACODEC = 167;
    public static final int AV_PIX_FMT_MMAL = 117;
    public static final int AV_PIX_FMT_MONOBLACK = 10;
    public static final int AV_PIX_FMT_MONOWHITE = 9;
    public static final int AV_PIX_FMT_NB = 196;
    public static final int AV_PIX_FMT_NONE = -1;
    public static final int AV_PIX_FMT_NV12 = 23;
    public static final int AV_PIX_FMT_NV16 = 103;
    public static final int AV_PIX_FMT_NV20 = AV_PIX_FMT_NV20();
    public static final int AV_PIX_FMT_NV20BE = 105;
    public static final int AV_PIX_FMT_NV20LE = 104;
    public static final int AV_PIX_FMT_NV21 = 24;
    public static final int AV_PIX_FMT_NV24 = 191;
    public static final int AV_PIX_FMT_NV42 = 192;
    public static final int AV_PIX_FMT_OPENCL = 182;
    public static final int AV_PIX_FMT_P010 = AV_PIX_FMT_P010();
    public static final int AV_PIX_FMT_P010BE = 162;
    public static final int AV_PIX_FMT_P010LE = 161;
    public static final int AV_PIX_FMT_P016 = AV_PIX_FMT_P016();
    public static final int AV_PIX_FMT_P016BE = 173;
    public static final int AV_PIX_FMT_P016LE = 172;
    public static final int AV_PIX_FMT_PAL8 = 11;
    public static final int AV_PIX_FMT_QSV = 116;
    public static final int AV_PIX_FMT_RGB0 = 121;
    public static final int AV_PIX_FMT_RGB24 = 2;
    public static final int AV_PIX_FMT_RGB32 = AV_PIX_FMT_RGB32();
    public static final int AV_PIX_FMT_RGB32_1 = AV_PIX_FMT_RGB32_1();
    public static final int AV_PIX_FMT_RGB4 = 21;
    public static final int AV_PIX_FMT_RGB444 = AV_PIX_FMT_RGB444();
    public static final int AV_PIX_FMT_RGB444BE = 55;
    public static final int AV_PIX_FMT_RGB444LE = 54;
    public static final int AV_PIX_FMT_RGB48 = AV_PIX_FMT_RGB48();
    public static final int AV_PIX_FMT_RGB48BE = 34;
    public static final int AV_PIX_FMT_RGB48LE = 35;
    public static final int AV_PIX_FMT_RGB4_BYTE = 22;
    public static final int AV_PIX_FMT_RGB555 = AV_PIX_FMT_RGB555();
    public static final int AV_PIX_FMT_RGB555BE = 38;
    public static final int AV_PIX_FMT_RGB555LE = 39;
    public static final int AV_PIX_FMT_RGB565 = AV_PIX_FMT_RGB565();
    public static final int AV_PIX_FMT_RGB565BE = 36;
    public static final int AV_PIX_FMT_RGB565LE = 37;
    public static final int AV_PIX_FMT_RGB8 = 20;
    public static final int AV_PIX_FMT_RGBA = 26;
    public static final int AV_PIX_FMT_RGBA64 = AV_PIX_FMT_RGBA64();
    public static final int AV_PIX_FMT_RGBA64BE = 106;
    public static final int AV_PIX_FMT_RGBA64LE = 107;
    public static final int AV_PIX_FMT_UYVY422 = 15;
    public static final int AV_PIX_FMT_UYYVYY411 = 16;
    public static final int AV_PIX_FMT_VAAPI = 46;
    public static final int AV_PIX_FMT_VAAPI_IDCT = 45;
    public static final int AV_PIX_FMT_VAAPI_MOCO = 44;
    public static final int AV_PIX_FMT_VAAPI_VLD = 46;
    public static final int AV_PIX_FMT_VDPAU = 100;
    public static final int AV_PIX_FMT_VIDEOTOOLBOX = 160;
    public static final int AV_PIX_FMT_VULKAN = 193;
    public static final int AV_PIX_FMT_XVMC = 153;
    public static final int AV_PIX_FMT_XYZ12 = AV_PIX_FMT_XYZ12();
    public static final int AV_PIX_FMT_XYZ12BE = 102;
    public static final int AV_PIX_FMT_XYZ12LE = 101;
    public static final int AV_PIX_FMT_Y210 = AV_PIX_FMT_Y210();
    public static final int AV_PIX_FMT_Y210BE = 194;
    public static final int AV_PIX_FMT_Y210LE = 195;
    public static final int AV_PIX_FMT_Y400A = 58;
    public static final int AV_PIX_FMT_YA16 = AV_PIX_FMT_YA16();
    public static final int AV_PIX_FMT_YA16BE = 111;
    public static final int AV_PIX_FMT_YA16LE = 112;
    public static final int AV_PIX_FMT_YA8 = 58;
    public static final int AV_PIX_FMT_YUV410P = 6;
    public static final int AV_PIX_FMT_YUV411P = 7;
    public static final int AV_PIX_FMT_YUV420P = 0;
    public static final int AV_PIX_FMT_YUV420P10 = AV_PIX_FMT_YUV420P10();
    public static final int AV_PIX_FMT_YUV420P10BE = 63;
    public static final int AV_PIX_FMT_YUV420P10LE = 64;
    public static final int AV_PIX_FMT_YUV420P12 = AV_PIX_FMT_YUV420P12();
    public static final int AV_PIX_FMT_YUV420P12BE = 124;
    public static final int AV_PIX_FMT_YUV420P12LE = 125;
    public static final int AV_PIX_FMT_YUV420P14 = AV_PIX_FMT_YUV420P14();
    public static final int AV_PIX_FMT_YUV420P14BE = 126;
    public static final int AV_PIX_FMT_YUV420P14LE = 127;
    public static final int AV_PIX_FMT_YUV420P16 = AV_PIX_FMT_YUV420P16();
    public static final int AV_PIX_FMT_YUV420P16BE = 48;
    public static final int AV_PIX_FMT_YUV420P16LE = 47;
    public static final int AV_PIX_FMT_YUV420P9 = AV_PIX_FMT_YUV420P9();
    public static final int AV_PIX_FMT_YUV420P9BE = 61;
    public static final int AV_PIX_FMT_YUV420P9LE = 62;
    public static final int AV_PIX_FMT_YUV422P = 4;
    public static final int AV_PIX_FMT_YUV422P10 = AV_PIX_FMT_YUV422P10();
    public static final int AV_PIX_FMT_YUV422P10BE = 65;
    public static final int AV_PIX_FMT_YUV422P10LE = 66;
    public static final int AV_PIX_FMT_YUV422P12 = AV_PIX_FMT_YUV422P12();
    public static final int AV_PIX_FMT_YUV422P12BE = 128;
    public static final int AV_PIX_FMT_YUV422P12LE = 129;
    public static final int AV_PIX_FMT_YUV422P14 = AV_PIX_FMT_YUV422P14();
    public static final int AV_PIX_FMT_YUV422P14BE = 130;
    public static final int AV_PIX_FMT_YUV422P14LE = 131;
    public static final int AV_PIX_FMT_YUV422P16 = AV_PIX_FMT_YUV422P16();
    public static final int AV_PIX_FMT_YUV422P16BE = 50;
    public static final int AV_PIX_FMT_YUV422P16LE = 49;
    public static final int AV_PIX_FMT_YUV422P9 = AV_PIX_FMT_YUV422P9();
    public static final int AV_PIX_FMT_YUV422P9BE = 71;
    public static final int AV_PIX_FMT_YUV422P9LE = 72;
    public static final int AV_PIX_FMT_YUV440P = 31;
    public static final int AV_PIX_FMT_YUV440P10 = AV_PIX_FMT_YUV440P10();
    public static final int AV_PIX_FMT_YUV440P10BE = 155;
    public static final int AV_PIX_FMT_YUV440P10LE = 154;
    public static final int AV_PIX_FMT_YUV440P12 = AV_PIX_FMT_YUV440P12();
    public static final int AV_PIX_FMT_YUV440P12BE = 157;
    public static final int AV_PIX_FMT_YUV440P12LE = 156;
    public static final int AV_PIX_FMT_YUV444P = 5;
    public static final int AV_PIX_FMT_YUV444P10 = AV_PIX_FMT_YUV444P10();
    public static final int AV_PIX_FMT_YUV444P10BE = 69;
    public static final int AV_PIX_FMT_YUV444P10LE = 70;
    public static final int AV_PIX_FMT_YUV444P12 = AV_PIX_FMT_YUV444P12();
    public static final int AV_PIX_FMT_YUV444P12BE = 132;
    public static final int AV_PIX_FMT_YUV444P12LE = 133;
    public static final int AV_PIX_FMT_YUV444P14 = AV_PIX_FMT_YUV444P14();
    public static final int AV_PIX_FMT_YUV444P14BE = 134;
    public static final int AV_PIX_FMT_YUV444P14LE = 135;
    public static final int AV_PIX_FMT_YUV444P16 = AV_PIX_FMT_YUV444P16();
    public static final int AV_PIX_FMT_YUV444P16BE = 52;
    public static final int AV_PIX_FMT_YUV444P16LE = 51;
    public static final int AV_PIX_FMT_YUV444P9 = AV_PIX_FMT_YUV444P9();
    public static final int AV_PIX_FMT_YUV444P9BE = 67;
    public static final int AV_PIX_FMT_YUV444P9LE = 68;
    public static final int AV_PIX_FMT_YUVA420P = 33;
    public static final int AV_PIX_FMT_YUVA420P10 = AV_PIX_FMT_YUVA420P10();
    public static final int AV_PIX_FMT_YUVA420P10BE = 88;
    public static final int AV_PIX_FMT_YUVA420P10LE = 89;
    public static final int AV_PIX_FMT_YUVA420P16 = AV_PIX_FMT_YUVA420P16();
    public static final int AV_PIX_FMT_YUVA420P16BE = 94;
    public static final int AV_PIX_FMT_YUVA420P16LE = 95;
    public static final int AV_PIX_FMT_YUVA420P9 = AV_PIX_FMT_YUVA420P9();
    public static final int AV_PIX_FMT_YUVA420P9BE = 82;
    public static final int AV_PIX_FMT_YUVA420P9LE = 83;
    public static final int AV_PIX_FMT_YUVA422P = 80;
    public static final int AV_PIX_FMT_YUVA422P10 = AV_PIX_FMT_YUVA422P10();
    public static final int AV_PIX_FMT_YUVA422P10BE = 90;
    public static final int AV_PIX_FMT_YUVA422P10LE = 91;
    public static final int AV_PIX_FMT_YUVA422P12 = AV_PIX_FMT_YUVA422P12();
    public static final int AV_PIX_FMT_YUVA422P12BE = 187;
    public static final int AV_PIX_FMT_YUVA422P12LE = 188;
    public static final int AV_PIX_FMT_YUVA422P16 = AV_PIX_FMT_YUVA422P16();
    public static final int AV_PIX_FMT_YUVA422P16BE = 96;
    public static final int AV_PIX_FMT_YUVA422P16LE = 97;
    public static final int AV_PIX_FMT_YUVA422P9 = AV_PIX_FMT_YUVA422P9();
    public static final int AV_PIX_FMT_YUVA422P9BE = 84;
    public static final int AV_PIX_FMT_YUVA422P9LE = 85;
    public static final int AV_PIX_FMT_YUVA444P = 81;
    public static final int AV_PIX_FMT_YUVA444P10 = AV_PIX_FMT_YUVA444P10();
    public static final int AV_PIX_FMT_YUVA444P10BE = 92;
    public static final int AV_PIX_FMT_YUVA444P10LE = 93;
    public static final int AV_PIX_FMT_YUVA444P12 = AV_PIX_FMT_YUVA444P12();
    public static final int AV_PIX_FMT_YUVA444P12BE = 189;
    public static final int AV_PIX_FMT_YUVA444P12LE = 190;
    public static final int AV_PIX_FMT_YUVA444P16 = AV_PIX_FMT_YUVA444P16();
    public static final int AV_PIX_FMT_YUVA444P16BE = 98;
    public static final int AV_PIX_FMT_YUVA444P16LE = 99;
    public static final int AV_PIX_FMT_YUVA444P9 = AV_PIX_FMT_YUVA444P9();
    public static final int AV_PIX_FMT_YUVA444P9BE = 86;
    public static final int AV_PIX_FMT_YUVA444P9LE = 87;
    public static final int AV_PIX_FMT_YUVJ411P = 140;
    public static final int AV_PIX_FMT_YUVJ420P = 12;
    public static final int AV_PIX_FMT_YUVJ422P = 13;
    public static final int AV_PIX_FMT_YUVJ440P = 32;
    public static final int AV_PIX_FMT_YUVJ444P = 14;
    public static final int AV_PIX_FMT_YUYV422 = 1;
    public static final int AV_PIX_FMT_YVYU422 = 110;
    public static final int AV_ROUND_DOWN = 2;
    public static final int AV_ROUND_INF = 1;
    public static final int AV_ROUND_NEAR_INF = 5;
    public static final int AV_ROUND_PASS_MINMAX = 8192;
    public static final int AV_ROUND_UP = 3;
    public static final int AV_ROUND_ZERO = 0;
    public static final int AV_SAMPLE_FMT_DBL = 4;
    public static final int AV_SAMPLE_FMT_DBLP = 9;
    public static final int AV_SAMPLE_FMT_FLT = 3;
    public static final int AV_SAMPLE_FMT_FLTP = 8;
    public static final int AV_SAMPLE_FMT_NB = 12;
    public static final int AV_SAMPLE_FMT_NONE = -1;
    public static final int AV_SAMPLE_FMT_S16 = 1;
    public static final int AV_SAMPLE_FMT_S16P = 6;
    public static final int AV_SAMPLE_FMT_S32 = 2;
    public static final int AV_SAMPLE_FMT_S32P = 7;
    public static final int AV_SAMPLE_FMT_S64 = 10;
    public static final int AV_SAMPLE_FMT_S64P = 11;
    public static final int AV_SAMPLE_FMT_U8 = 0;
    public static final int AV_SAMPLE_FMT_U8P = 5;
    public static final int AV_SPHERICAL_CUBEMAP = 1;
    public static final int AV_SPHERICAL_EQUIRECTANGULAR = 0;
    public static final int AV_SPHERICAL_EQUIRECTANGULAR_TILE = 2;
    public static final int AV_STEREO3D_2D = 0;
    public static final int AV_STEREO3D_CHECKERBOARD = 4;
    public static final int AV_STEREO3D_COLUMNS = 7;
    public static final int AV_STEREO3D_FLAG_INVERT = 1;
    public static final int AV_STEREO3D_FRAMESEQUENCE = 3;
    public static final int AV_STEREO3D_LINES = 6;
    public static final int AV_STEREO3D_SIDEBYSIDE = 1;
    public static final int AV_STEREO3D_SIDEBYSIDE_QUINCUNX = 5;
    public static final int AV_STEREO3D_TOPBOTTOM = 2;
    public static final int AV_STEREO3D_VIEW_LEFT = 1;
    public static final int AV_STEREO3D_VIEW_PACKED = 0;
    public static final int AV_STEREO3D_VIEW_RIGHT = 2;
    public static final int AV_THREAD_MESSAGE_NONBLOCK = 1;
    public static final int AV_TIMECODE_FLAG_24HOURSMAX = 2;
    public static final int AV_TIMECODE_FLAG_ALLOWNEGATIVE = 4;
    public static final int AV_TIMECODE_FLAG_DROPFRAME = 1;
    public static final int AV_TIMECODE_STR_SIZE = 23;
    public static final int AV_TIME_BASE = 1000000;
    public static final int AV_TS_MAX_STRING_SIZE = 32;
    public static final int AV_TX_DOUBLE_FFT = 2;
    public static final int AV_TX_DOUBLE_MDCT = 3;
    public static final int AV_TX_FLOAT_FFT = 0;
    public static final int AV_TX_FLOAT_MDCT = 1;
    public static final int AV_TX_INT32_FFT = 4;
    public static final int AV_TX_INT32_MDCT = 5;
    public static final int AV_UTF8_FLAG_ACCEPT_ALL = 7;
    public static final int AV_UTF8_FLAG_ACCEPT_INVALID_BIG_CODES = 1;
    public static final int AV_UTF8_FLAG_ACCEPT_NON_CHARACTERS = 2;
    public static final int AV_UTF8_FLAG_ACCEPT_SURROGATES = 4;
    public static final int AV_UTF8_FLAG_EXCLUDE_XML_INVALID_CONTROL_CODES = 8;
    public static final String FFMPEG_VERSION = "4.3.1";
    public static final int FF_LAMBDA_MAX = 32767;
    public static final int FF_LAMBDA_SCALE = 128;
    public static final int FF_LAMBDA_SHIFT = 7;
    public static final int FF_LOSS_ALPHA = 8;
    public static final int FF_LOSS_CHROMA = 32;
    public static final int FF_LOSS_COLORQUANT = 16;
    public static final int FF_LOSS_COLORSPACE = 4;
    public static final int FF_LOSS_DEPTH = 2;
    public static final int FF_LOSS_RESOLUTION = 1;
    public static final int FF_QP2LAMBDA = 118;
    public static final int FF_QUALITY_SCALE = 128;
    public static final double INFINITY = INFINITY();
    public static final double M_E = 2.718281828459045d;
    public static final double M_LN10 = 2.302585092994046d;
    public static final double M_LN2 = 0.6931471805599453d;
    public static final double M_LOG2_10 = 3.321928094887362d;
    public static final double M_PHI = 1.618033988749895d;
    public static final double M_PI = 3.141592653589793d;
    public static final double M_PI_2 = 1.5707963267948966d;
    public static final double M_SQRT1_2 = 0.7071067811865476d;
    public static final double M_SQRT2 = 1.4142135623730951d;
    public static final double NAN = NAN();

    @MemberGetter
    public static native int AVERROR_BSF_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_BUFFER_TOO_SMALL();

    @MemberGetter
    public static native int AVERROR_BUG();

    @MemberGetter
    public static native int AVERROR_BUG2();

    @MemberGetter
    public static native int AVERROR_DECODER_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_DEMUXER_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_ENCODER_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_EOF();

    @MemberGetter
    public static native int AVERROR_EXIT();

    @MemberGetter
    public static native int AVERROR_EXTERNAL();

    @MemberGetter
    public static native int AVERROR_FILTER_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_HTTP_BAD_REQUEST();

    @MemberGetter
    public static native int AVERROR_HTTP_FORBIDDEN();

    @MemberGetter
    public static native int AVERROR_HTTP_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_HTTP_OTHER_4XX();

    @MemberGetter
    public static native int AVERROR_HTTP_SERVER_ERROR();

    @MemberGetter
    public static native int AVERROR_HTTP_UNAUTHORIZED();

    @MemberGetter
    public static native int AVERROR_INVALIDDATA();

    @MemberGetter
    public static native int AVERROR_MUXER_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_OPTION_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_PATCHWELCOME();

    @MemberGetter
    public static native int AVERROR_PROTOCOL_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_STREAM_NOT_FOUND();

    @MemberGetter
    public static native int AVERROR_UNKNOWN();

    @MemberGetter
    @Cast({"int64_t"})
    public static native long AV_NOPTS_VALUE();

    @MemberGetter
    public static native int AV_PIX_FMT_0BGR32();

    @MemberGetter
    public static native int AV_PIX_FMT_0RGB32();

    @MemberGetter
    public static native int AV_PIX_FMT_AYUV64();

    @MemberGetter
    public static native int AV_PIX_FMT_BAYER_BGGR16();

    @MemberGetter
    public static native int AV_PIX_FMT_BAYER_GBRG16();

    @MemberGetter
    public static native int AV_PIX_FMT_BAYER_GRBG16();

    @MemberGetter
    public static native int AV_PIX_FMT_BAYER_RGGB16();

    @MemberGetter
    public static native int AV_PIX_FMT_BGR32();

    @MemberGetter
    public static native int AV_PIX_FMT_BGR32_1();

    @MemberGetter
    public static native int AV_PIX_FMT_BGR444();

    @MemberGetter
    public static native int AV_PIX_FMT_BGR48();

    @MemberGetter
    public static native int AV_PIX_FMT_BGR555();

    @MemberGetter
    public static native int AV_PIX_FMT_BGR565();

    @MemberGetter
    public static native int AV_PIX_FMT_BGRA64();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRAP10();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRAP12();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRAP16();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRAPF32();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRP10();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRP12();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRP14();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRP16();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRP9();

    @MemberGetter
    public static native int AV_PIX_FMT_GBRPF32();

    @MemberGetter
    public static native int AV_PIX_FMT_GRAY10();

    @MemberGetter
    public static native int AV_PIX_FMT_GRAY12();

    @MemberGetter
    public static native int AV_PIX_FMT_GRAY14();

    @MemberGetter
    public static native int AV_PIX_FMT_GRAY16();

    @MemberGetter
    public static native int AV_PIX_FMT_GRAY9();

    @MemberGetter
    public static native int AV_PIX_FMT_GRAYF32();

    @MemberGetter
    public static native int AV_PIX_FMT_NV20();

    @MemberGetter
    public static native int AV_PIX_FMT_P010();

    @MemberGetter
    public static native int AV_PIX_FMT_P016();

    @MemberGetter
    public static native int AV_PIX_FMT_RGB32();

    @MemberGetter
    public static native int AV_PIX_FMT_RGB32_1();

    @MemberGetter
    public static native int AV_PIX_FMT_RGB444();

    @MemberGetter
    public static native int AV_PIX_FMT_RGB48();

    @MemberGetter
    public static native int AV_PIX_FMT_RGB555();

    @MemberGetter
    public static native int AV_PIX_FMT_RGB565();

    @MemberGetter
    public static native int AV_PIX_FMT_RGBA64();

    @MemberGetter
    public static native int AV_PIX_FMT_XYZ12();

    @MemberGetter
    public static native int AV_PIX_FMT_Y210();

    @MemberGetter
    public static native int AV_PIX_FMT_YA16();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV420P10();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV420P12();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV420P14();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV420P16();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV420P9();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV422P10();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV422P12();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV422P14();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV422P16();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV422P9();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV440P10();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV440P12();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV444P10();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV444P12();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV444P14();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV444P16();

    @MemberGetter
    public static native int AV_PIX_FMT_YUV444P9();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA420P10();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA420P16();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA420P9();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA422P10();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA422P12();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA422P16();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA422P9();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA444P10();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA444P12();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA444P16();

    @MemberGetter
    public static native int AV_PIX_FMT_YUVA444P9();

    @MemberGetter
    public static native double INFINITY();

    public static native int MKBETAG(@Cast({"char"}) byte b, @Cast({"char"}) byte b2, @Cast({"char"}) byte b3, @Cast({"char"}) byte b4);

    public static native int MKTAG(@Cast({"char"}) byte b, @Cast({"char"}) byte b2, @Cast({"char"}) byte b3, @Cast({"char"}) byte b4);

    @MemberGetter
    public static native double NAN();

    @NoException
    @Const
    @ByVal
    public static native AVRational av_add_q(@ByVal AVRational aVRational, @ByVal AVRational aVRational2);

    @NoException
    @Cast({"int64_t"})
    public static native long av_add_stable(@ByVal AVRational aVRational, @Cast({"int64_t"}) long j, @ByVal AVRational aVRational2, @Cast({"int64_t"}) long j2);

    @NoException
    @Cast({"unsigned long"})
    public static native long av_adler32_update(@Cast({"unsigned long"}) long j, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    @Cast({"unsigned long"})
    public static native long av_adler32_update(@Cast({"unsigned long"}) long j, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

    @NoException
    @Cast({"unsigned long"})
    public static native long av_adler32_update(@Cast({"unsigned long"}) long j, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"unsigned int"}) int i);

    @NoException
    public static native AVAES av_aes_alloc();

    @NoException
    public static native void av_aes_crypt(AVAES avaes, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_aes_crypt(AVAES avaes, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_aes_crypt(AVAES avaes, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native AVAESCTR av_aes_ctr_alloc();

    @NoException
    public static native void av_aes_ctr_crypt(AVAESCTR avaesctr, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i);

    @NoException
    public static native void av_aes_ctr_crypt(AVAESCTR avaesctr, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i);

    @NoException
    public static native void av_aes_ctr_crypt(AVAESCTR avaesctr, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i);

    @NoException
    public static native void av_aes_ctr_free(AVAESCTR avaesctr);

    @NoException
    @Cast({"const uint8_t*"})
    public static native BytePointer av_aes_ctr_get_iv(AVAESCTR avaesctr);

    @NoException
    public static native void av_aes_ctr_increment_iv(AVAESCTR avaesctr);

    @NoException
    public static native int av_aes_ctr_init(AVAESCTR avaesctr, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native int av_aes_ctr_init(AVAESCTR avaesctr, @Cast({"const uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native int av_aes_ctr_init(AVAESCTR avaesctr, @Cast({"const uint8_t*"}) byte[] bArr);

    @NoException
    public static native void av_aes_ctr_set_full_iv(AVAESCTR avaesctr, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_aes_ctr_set_full_iv(AVAESCTR avaesctr, @Cast({"const uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_aes_ctr_set_full_iv(AVAESCTR avaesctr, @Cast({"const uint8_t*"}) byte[] bArr);

    @NoException
    public static native void av_aes_ctr_set_iv(AVAESCTR avaesctr, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_aes_ctr_set_iv(AVAESCTR avaesctr, @Cast({"const uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_aes_ctr_set_iv(AVAESCTR avaesctr, @Cast({"const uint8_t*"}) byte[] bArr);

    @NoException
    public static native void av_aes_ctr_set_random_iv(AVAESCTR avaesctr);

    @NoException
    public static native int av_aes_init(AVAES avaes, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native int av_aes_init(AVAES avaes, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i, int i2);

    @NoException
    public static native int av_aes_init(AVAES avaes, @Cast({"const uint8_t*"}) byte[] bArr, int i, int i2);

    @MemberGetter
    public static native int av_aes_size();

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_append_path_component(String str, String str2);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_append_path_component(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_asprintf(String str);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_asprintf(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native AVAudioFifo av_audio_fifo_alloc(@Cast({"AVSampleFormat"}) int i, int i2, int i3);

    @NoException
    public static native int av_audio_fifo_drain(AVAudioFifo aVAudioFifo, int i);

    @NoException
    public static native void av_audio_fifo_free(AVAudioFifo aVAudioFifo);

    @NoException
    public static native int av_audio_fifo_peek(AVAudioFifo aVAudioFifo, @ByPtrPtr @Cast({"void**"}) Pointer pointer, int i);

    @NoException
    public static native int av_audio_fifo_peek(AVAudioFifo aVAudioFifo, @Cast({"void**"}) PointerPointer pointerPointer, int i);

    @NoException
    public static native int av_audio_fifo_peek_at(AVAudioFifo aVAudioFifo, @ByPtrPtr @Cast({"void**"}) Pointer pointer, int i, int i2);

    @NoException
    public static native int av_audio_fifo_peek_at(AVAudioFifo aVAudioFifo, @Cast({"void**"}) PointerPointer pointerPointer, int i, int i2);

    @NoException
    public static native int av_audio_fifo_read(AVAudioFifo aVAudioFifo, @ByPtrPtr @Cast({"void**"}) Pointer pointer, int i);

    @NoException
    public static native int av_audio_fifo_read(AVAudioFifo aVAudioFifo, @Cast({"void**"}) PointerPointer pointerPointer, int i);

    @NoException
    public static native int av_audio_fifo_realloc(AVAudioFifo aVAudioFifo, int i);

    @NoException
    public static native void av_audio_fifo_reset(AVAudioFifo aVAudioFifo);

    @NoException
    public static native int av_audio_fifo_size(AVAudioFifo aVAudioFifo);

    @NoException
    public static native int av_audio_fifo_space(AVAudioFifo aVAudioFifo);

    @NoException
    public static native int av_audio_fifo_write(AVAudioFifo aVAudioFifo, @ByPtrPtr @Cast({"void**"}) Pointer pointer, int i);

    @NoException
    public static native int av_audio_fifo_write(AVAudioFifo aVAudioFifo, @Cast({"void**"}) PointerPointer pointerPointer, int i);

    @NoException
    public static native int av_base64_decode(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, String str, int i);

    @NoException
    public static native int av_base64_decode(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_base64_decode(@Cast({"uint8_t*"}) BytePointer bytePointer, String str, int i);

    @NoException
    public static native int av_base64_decode(@Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i);

    @NoException
    public static native int av_base64_decode(@Cast({"uint8_t*"}) byte[] bArr, String str, int i);

    @NoException
    public static native int av_base64_decode(@Cast({"uint8_t*"}) byte[] bArr, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_base64_encode(@Cast({"char*"}) ByteBuffer byteBuffer, int i, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i2);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_base64_encode(@Cast({"char*"}) BytePointer bytePointer, int i, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i2);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_base64_encode(@Cast({"char*"}) byte[] bArr, int i, @Cast({"const uint8_t*"}) byte[] bArr2, int i2);

    @NoException
    public static native String av_basename(String str);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_basename(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native AVBlowfish av_blowfish_alloc();

    @NoException
    public static native void av_blowfish_crypt(AVBlowfish aVBlowfish, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_blowfish_crypt(AVBlowfish aVBlowfish, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_blowfish_crypt(AVBlowfish aVBlowfish, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native void av_blowfish_crypt_ecb(AVBlowfish aVBlowfish, @Cast({"uint32_t*"}) IntBuffer intBuffer, @Cast({"uint32_t*"}) IntBuffer intBuffer2, int i);

    @NoException
    public static native void av_blowfish_crypt_ecb(AVBlowfish aVBlowfish, @Cast({"uint32_t*"}) IntPointer intPointer, @Cast({"uint32_t*"}) IntPointer intPointer2, int i);

    @NoException
    public static native void av_blowfish_crypt_ecb(AVBlowfish aVBlowfish, @Cast({"uint32_t*"}) int[] iArr, @Cast({"uint32_t*"}) int[] iArr2, int i);

    @NoException
    public static native void av_blowfish_init(AVBlowfish aVBlowfish, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void av_blowfish_init(AVBlowfish aVBlowfish, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void av_blowfish_init(AVBlowfish aVBlowfish, @Cast({"const uint8_t*"}) byte[] bArr, int i);

    @NoException
    public static native void av_bmg_get(AVLFG avlfg, DoubleBuffer doubleBuffer);

    @NoException
    public static native void av_bmg_get(AVLFG avlfg, DoublePointer doublePointer);

    @NoException
    public static native void av_bmg_get(AVLFG avlfg, double[] dArr);

    @NoException
    public static native void av_bprint_append_data(AVBPrint aVBPrint, String str, @Cast({"unsigned"}) int i);

    @NoException
    public static native void av_bprint_append_data(AVBPrint aVBPrint, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"unsigned"}) int i);

    @NoException
    public static native void av_bprint_channel_layout(AVBPrint aVBPrint, int i, @Cast({"uint64_t"}) long j);

    @NoException
    public static native void av_bprint_chars(AVBPrint aVBPrint, @Cast({"char"}) byte b, @Cast({"unsigned"}) int i);

    @NoException
    public static native void av_bprint_clear(AVBPrint aVBPrint);

    @NoException
    public static native void av_bprint_escape(AVBPrint aVBPrint, String str, String str2, @Cast({"AVEscapeMode"}) int i, int i2);

    @NoException
    public static native void av_bprint_escape(AVBPrint aVBPrint, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"AVEscapeMode"}) int i, int i2);

    @NoException
    public static native int av_bprint_finalize(AVBPrint aVBPrint, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int av_bprint_finalize(AVBPrint aVBPrint, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer);

    @NoException
    public static native int av_bprint_finalize(AVBPrint aVBPrint, @Cast({"char**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_bprint_finalize(AVBPrint aVBPrint, @ByPtrPtr @Cast({"char**"}) byte[] bArr);

    @NoException
    public static native void av_bprint_get_buffer(AVBPrint aVBPrint, @Cast({"unsigned"}) int i, @ByPtrPtr @Cast({"unsigned char**"}) ByteBuffer byteBuffer, @Cast({"unsigned*"}) IntBuffer intBuffer);

    @NoException
    public static native void av_bprint_get_buffer(AVBPrint aVBPrint, @Cast({"unsigned"}) int i, @ByPtrPtr @Cast({"unsigned char**"}) BytePointer bytePointer, @Cast({"unsigned*"}) IntPointer intPointer);

    @NoException
    public static native void av_bprint_get_buffer(AVBPrint aVBPrint, @Cast({"unsigned"}) int i, @Cast({"unsigned char**"}) PointerPointer pointerPointer, @Cast({"unsigned*"}) IntPointer intPointer);

    @NoException
    public static native void av_bprint_get_buffer(AVBPrint aVBPrint, @Cast({"unsigned"}) int i, @ByPtrPtr @Cast({"unsigned char**"}) byte[] bArr, @Cast({"unsigned*"}) int[] iArr);

    @NoException
    public static native void av_bprint_init(AVBPrint aVBPrint, @Cast({"unsigned"}) int i, @Cast({"unsigned"}) int i2);

    @NoException
    public static native void av_bprint_init_for_buffer(AVBPrint aVBPrint, @Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"unsigned"}) int i);

    @NoException
    public static native void av_bprint_init_for_buffer(AVBPrint aVBPrint, @Cast({"char*"}) BytePointer bytePointer, @Cast({"unsigned"}) int i);

    @NoException
    public static native void av_bprint_init_for_buffer(AVBPrint aVBPrint, @Cast({"char*"}) byte[] bArr, @Cast({"unsigned"}) int i);

    @NoException
    public static native int av_bprint_is_complete(@Const AVBPrint aVBPrint);

    @NoException
    public static native void av_bprint_strftime(AVBPrint aVBPrint, String str, @Const tm tmVar);

    @NoException
    public static native void av_bprint_strftime(AVBPrint aVBPrint, @Cast({"const char*"}) BytePointer bytePointer, @Const tm tmVar);

    @NoException
    public static native void av_bprintf(AVBPrint aVBPrint, String str);

    @NoException
    public static native void av_bprintf(AVBPrint aVBPrint, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native AVBufferRef av_buffer_alloc(int i);

    @NoException
    public static native AVBufferRef av_buffer_allocz(int i);

    @NoException
    public static native AVBufferRef av_buffer_create(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, int i, Free_Pointer_ByteBuffer free_Pointer_ByteBuffer, Pointer pointer, int i2);

    @NoException
    public static native AVBufferRef av_buffer_create(@Cast({"uint8_t*"}) BytePointer bytePointer, int i, Free_Pointer_BytePointer free_Pointer_BytePointer, Pointer pointer, int i2);

    @NoException
    public static native AVBufferRef av_buffer_create(@Cast({"uint8_t*"}) byte[] bArr, int i, Free_Pointer_byte__ free_Pointer_byte__, Pointer pointer, int i2);

    @NoException
    public static native void av_buffer_default_free(Pointer pointer, @Cast({"uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_buffer_default_free(Pointer pointer, @Cast({"uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_buffer_default_free(Pointer pointer, @Cast({"uint8_t*"}) byte[] bArr);

    @NoException
    public static native Pointer av_buffer_get_opaque(@Const AVBufferRef aVBufferRef);

    @NoException
    public static native int av_buffer_get_ref_count(@Const AVBufferRef aVBufferRef);

    @NoException
    public static native int av_buffer_is_writable(@Const AVBufferRef aVBufferRef);

    @NoException
    public static native int av_buffer_make_writable(@ByPtrPtr AVBufferRef aVBufferRef);

    @NoException
    public static native int av_buffer_make_writable(@Cast({"AVBufferRef**"}) PointerPointer pointerPointer);

    @NoException
    public static native Pointer av_buffer_pool_buffer_get_opaque(AVBufferRef aVBufferRef);

    @NoException
    public static native AVBufferRef av_buffer_pool_get(AVBufferPool aVBufferPool);

    @NoException
    public static native AVBufferPool av_buffer_pool_init(int i, Alloc_int alloc_int);

    @NoException
    public static native AVBufferPool av_buffer_pool_init2(int i, Pointer pointer, Alloc_Pointer_int alloc_Pointer_int, Pool_free_Pointer pool_free_Pointer);

    @NoException
    public static native void av_buffer_pool_uninit(@ByPtrPtr AVBufferPool aVBufferPool);

    @NoException
    public static native void av_buffer_pool_uninit(@Cast({"AVBufferPool**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_buffer_realloc(@ByPtrPtr AVBufferRef aVBufferRef, int i);

    @NoException
    public static native int av_buffer_realloc(@Cast({"AVBufferRef**"}) PointerPointer pointerPointer, int i);

    @NoException
    public static native AVBufferRef av_buffer_ref(AVBufferRef aVBufferRef);

    @NoException
    public static native void av_buffer_unref(@ByPtrPtr AVBufferRef aVBufferRef);

    @NoException
    public static native void av_buffer_unref(@Cast({"AVBufferRef**"}) PointerPointer pointerPointer);

    @NoException
    public static native Pointer av_calloc(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    @NoException
    public static native AVCAMELLIA av_camellia_alloc();

    @NoException
    public static native void av_camellia_crypt(AVCAMELLIA avcamellia, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_camellia_crypt(AVCAMELLIA avcamellia, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_camellia_crypt(AVCAMELLIA avcamellia, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native int av_camellia_init(AVCAMELLIA avcamellia, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native int av_camellia_init(AVCAMELLIA avcamellia, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_camellia_init(AVCAMELLIA avcamellia, @Cast({"const uint8_t*"}) byte[] bArr, int i);

    @MemberGetter
    public static native int av_camellia_size();

    @NoException
    public static native AVCAST5 av_cast5_alloc();

    @NoException
    public static native void av_cast5_crypt(AVCAST5 avcast5, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, int i2);

    @NoException
    public static native void av_cast5_crypt(AVCAST5 avcast5, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, int i2);

    @NoException
    public static native void av_cast5_crypt(AVCAST5 avcast5, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, int i2);

    @NoException
    public static native void av_cast5_crypt2(AVCAST5 avcast5, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_cast5_crypt2(AVCAST5 avcast5, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_cast5_crypt2(AVCAST5 avcast5, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native int av_cast5_init(AVCAST5 avcast5, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native int av_cast5_init(AVCAST5 avcast5, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_cast5_init(AVCAST5 avcast5, @Cast({"const uint8_t*"}) byte[] bArr, int i);

    @MemberGetter
    public static native int av_cast5_size();

    @NoException
    @Const
    public static native int av_ceil_log2_c(int i);

    @NoException
    @Cast({"uint64_t"})
    public static native long av_channel_layout_extract_channel(@Cast({"uint64_t"}) long j, int i);

    @NoException
    public static native int av_chroma_location_from_name(String str);

    @NoException
    public static native int av_chroma_location_from_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_chroma_location_name(@Cast({"AVChromaLocation"}) int i);

    @NoException
    @Cast({"int64_t"})
    @Const
    public static native long av_clip64_c(@Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2, @Cast({"int64_t"}) long j3);

    @NoException
    @Const
    public static native int av_clip_c(int i, int i2, int i3);

    @NoException
    @Const
    public static native short av_clip_int16_c(int i);

    @NoException
    @Const
    public static native byte av_clip_int8_c(int i);

    @NoException
    @Const
    public static native int av_clip_intp2_c(int i, int i2);

    @NoException
    @Cast({"uint16_t"})
    @Const
    public static native short av_clip_uint16_c(int i);

    @NoException
    @Cast({"uint8_t"})
    @Const
    public static native byte av_clip_uint8_c(int i);

    @NoException
    @Cast({"unsigned"})
    @Const
    public static native int av_clip_uintp2_c(int i, int i2);

    @NoException
    @Const
    public static native double av_clipd_c(double d, double d2, double d3);

    @NoException
    @Const
    public static native float av_clipf_c(float f, float f2, float f3);

    @NoException
    @Const
    public static native int av_clipl_int32_c(@Cast({"int64_t"}) long j);

    @NoException
    public static native int av_cmp_q(@ByVal AVRational aVRational, @ByVal AVRational aVRational2);

    @NoException
    public static native int av_color_primaries_from_name(String str);

    @NoException
    public static native int av_color_primaries_from_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_color_primaries_name(@Cast({"AVColorPrimaries"}) int i);

    @NoException
    public static native int av_color_range_from_name(String str);

    @NoException
    public static native int av_color_range_from_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_color_range_name(@Cast({"AVColorRange"}) int i);

    @NoException
    public static native int av_color_space_from_name(String str);

    @NoException
    public static native int av_color_space_from_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_color_space_name(@Cast({"AVColorSpace"}) int i);

    @NoException
    public static native int av_color_transfer_from_name(String str);

    @NoException
    public static native int av_color_transfer_from_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_color_transfer_name(@Cast({"AVColorTransferCharacteristic"}) int i);

    @NoException
    @Cast({"int64_t"})
    public static native long av_compare_mod(@Cast({"uint64_t"}) long j, @Cast({"uint64_t"}) long j2, @Cast({"uint64_t"}) long j3);

    @NoException
    public static native int av_compare_ts(@Cast({"int64_t"}) long j, @ByVal AVRational aVRational, @Cast({"int64_t"}) long j2, @ByVal AVRational aVRational2);

    @NoException
    public static native AVContentLightMetadata av_content_light_metadata_alloc(@Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @NoException
    public static native AVContentLightMetadata av_content_light_metadata_create_side_data(AVFrame aVFrame);

    @NoException
    public static native int av_cpu_count();

    @NoException
    @Cast({"size_t"})
    public static native long av_cpu_max_align();

    @NoException
    @Cast({"uint32_t"})
    public static native int av_crc(@Cast({"const AVCRC*"}) IntBuffer intBuffer, @Cast({"uint32_t"}) int i, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"uint32_t"})
    public static native int av_crc(@Cast({"const AVCRC*"}) IntPointer intPointer, @Cast({"uint32_t"}) int i, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"uint32_t"})
    public static native int av_crc(@Cast({"const AVCRC*"}) int[] iArr, @Cast({"uint32_t"}) int i, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"const AVCRC*"})
    public static native IntPointer av_crc_get_table(@Cast({"AVCRCId"}) int i);

    @NoException
    public static native int av_crc_init(@Cast({"AVCRC*"}) IntBuffer intBuffer, int i, int i2, @Cast({"uint32_t"}) int i3, int i4);

    @NoException
    public static native int av_crc_init(@Cast({"AVCRC*"}) IntPointer intPointer, int i, int i2, @Cast({"uint32_t"}) int i3, int i4);

    @NoException
    public static native int av_crc_init(@Cast({"AVCRC*"}) int[] iArr, int i, int i2, @Cast({"uint32_t"}) int i3, int i4);

    @NoException
    @Const
    @ByVal
    public static native AVRational av_d2q(double d, int i);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_d2str(double d);

    @NoException
    @Cast({"AVClassCategory"})
    public static native int av_default_get_category(Pointer pointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_default_item_name(Pointer pointer);

    @NoException
    public static native AVDES av_des_alloc();

    @NoException
    public static native void av_des_crypt(AVDES avdes, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_des_crypt(AVDES avdes, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_des_crypt(AVDES avdes, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native int av_des_init(AVDES avdes, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native int av_des_init(AVDES avdes, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i, int i2);

    @NoException
    public static native int av_des_init(AVDES avdes, @Cast({"const uint8_t*"}) byte[] bArr, int i, int i2);

    @NoException
    public static native void av_des_mac(AVDES avdes, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i);

    @NoException
    public static native void av_des_mac(AVDES avdes, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i);

    @NoException
    public static native void av_des_mac(AVDES avdes, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i);

    @NoException
    public static native int av_dict_copy(@ByPtrPtr AVDictionary aVDictionary, @Const AVDictionary aVDictionary2, int i);

    @NoException
    public static native int av_dict_copy(@Cast({"AVDictionary**"}) PointerPointer pointerPointer, @Const AVDictionary aVDictionary, int i);

    @NoException
    public static native int av_dict_count(@Const AVDictionary aVDictionary);

    @NoException
    public static native void av_dict_free(@ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native void av_dict_free(@Cast({"AVDictionary**"}) PointerPointer pointerPointer);

    @NoException
    public static native AVDictionaryEntry av_dict_get(@Const AVDictionary aVDictionary, String str, @Const AVDictionaryEntry aVDictionaryEntry, int i);

    @NoException
    public static native AVDictionaryEntry av_dict_get(@Const AVDictionary aVDictionary, @Cast({"const char*"}) BytePointer bytePointer, @Const AVDictionaryEntry aVDictionaryEntry, int i);

    @NoException
    public static native int av_dict_get_string(@Const AVDictionary aVDictionary, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer, byte b, byte b2);

    @NoException
    public static native int av_dict_get_string(@Const AVDictionary aVDictionary, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer, byte b, byte b2);

    @NoException
    public static native int av_dict_get_string(@Const AVDictionary aVDictionary, @Cast({"char**"}) PointerPointer pointerPointer, byte b, byte b2);

    @NoException
    public static native int av_dict_get_string(@Const AVDictionary aVDictionary, @ByPtrPtr @Cast({"char**"}) byte[] bArr, byte b, byte b2);

    @NoException
    public static native int av_dict_parse_string(@ByPtrPtr AVDictionary aVDictionary, String str, String str2, String str3, int i);

    @NoException
    public static native int av_dict_parse_string(@ByPtrPtr AVDictionary aVDictionary, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i);

    @NoException
    public static native int av_dict_parse_string(@Cast({"AVDictionary**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i);

    @NoException
    public static native int av_dict_set(@ByPtrPtr AVDictionary aVDictionary, String str, String str2, int i);

    @NoException
    public static native int av_dict_set(@ByPtrPtr AVDictionary aVDictionary, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i);

    @NoException
    public static native int av_dict_set(@Cast({"AVDictionary**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i);

    @NoException
    public static native int av_dict_set_int(@ByPtrPtr AVDictionary aVDictionary, String str, @Cast({"int64_t"}) long j, int i);

    @NoException
    public static native int av_dict_set_int(@ByPtrPtr AVDictionary aVDictionary, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"int64_t"}) long j, int i);

    @NoException
    public static native int av_dict_set_int(@Cast({"AVDictionary**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"int64_t"}) long j, int i);

    @NoException
    public static native String av_dirname(@Cast({"char*"}) ByteBuffer byteBuffer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_dirname(@Cast({"char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_dirname(@Cast({"char*"}) byte[] bArr);

    @NoException
    public static native void av_display_matrix_flip(IntBuffer intBuffer, int i, int i2);

    @NoException
    public static native void av_display_matrix_flip(IntPointer intPointer, int i, int i2);

    @NoException
    public static native void av_display_matrix_flip(int[] iArr, int i, int i2);

    @NoException
    public static native double av_display_rotation_get(@Const IntBuffer intBuffer);

    @NoException
    public static native double av_display_rotation_get(@Const IntPointer intPointer);

    @NoException
    public static native double av_display_rotation_get(@Const int[] iArr);

    @NoException
    public static native void av_display_rotation_set(IntBuffer intBuffer, double d);

    @NoException
    public static native void av_display_rotation_set(IntPointer intPointer, double d);

    @NoException
    public static native void av_display_rotation_set(int[] iArr, double d);

    @NoException
    @Const
    @ByVal
    public static native AVRational av_div_q(@ByVal AVRational aVRational, @ByVal AVRational aVRational2);

    @NoException
    @Cast({"uint64_t"})
    public static native long av_double2int(double d);

    @NoException
    public static native AVDownmixInfo av_downmix_info_update_side_data(AVFrame aVFrame);

    @NoException
    public static native AVDynamicHDRPlus av_dynamic_hdr_plus_alloc(@Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @NoException
    public static native AVDynamicHDRPlus av_dynamic_hdr_plus_create_side_data(AVFrame aVFrame);

    @NoException
    public static native Pointer av_dynarray2_add(@ByPtrPtr @Cast({"void**"}) Pointer pointer, IntBuffer intBuffer, @Cast({"size_t"}) long j, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native Pointer av_dynarray2_add(@ByPtrPtr @Cast({"void**"}) Pointer pointer, IntPointer intPointer, @Cast({"size_t"}) long j, @Cast({"const uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native Pointer av_dynarray2_add(@ByPtrPtr @Cast({"void**"}) Pointer pointer, int[] iArr, @Cast({"size_t"}) long j, @Cast({"const uint8_t*"}) byte[] bArr);

    @NoException
    public static native Pointer av_dynarray2_add(@Cast({"void**"}) PointerPointer pointerPointer, IntPointer intPointer, @Cast({"size_t"}) long j, @Cast({"const uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_dynarray_add(Pointer pointer, IntBuffer intBuffer, Pointer pointer2);

    @NoException
    public static native void av_dynarray_add(Pointer pointer, IntPointer intPointer, Pointer pointer2);

    @NoException
    public static native void av_dynarray_add(Pointer pointer, int[] iArr, Pointer pointer2);

    @NoException
    public static native int av_dynarray_add_nofree(Pointer pointer, IntBuffer intBuffer, Pointer pointer2);

    @NoException
    public static native int av_dynarray_add_nofree(Pointer pointer, IntPointer intPointer, Pointer pointer2);

    @NoException
    public static native int av_dynarray_add_nofree(Pointer pointer, int[] iArr, Pointer pointer2);

    @NoException
    @Cast({"uint8_t*"})
    public static native BytePointer av_encryption_info_add_side_data(@Const AVEncryptionInfo aVEncryptionInfo, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @NoException
    public static native AVEncryptionInfo av_encryption_info_alloc(@Cast({"uint32_t"}) int i, @Cast({"uint32_t"}) int i2, @Cast({"uint32_t"}) int i3);

    @NoException
    public static native AVEncryptionInfo av_encryption_info_clone(@Const AVEncryptionInfo aVEncryptionInfo);

    @NoException
    public static native void av_encryption_info_free(AVEncryptionInfo aVEncryptionInfo);

    @NoException
    public static native AVEncryptionInfo av_encryption_info_get_side_data(@Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native AVEncryptionInfo av_encryption_info_get_side_data(@Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native AVEncryptionInfo av_encryption_info_get_side_data(@Cast({"const uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"uint8_t*"})
    public static native BytePointer av_encryption_init_info_add_side_data(@Const AVEncryptionInitInfo aVEncryptionInitInfo, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @NoException
    public static native AVEncryptionInitInfo av_encryption_init_info_alloc(@Cast({"uint32_t"}) int i, @Cast({"uint32_t"}) int i2, @Cast({"uint32_t"}) int i3, @Cast({"uint32_t"}) int i4);

    @NoException
    public static native void av_encryption_init_info_free(AVEncryptionInitInfo aVEncryptionInitInfo);

    @NoException
    public static native AVEncryptionInitInfo av_encryption_init_info_get_side_data(@Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native AVEncryptionInitInfo av_encryption_init_info_get_side_data(@Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native AVEncryptionInitInfo av_encryption_init_info_get_side_data(@Cast({"const uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_escape(@ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer, String str, String str2, @Cast({"AVEscapeMode"}) int i, int i2);

    @NoException
    public static native int av_escape(@ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"AVEscapeMode"}) int i, int i2);

    @NoException
    public static native int av_escape(@ByPtrPtr @Cast({"char**"}) BytePointer bytePointer, String str, String str2, @Cast({"AVEscapeMode"}) int i, int i2);

    @NoException
    public static native int av_escape(@ByPtrPtr @Cast({"char**"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, @Cast({"AVEscapeMode"}) int i, int i2);

    @NoException
    public static native int av_escape(@Cast({"char**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"AVEscapeMode"}) int i, int i2);

    @NoException
    public static native int av_escape(@ByPtrPtr @Cast({"char**"}) byte[] bArr, String str, String str2, @Cast({"AVEscapeMode"}) int i, int i2);

    @NoException
    public static native int av_escape(@ByPtrPtr @Cast({"char**"}) byte[] bArr, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"AVEscapeMode"}) int i, int i2);

    @NoException
    public static native int av_expr_count_func(AVExpr aVExpr, @Cast({"unsigned*"}) IntBuffer intBuffer, int i, int i2);

    @NoException
    public static native int av_expr_count_func(AVExpr aVExpr, @Cast({"unsigned*"}) IntPointer intPointer, int i, int i2);

    @NoException
    public static native int av_expr_count_func(AVExpr aVExpr, @Cast({"unsigned*"}) int[] iArr, int i, int i2);

    @NoException
    public static native int av_expr_count_vars(AVExpr aVExpr, @Cast({"unsigned*"}) IntBuffer intBuffer, int i);

    @NoException
    public static native int av_expr_count_vars(AVExpr aVExpr, @Cast({"unsigned*"}) IntPointer intPointer, int i);

    @NoException
    public static native int av_expr_count_vars(AVExpr aVExpr, @Cast({"unsigned*"}) int[] iArr, int i);

    @NoException
    public static native double av_expr_eval(AVExpr aVExpr, @Const DoubleBuffer doubleBuffer, Pointer pointer);

    @NoException
    public static native double av_expr_eval(AVExpr aVExpr, @Const DoublePointer doublePointer, Pointer pointer);

    @NoException
    public static native double av_expr_eval(AVExpr aVExpr, @Const double[] dArr, Pointer pointer);

    @NoException
    public static native void av_expr_free(AVExpr aVExpr);

    @NoException
    public static native int av_expr_parse(@ByPtrPtr AVExpr aVExpr, String str, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, int i, Pointer pointer);

    @NoException
    public static native int av_expr_parse(@ByPtrPtr AVExpr aVExpr, String str, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, int i, Pointer pointer);

    @NoException
    public static native int av_expr_parse(@ByPtrPtr AVExpr aVExpr, String str, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, int i, Pointer pointer);

    @NoException
    public static native int av_expr_parse(@ByPtrPtr AVExpr aVExpr, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, int i, Pointer pointer);

    @NoException
    public static native int av_expr_parse(@ByPtrPtr AVExpr aVExpr, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer3, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer4, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, int i, Pointer pointer);

    @NoException
    public static native int av_expr_parse(@ByPtrPtr AVExpr aVExpr, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, int i, Pointer pointer);

    @NoException
    public static native int av_expr_parse(@Cast({"AVExpr**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*const*"}) PointerPointer pointerPointer2, @Cast({"const char*const*"}) PointerPointer pointerPointer3, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer4, @Cast({"const char*const*"}) PointerPointer pointerPointer5, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer6, int i, Pointer pointer);

    @NoException
    public static native int av_expr_parse_and_eval(DoubleBuffer doubleBuffer, String str, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer, @Const DoubleBuffer doubleBuffer2, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, Pointer pointer, int i, Pointer pointer2);

    @NoException
    public static native int av_expr_parse_and_eval(DoubleBuffer doubleBuffer, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer, @Const DoubleBuffer doubleBuffer2, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, Pointer pointer, int i, Pointer pointer2);

    @NoException
    public static native int av_expr_parse_and_eval(DoublePointer doublePointer, String str, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer, @Const DoublePointer doublePointer2, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, Pointer pointer, int i, Pointer pointer2);

    @NoException
    public static native int av_expr_parse_and_eval(DoublePointer doublePointer, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer2, @Const DoublePointer doublePointer2, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer3, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer4, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, Pointer pointer, int i, Pointer pointer2);

    @NoException
    public static native int av_expr_parse_and_eval(DoublePointer doublePointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*const*"}) PointerPointer pointerPointer, @Const DoublePointer doublePointer2, @Cast({"const char*const*"}) PointerPointer pointerPointer2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer3, @Cast({"const char*const*"}) PointerPointer pointerPointer4, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer5, Pointer pointer, int i, Pointer pointer2);

    @NoException
    public static native int av_expr_parse_and_eval(double[] dArr, String str, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr, @Const double[] dArr2, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, Pointer pointer, int i, Pointer pointer2);

    @NoException
    public static native int av_expr_parse_and_eval(double[] dArr, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr, @Const double[] dArr2, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr2, @Cast({"double (**)(void*, double)"}) PointerPointer pointerPointer, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr3, @Cast({"double (**)(void*, double, double)"}) PointerPointer pointerPointer2, Pointer pointer, int i, Pointer pointer2);

    @NoException
    public static native void av_fast_malloc(Pointer pointer, @Cast({"unsigned int*"}) IntBuffer intBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_fast_malloc(Pointer pointer, @Cast({"unsigned int*"}) IntPointer intPointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_fast_malloc(Pointer pointer, @Cast({"unsigned int*"}) int[] iArr, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_fast_mallocz(Pointer pointer, @Cast({"unsigned int*"}) IntBuffer intBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_fast_mallocz(Pointer pointer, @Cast({"unsigned int*"}) IntPointer intPointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_fast_mallocz(Pointer pointer, @Cast({"unsigned int*"}) int[] iArr, @Cast({"size_t"}) long j);

    @NoException
    public static native Pointer av_fast_realloc(Pointer pointer, @Cast({"unsigned int*"}) IntBuffer intBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native Pointer av_fast_realloc(Pointer pointer, @Cast({"unsigned int*"}) IntPointer intPointer, @Cast({"size_t"}) long j);

    @NoException
    public static native Pointer av_fast_realloc(Pointer pointer, @Cast({"unsigned int*"}) int[] iArr, @Cast({"size_t"}) long j);

    @NoException
    public static native AVFifoBuffer av_fifo_alloc(@Cast({"unsigned int"}) int i);

    @NoException
    public static native AVFifoBuffer av_fifo_alloc_array(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    @NoException
    public static native void av_fifo_drain(AVFifoBuffer aVFifoBuffer, int i);

    @NoException
    public static native void av_fifo_free(AVFifoBuffer aVFifoBuffer);

    @NoException
    public static native void av_fifo_freep(@ByPtrPtr AVFifoBuffer aVFifoBuffer);

    @NoException
    public static native void av_fifo_freep(@Cast({"AVFifoBuffer**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_fifo_generic_peek(AVFifoBuffer aVFifoBuffer, Pointer pointer, int i, Func_Pointer_Pointer_int func_Pointer_Pointer_int);

    @NoException
    public static native int av_fifo_generic_peek_at(AVFifoBuffer aVFifoBuffer, Pointer pointer, int i, int i2, Func_Pointer_Pointer_int func_Pointer_Pointer_int);

    @NoException
    public static native int av_fifo_generic_read(AVFifoBuffer aVFifoBuffer, Pointer pointer, int i, Func_Pointer_Pointer_int func_Pointer_Pointer_int);

    @NoException
    public static native int av_fifo_generic_write(AVFifoBuffer aVFifoBuffer, Pointer pointer, int i, Int_func_Pointer_Pointer_int int_func_Pointer_Pointer_int);

    @NoException
    public static native int av_fifo_grow(AVFifoBuffer aVFifoBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    @Cast({"uint8_t*"})
    public static native BytePointer av_fifo_peek2(@Const AVFifoBuffer aVFifoBuffer, int i);

    @NoException
    public static native int av_fifo_realloc2(AVFifoBuffer aVFifoBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_fifo_reset(AVFifoBuffer aVFifoBuffer);

    @NoException
    public static native int av_fifo_size(@Const AVFifoBuffer aVFifoBuffer);

    @NoException
    public static native int av_fifo_space(@Const AVFifoBuffer aVFifoBuffer);

    @NoException
    public static native int av_file_map(String str, @ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, @Cast({"size_t*"}) SizeTPointer sizeTPointer, int i, Pointer pointer);

    @NoException
    public static native int av_file_map(String str, @ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer, int i, Pointer pointer);

    @NoException
    public static native int av_file_map(String str, @ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, @Cast({"size_t*"}) SizeTPointer sizeTPointer, int i, Pointer pointer);

    @NoException
    public static native int av_file_map(@Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, @Cast({"size_t*"}) SizeTPointer sizeTPointer, int i, Pointer pointer);

    @NoException
    public static native int av_file_map(@Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer2, @Cast({"size_t*"}) SizeTPointer sizeTPointer, int i, Pointer pointer);

    @NoException
    public static native int av_file_map(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"uint8_t**"}) PointerPointer pointerPointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer, int i, Pointer pointer);

    @NoException
    public static native int av_file_map(@Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, @Cast({"size_t*"}) SizeTPointer sizeTPointer, int i, Pointer pointer);

    @NoException
    public static native void av_file_unmap(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_file_unmap(@Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_file_unmap(@Cast({"uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"AVPixelFormat"})
    public static native int av_find_best_pix_fmt_of_2(@Cast({"AVPixelFormat"}) int i, @Cast({"AVPixelFormat"}) int i2, @Cast({"AVPixelFormat"}) int i3, int i4, IntBuffer intBuffer);

    @NoException
    @Cast({"AVPixelFormat"})
    public static native int av_find_best_pix_fmt_of_2(@Cast({"AVPixelFormat"}) int i, @Cast({"AVPixelFormat"}) int i2, @Cast({"AVPixelFormat"}) int i3, int i4, IntPointer intPointer);

    @NoException
    @Cast({"AVPixelFormat"})
    public static native int av_find_best_pix_fmt_of_2(@Cast({"AVPixelFormat"}) int i, @Cast({"AVPixelFormat"}) int i2, @Cast({"AVPixelFormat"}) int i3, int i4, int[] iArr);

    @NoException
    public static native int av_find_info_tag(@Cast({"char*"}) ByteBuffer byteBuffer, int i, String str, String str2);

    @NoException
    public static native int av_find_info_tag(@Cast({"char*"}) ByteBuffer byteBuffer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    public static native int av_find_info_tag(@Cast({"char*"}) BytePointer bytePointer, int i, String str, String str2);

    @NoException
    public static native int av_find_info_tag(@Cast({"char*"}) BytePointer bytePointer, int i, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3);

    @NoException
    public static native int av_find_info_tag(@Cast({"char*"}) byte[] bArr, int i, String str, String str2);

    @NoException
    public static native int av_find_info_tag(@Cast({"char*"}) byte[] bArr, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    public static native int av_find_nearest_q_idx(@ByVal AVRational aVRational, @Const AVRational aVRational2);

    @NoException
    @Cast({"uint32_t"})
    public static native int av_float2int(float f);

    @NoException
    @Cast({"FILE*"})
    public static native Pointer av_fopen_utf8(String str, String str2);

    @NoException
    @Cast({"FILE*"})
    public static native Pointer av_fopen_utf8(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    public static native void av_force_cpu_flags(int i);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_fourcc_make_string(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"uint32_t"}) int i);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_fourcc_make_string(@Cast({"char*"}) BytePointer bytePointer, @Cast({"uint32_t"}) int i);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_fourcc_make_string(@Cast({"char*"}) byte[] bArr, @Cast({"uint32_t"}) int i);

    @NoException
    public static native AVFrame av_frame_alloc();

    @NoException
    public static native int av_frame_apply_cropping(AVFrame aVFrame, int i);

    @NoException
    public static native AVFrame av_frame_clone(@Const AVFrame aVFrame);

    @NoException
    public static native int av_frame_copy(AVFrame aVFrame, @Const AVFrame aVFrame2);

    @NoException
    public static native int av_frame_copy_props(AVFrame aVFrame, @Const AVFrame aVFrame2);

    @NoException
    public static native void av_frame_free(@ByPtrPtr AVFrame aVFrame);

    @NoException
    public static native void av_frame_free(@Cast({"AVFrame**"}) PointerPointer pointerPointer);

    @NoException
    @Deprecated
    @Cast({"int64_t"})
    public static native long av_frame_get_best_effort_timestamp(@Const AVFrame aVFrame);

    @NoException
    public static native int av_frame_get_buffer(AVFrame aVFrame, int i);

    @NoException
    @Deprecated
    @Cast({"int64_t"})
    public static native long av_frame_get_channel_layout(@Const AVFrame aVFrame);

    @NoException
    @Deprecated
    public static native int av_frame_get_channels(@Const AVFrame aVFrame);

    @NoException
    @Deprecated
    @Cast({"AVColorRange"})
    public static native int av_frame_get_color_range(@Const AVFrame aVFrame);

    @NoException
    @Deprecated
    @Cast({"AVColorSpace"})
    public static native int av_frame_get_colorspace(@Const AVFrame aVFrame);

    @NoException
    @Deprecated
    public static native int av_frame_get_decode_error_flags(@Const AVFrame aVFrame);

    @NoException
    @Deprecated
    public static native AVDictionary av_frame_get_metadata(@Const AVFrame aVFrame);

    @NoException
    @Deprecated
    @Cast({"int64_t"})
    public static native long av_frame_get_pkt_duration(@Const AVFrame aVFrame);

    @NoException
    @Deprecated
    @Cast({"int64_t"})
    public static native long av_frame_get_pkt_pos(@Const AVFrame aVFrame);

    @NoException
    @Deprecated
    public static native int av_frame_get_pkt_size(@Const AVFrame aVFrame);

    @NoException
    public static native AVBufferRef av_frame_get_plane_buffer(AVFrame aVFrame, int i);

    @NoException
    @Deprecated
    public static native ByteBuffer av_frame_get_qp_table(AVFrame aVFrame, IntBuffer intBuffer, IntBuffer intBuffer2);

    @NoException
    @Deprecated
    public static native BytePointer av_frame_get_qp_table(AVFrame aVFrame, IntPointer intPointer, IntPointer intPointer2);

    @NoException
    @Deprecated
    public static native byte[] av_frame_get_qp_table(AVFrame aVFrame, int[] iArr, int[] iArr2);

    @NoException
    @Deprecated
    public static native int av_frame_get_sample_rate(@Const AVFrame aVFrame);

    @NoException
    public static native AVFrameSideData av_frame_get_side_data(@Const AVFrame aVFrame, @Cast({"AVFrameSideDataType"}) int i);

    @NoException
    public static native int av_frame_is_writable(AVFrame aVFrame);

    @NoException
    public static native int av_frame_make_writable(AVFrame aVFrame);

    @NoException
    public static native void av_frame_move_ref(AVFrame aVFrame, AVFrame aVFrame2);

    @NoException
    public static native AVFrameSideData av_frame_new_side_data(AVFrame aVFrame, @Cast({"AVFrameSideDataType"}) int i, int i2);

    @NoException
    public static native AVFrameSideData av_frame_new_side_data_from_buf(AVFrame aVFrame, @Cast({"AVFrameSideDataType"}) int i, AVBufferRef aVBufferRef);

    @NoException
    public static native int av_frame_ref(AVFrame aVFrame, @Const AVFrame aVFrame2);

    @NoException
    public static native void av_frame_remove_side_data(AVFrame aVFrame, @Cast({"AVFrameSideDataType"}) int i);

    @NoException
    @Deprecated
    public static native void av_frame_set_best_effort_timestamp(AVFrame aVFrame, @Cast({"int64_t"}) long j);

    @NoException
    @Deprecated
    public static native void av_frame_set_channel_layout(AVFrame aVFrame, @Cast({"int64_t"}) long j);

    @NoException
    @Deprecated
    public static native void av_frame_set_channels(AVFrame aVFrame, int i);

    @NoException
    @Deprecated
    public static native void av_frame_set_color_range(AVFrame aVFrame, @Cast({"AVColorRange"}) int i);

    @NoException
    @Deprecated
    public static native void av_frame_set_colorspace(AVFrame aVFrame, @Cast({"AVColorSpace"}) int i);

    @NoException
    @Deprecated
    public static native void av_frame_set_decode_error_flags(AVFrame aVFrame, int i);

    @NoException
    @Deprecated
    public static native void av_frame_set_metadata(AVFrame aVFrame, AVDictionary aVDictionary);

    @NoException
    @Deprecated
    public static native void av_frame_set_pkt_duration(AVFrame aVFrame, @Cast({"int64_t"}) long j);

    @NoException
    @Deprecated
    public static native void av_frame_set_pkt_pos(AVFrame aVFrame, @Cast({"int64_t"}) long j);

    @NoException
    @Deprecated
    public static native void av_frame_set_pkt_size(AVFrame aVFrame, int i);

    @NoException
    @Deprecated
    public static native int av_frame_set_qp_table(AVFrame aVFrame, AVBufferRef aVBufferRef, int i, int i2);

    @NoException
    @Deprecated
    public static native void av_frame_set_sample_rate(AVFrame aVFrame, int i);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_frame_side_data_name(@Cast({"AVFrameSideDataType"}) int i);

    @NoException
    public static native void av_frame_unref(AVFrame aVFrame);

    @NoException
    public static native void av_free(Pointer pointer);

    @NoException
    public static native void av_freep(Pointer pointer);

    @NoException
    @Cast({"int64_t"})
    @Const
    public static native long av_gcd(@Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2);

    @NoException
    @ByVal
    public static native AVRational av_gcd_q(@ByVal AVRational aVRational, @ByVal AVRational aVRational2, int i, @ByVal AVRational aVRational3);

    @NoException
    @Cast({"AVSampleFormat"})
    public static native int av_get_alt_sample_fmt(@Cast({"AVSampleFormat"}) int i, int i2);

    @NoException
    public static native int av_get_bits_per_pixel(@Const AVPixFmtDescriptor aVPixFmtDescriptor);

    @NoException
    public static native int av_get_bytes_per_sample(@Cast({"AVSampleFormat"}) int i);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_get_channel_description(@Cast({"uint64_t"}) long j);

    @NoException
    @Cast({"uint64_t"})
    public static native long av_get_channel_layout(String str);

    @NoException
    @Cast({"uint64_t"})
    public static native long av_get_channel_layout(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_get_channel_layout_channel_index(@Cast({"uint64_t"}) long j, @Cast({"uint64_t"}) long j2);

    @NoException
    public static native int av_get_channel_layout_nb_channels(@Cast({"uint64_t"}) long j);

    @NoException
    public static native void av_get_channel_layout_string(@Cast({"char*"}) ByteBuffer byteBuffer, int i, int i2, @Cast({"uint64_t"}) long j);

    @NoException
    public static native void av_get_channel_layout_string(@Cast({"char*"}) BytePointer bytePointer, int i, int i2, @Cast({"uint64_t"}) long j);

    @NoException
    public static native void av_get_channel_layout_string(@Cast({"char*"}) byte[] bArr, int i, int i2, @Cast({"uint64_t"}) long j);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_get_channel_name(@Cast({"uint64_t"}) long j);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_get_colorspace_name(@Cast({"AVColorSpace"}) int i);

    @NoException
    public static native int av_get_cpu_flags();

    @NoException
    @Cast({"int64_t"})
    public static native long av_get_default_channel_layout(int i);

    @NoException
    public static native int av_get_extended_channel_layout(String str, @Cast({"uint64_t*"}) LongBuffer longBuffer, IntBuffer intBuffer);

    @NoException
    public static native int av_get_extended_channel_layout(String str, @Cast({"uint64_t*"}) LongPointer longPointer, IntPointer intPointer);

    @NoException
    public static native int av_get_extended_channel_layout(String str, @Cast({"uint64_t*"}) long[] jArr, int[] iArr);

    @NoException
    public static native int av_get_extended_channel_layout(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"uint64_t*"}) LongBuffer longBuffer, IntBuffer intBuffer);

    @NoException
    public static native int av_get_extended_channel_layout(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"uint64_t*"}) LongPointer longPointer, IntPointer intPointer);

    @NoException
    public static native int av_get_extended_channel_layout(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"uint64_t*"}) long[] jArr, int[] iArr);

    @NoException
    public static native String av_get_known_color_name(int i, @ByPtrPtr @Cast({"const uint8_t**"}) ByteBuffer byteBuffer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_get_known_color_name(int i, @ByPtrPtr @Cast({"const uint8_t**"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_get_known_color_name(int i, @Cast({"const uint8_t**"}) PointerPointer pointerPointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_get_known_color_name(int i, @ByPtrPtr @Cast({"const uint8_t**"}) byte[] bArr);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_get_media_type_string(@Cast({"AVMediaType"}) int i);

    @NoException
    @Cast({"AVSampleFormat"})
    public static native int av_get_packed_sample_fmt(@Cast({"AVSampleFormat"}) int i);

    @NoException
    public static native int av_get_padded_bits_per_pixel(@Const AVPixFmtDescriptor aVPixFmtDescriptor);

    @NoException
    @Cast({"char"})
    public static native byte av_get_picture_type_char(@Cast({"AVPictureType"}) int i);

    @NoException
    @Cast({"AVPixelFormat"})
    public static native int av_get_pix_fmt(String str);

    @NoException
    @Cast({"AVPixelFormat"})
    public static native int av_get_pix_fmt(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_get_pix_fmt_loss(@Cast({"AVPixelFormat"}) int i, @Cast({"AVPixelFormat"}) int i2, int i3);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_get_pix_fmt_name(@Cast({"AVPixelFormat"}) int i);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_get_pix_fmt_string(@Cast({"char*"}) ByteBuffer byteBuffer, int i, @Cast({"AVPixelFormat"}) int i2);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_get_pix_fmt_string(@Cast({"char*"}) BytePointer bytePointer, int i, @Cast({"AVPixelFormat"}) int i2);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_get_pix_fmt_string(@Cast({"char*"}) byte[] bArr, int i, @Cast({"AVPixelFormat"}) int i2);

    @NoException
    @Cast({"AVSampleFormat"})
    public static native int av_get_planar_sample_fmt(@Cast({"AVSampleFormat"}) int i);

    @NoException
    @Cast({"uint32_t"})
    public static native int av_get_random_seed();

    @NoException
    @Cast({"AVSampleFormat"})
    public static native int av_get_sample_fmt(String str);

    @NoException
    @Cast({"AVSampleFormat"})
    public static native int av_get_sample_fmt(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_get_sample_fmt_name(@Cast({"AVSampleFormat"}) int i);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_get_sample_fmt_string(@Cast({"char*"}) ByteBuffer byteBuffer, int i, @Cast({"AVSampleFormat"}) int i2);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_get_sample_fmt_string(@Cast({"char*"}) BytePointer bytePointer, int i, @Cast({"AVSampleFormat"}) int i2);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_get_sample_fmt_string(@Cast({"char*"}) byte[] bArr, int i, @Cast({"AVSampleFormat"}) int i2);

    @NoException
    public static native int av_get_standard_channel_layout(@Cast({"unsigned"}) int i, @Cast({"uint64_t*"}) LongBuffer longBuffer, @ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int av_get_standard_channel_layout(@Cast({"unsigned"}) int i, @Cast({"uint64_t*"}) LongPointer longPointer, @ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer);

    @NoException
    public static native int av_get_standard_channel_layout(@Cast({"unsigned"}) int i, @Cast({"uint64_t*"}) LongPointer longPointer, @Cast({"const char**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_get_standard_channel_layout(@Cast({"unsigned"}) int i, @Cast({"uint64_t*"}) long[] jArr, @ByPtrPtr @Cast({"const char**"}) byte[] bArr);

    @NoException
    @ByVal
    public static native AVRational av_get_time_base_q();

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_get_token(@ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer, String str);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_get_token(@ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_get_token(@ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer, String str);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_get_token(@ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_get_token(@Cast({"const char**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_get_token(@ByPtrPtr @Cast({"const char**"}) byte[] bArr, String str);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_get_token(@ByPtrPtr @Cast({"const char**"}) byte[] bArr, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"int64_t"})
    public static native long av_gettime();

    @NoException
    @Cast({"int64_t"})
    public static native long av_gettime_relative();

    @NoException
    public static native int av_gettime_relative_is_monotonic();

    @NoException
    public static native int av_hash_alloc(@ByPtrPtr AVHashContext aVHashContext, String str);

    @NoException
    public static native int av_hash_alloc(@ByPtrPtr AVHashContext aVHashContext, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_hash_alloc(@Cast({"AVHashContext**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native void av_hash_final(AVHashContext aVHashContext, @Cast({"uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_hash_final(AVHashContext aVHashContext, @Cast({"uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_hash_final(AVHashContext aVHashContext, @Cast({"uint8_t*"}) byte[] bArr);

    @NoException
    public static native void av_hash_final_b64(AVHashContext aVHashContext, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void av_hash_final_b64(AVHashContext aVHashContext, @Cast({"uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void av_hash_final_b64(AVHashContext aVHashContext, @Cast({"uint8_t*"}) byte[] bArr, int i);

    @NoException
    public static native void av_hash_final_bin(AVHashContext aVHashContext, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void av_hash_final_bin(AVHashContext aVHashContext, @Cast({"uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void av_hash_final_bin(AVHashContext aVHashContext, @Cast({"uint8_t*"}) byte[] bArr, int i);

    @NoException
    public static native void av_hash_final_hex(AVHashContext aVHashContext, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void av_hash_final_hex(AVHashContext aVHashContext, @Cast({"uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void av_hash_final_hex(AVHashContext aVHashContext, @Cast({"uint8_t*"}) byte[] bArr, int i);

    @NoException
    public static native void av_hash_freep(@ByPtrPtr AVHashContext aVHashContext);

    @NoException
    public static native void av_hash_freep(@Cast({"AVHashContext**"}) PointerPointer pointerPointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_hash_get_name(@Const AVHashContext aVHashContext);

    @NoException
    public static native int av_hash_get_size(@Const AVHashContext aVHashContext);

    @NoException
    public static native void av_hash_init(AVHashContext aVHashContext);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_hash_names(int i);

    @NoException
    public static native void av_hash_update(AVHashContext aVHashContext, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void av_hash_update(AVHashContext aVHashContext, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_hash_update(AVHashContext aVHashContext, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void av_hash_update(AVHashContext aVHashContext, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_hash_update(AVHashContext aVHashContext, @Cast({"const uint8_t*"}) byte[] bArr, int i);

    @NoException
    public static native void av_hash_update(AVHashContext aVHashContext, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    public static native AVHMAC av_hmac_alloc(@Cast({"AVHMACType"}) int i);

    @NoException
    public static native int av_hmac_calc(AVHMAC avhmac, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"unsigned int"}) int i, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, @Cast({"unsigned int"}) int i2, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, @Cast({"unsigned int"}) int i3);

    @NoException
    public static native int av_hmac_calc(AVHMAC avhmac, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i, @Cast({"const uint8_t*"}) BytePointer bytePointer2, @Cast({"unsigned int"}) int i2, @Cast({"uint8_t*"}) BytePointer bytePointer3, @Cast({"unsigned int"}) int i3);

    @NoException
    public static native int av_hmac_calc(AVHMAC avhmac, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"unsigned int"}) int i, @Cast({"const uint8_t*"}) byte[] bArr2, @Cast({"unsigned int"}) int i2, @Cast({"uint8_t*"}) byte[] bArr3, @Cast({"unsigned int"}) int i3);

    @NoException
    public static native int av_hmac_final(AVHMAC avhmac, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native int av_hmac_final(AVHMAC avhmac, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native int av_hmac_final(AVHMAC avhmac, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_hmac_free(AVHMAC avhmac);

    @NoException
    public static native void av_hmac_init(AVHMAC avhmac, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_hmac_init(AVHMAC avhmac, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_hmac_init(AVHMAC avhmac, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_hmac_update(AVHMAC avhmac, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_hmac_update(AVHMAC avhmac, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_hmac_update(AVHMAC avhmac, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"unsigned int"}) int i);

    @NoException
    public static native AVBufferRef av_hwdevice_ctx_alloc(@Cast({"AVHWDeviceType"}) int i);

    @NoException
    public static native int av_hwdevice_ctx_create(@ByPtrPtr AVBufferRef aVBufferRef, @Cast({"AVHWDeviceType"}) int i, String str, AVDictionary aVDictionary, int i2);

    @NoException
    public static native int av_hwdevice_ctx_create(@ByPtrPtr AVBufferRef aVBufferRef, @Cast({"AVHWDeviceType"}) int i, @Cast({"const char*"}) BytePointer bytePointer, AVDictionary aVDictionary, int i2);

    @NoException
    public static native int av_hwdevice_ctx_create(@Cast({"AVBufferRef**"}) PointerPointer pointerPointer, @Cast({"AVHWDeviceType"}) int i, @Cast({"const char*"}) BytePointer bytePointer, AVDictionary aVDictionary, int i2);

    @NoException
    public static native int av_hwdevice_ctx_create_derived(@ByPtrPtr AVBufferRef aVBufferRef, @Cast({"AVHWDeviceType"}) int i, AVBufferRef aVBufferRef2, int i2);

    @NoException
    public static native int av_hwdevice_ctx_create_derived(@Cast({"AVBufferRef**"}) PointerPointer pointerPointer, @Cast({"AVHWDeviceType"}) int i, AVBufferRef aVBufferRef, int i2);

    @NoException
    public static native int av_hwdevice_ctx_create_derived_opts(@ByPtrPtr AVBufferRef aVBufferRef, @Cast({"AVHWDeviceType"}) int i, AVBufferRef aVBufferRef2, AVDictionary aVDictionary, int i2);

    @NoException
    public static native int av_hwdevice_ctx_create_derived_opts(@Cast({"AVBufferRef**"}) PointerPointer pointerPointer, @Cast({"AVHWDeviceType"}) int i, AVBufferRef aVBufferRef, AVDictionary aVDictionary, int i2);

    @NoException
    public static native int av_hwdevice_ctx_init(AVBufferRef aVBufferRef);

    @NoException
    @Cast({"AVHWDeviceType"})
    public static native int av_hwdevice_find_type_by_name(String str);

    @NoException
    @Cast({"AVHWDeviceType"})
    public static native int av_hwdevice_find_type_by_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native AVHWFramesConstraints av_hwdevice_get_hwframe_constraints(AVBufferRef aVBufferRef, @Const Pointer pointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_hwdevice_get_type_name(@Cast({"AVHWDeviceType"}) int i);

    @NoException
    public static native Pointer av_hwdevice_hwconfig_alloc(AVBufferRef aVBufferRef);

    @NoException
    @Cast({"AVHWDeviceType"})
    public static native int av_hwdevice_iterate_types(@Cast({"AVHWDeviceType"}) int i);

    @NoException
    public static native void av_hwframe_constraints_free(@ByPtrPtr AVHWFramesConstraints aVHWFramesConstraints);

    @NoException
    public static native void av_hwframe_constraints_free(@Cast({"AVHWFramesConstraints**"}) PointerPointer pointerPointer);

    @NoException
    public static native AVBufferRef av_hwframe_ctx_alloc(AVBufferRef aVBufferRef);

    @NoException
    public static native int av_hwframe_ctx_create_derived(@ByPtrPtr AVBufferRef aVBufferRef, @Cast({"AVPixelFormat"}) int i, AVBufferRef aVBufferRef2, AVBufferRef aVBufferRef3, int i2);

    @NoException
    public static native int av_hwframe_ctx_create_derived(@Cast({"AVBufferRef**"}) PointerPointer pointerPointer, @Cast({"AVPixelFormat"}) int i, AVBufferRef aVBufferRef, AVBufferRef aVBufferRef2, int i2);

    @NoException
    public static native int av_hwframe_ctx_init(AVBufferRef aVBufferRef);

    @NoException
    public static native int av_hwframe_get_buffer(AVBufferRef aVBufferRef, AVFrame aVFrame, int i);

    @NoException
    public static native int av_hwframe_map(AVFrame aVFrame, @Const AVFrame aVFrame2, int i);

    @NoException
    public static native int av_hwframe_transfer_data(AVFrame aVFrame, @Const AVFrame aVFrame2, int i);

    @NoException
    public static native int av_hwframe_transfer_get_formats(AVBufferRef aVBufferRef, @Cast({"AVHWFrameTransferDirection"}) int i, @ByPtrPtr @Cast({"AVPixelFormat**"}) IntBuffer intBuffer, int i2);

    @NoException
    public static native int av_hwframe_transfer_get_formats(AVBufferRef aVBufferRef, @Cast({"AVHWFrameTransferDirection"}) int i, @ByPtrPtr @Cast({"AVPixelFormat**"}) IntPointer intPointer, int i2);

    @NoException
    public static native int av_hwframe_transfer_get_formats(AVBufferRef aVBufferRef, @Cast({"AVHWFrameTransferDirection"}) int i, @Cast({"AVPixelFormat**"}) PointerPointer pointerPointer, int i2);

    @NoException
    public static native int av_hwframe_transfer_get_formats(AVBufferRef aVBufferRef, @Cast({"AVHWFrameTransferDirection"}) int i, @ByPtrPtr @Cast({"AVPixelFormat**"}) int[] iArr, int i2);

    @NoException
    public static native int av_image_alloc(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, IntBuffer intBuffer, int i, int i2, @Cast({"AVPixelFormat"}) int i3, int i4);

    @NoException
    public static native int av_image_alloc(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, IntPointer intPointer, int i, int i2, @Cast({"AVPixelFormat"}) int i3, int i4);

    @NoException
    public static native int av_image_alloc(@Cast({"uint8_t**"}) PointerPointer pointerPointer, IntPointer intPointer, int i, int i2, @Cast({"AVPixelFormat"}) int i3, int i4);

    @NoException
    public static native int av_image_alloc(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, int[] iArr, int i, int i2, @Cast({"AVPixelFormat"}) int i3, int i4);

    @NoException
    public static native int av_image_check_sar(@Cast({"unsigned int"}) int i, @Cast({"unsigned int"}) int i2, @ByVal AVRational aVRational);

    @NoException
    public static native int av_image_check_size(@Cast({"unsigned int"}) int i, @Cast({"unsigned int"}) int i2, int i3, Pointer pointer);

    @NoException
    public static native int av_image_check_size2(@Cast({"unsigned int"}) int i, @Cast({"unsigned int"}) int i2, @Cast({"int64_t"}) long j, @Cast({"AVPixelFormat"}) int i3, int i4, Pointer pointer);

    @NoException
    public static native void av_image_copy(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, IntBuffer intBuffer, @ByPtrPtr @Cast({"const uint8_t**"}) ByteBuffer byteBuffer2, @Const IntBuffer intBuffer2, @Cast({"AVPixelFormat"}) int i, int i2, int i3);

    @NoException
    public static native void av_image_copy(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, IntPointer intPointer, @ByPtrPtr @Cast({"const uint8_t**"}) BytePointer bytePointer2, @Const IntPointer intPointer2, @Cast({"AVPixelFormat"}) int i, int i2, int i3);

    @NoException
    public static native void av_image_copy(@Cast({"uint8_t**"}) PointerPointer pointerPointer, IntPointer intPointer, @Cast({"const uint8_t**"}) PointerPointer pointerPointer2, @Const IntPointer intPointer2, @Cast({"AVPixelFormat"}) int i, int i2, int i3);

    @NoException
    public static native void av_image_copy(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, int[] iArr, @ByPtrPtr @Cast({"const uint8_t**"}) byte[] bArr2, @Const int[] iArr2, @Cast({"AVPixelFormat"}) int i, int i2, int i3);

    @NoException
    public static native void av_image_copy_plane(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, int i, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i2, int i3, int i4);

    @NoException
    public static native void av_image_copy_plane(@Cast({"uint8_t*"}) BytePointer bytePointer, int i, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i2, int i3, int i4);

    @NoException
    public static native void av_image_copy_plane(@Cast({"uint8_t*"}) byte[] bArr, int i, @Cast({"const uint8_t*"}) byte[] bArr2, int i2, int i3, int i4);

    @NoException
    public static native int av_image_copy_to_buffer(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, int i, @ByPtrPtr @Cast({"const uint8_t*const*"}) ByteBuffer byteBuffer2, @Const IntBuffer intBuffer, @Cast({"AVPixelFormat"}) int i2, int i3, int i4, int i5);

    @NoException
    public static native int av_image_copy_to_buffer(@Cast({"uint8_t*"}) BytePointer bytePointer, int i, @ByPtrPtr @Cast({"const uint8_t*const*"}) BytePointer bytePointer2, @Const IntPointer intPointer, @Cast({"AVPixelFormat"}) int i2, int i3, int i4, int i5);

    @NoException
    public static native int av_image_copy_to_buffer(@Cast({"uint8_t*"}) BytePointer bytePointer, int i, @Cast({"const uint8_t*const*"}) PointerPointer pointerPointer, @Const IntPointer intPointer, @Cast({"AVPixelFormat"}) int i2, int i3, int i4, int i5);

    @NoException
    public static native int av_image_copy_to_buffer(@Cast({"uint8_t*"}) byte[] bArr, int i, @ByPtrPtr @Cast({"const uint8_t*const*"}) byte[] bArr2, @Const int[] iArr, @Cast({"AVPixelFormat"}) int i2, int i3, int i4, int i5);

    @NoException
    public static native void av_image_copy_uc_from(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer, @ByPtrPtr @Cast({"const uint8_t**"}) ByteBuffer byteBuffer2, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer2, @Cast({"AVPixelFormat"}) int i, int i2, int i3);

    @NoException
    public static native void av_image_copy_uc_from(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer, @ByPtrPtr @Cast({"const uint8_t**"}) BytePointer bytePointer2, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer2, @Cast({"AVPixelFormat"}) int i, int i2, int i3);

    @NoException
    public static native void av_image_copy_uc_from(@Cast({"uint8_t**"}) PointerPointer pointerPointer, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer, @Cast({"const uint8_t**"}) PointerPointer pointerPointer2, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer2, @Cast({"AVPixelFormat"}) int i, int i2, int i3);

    @NoException
    public static native void av_image_copy_uc_from(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer, @ByPtrPtr @Cast({"const uint8_t**"}) byte[] bArr2, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer2, @Cast({"AVPixelFormat"}) int i, int i2, int i3);

    @NoException
    public static native int av_image_fill_arrays(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, IntBuffer intBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, @Cast({"AVPixelFormat"}) int i, int i2, int i3, int i4);

    @NoException
    public static native int av_image_fill_arrays(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, IntPointer intPointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, @Cast({"AVPixelFormat"}) int i, int i2, int i3, int i4);

    @NoException
    public static native int av_image_fill_arrays(@Cast({"uint8_t**"}) PointerPointer pointerPointer, IntPointer intPointer, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"AVPixelFormat"}) int i, int i2, int i3, int i4);

    @NoException
    public static native int av_image_fill_arrays(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, int[] iArr, @Cast({"const uint8_t*"}) byte[] bArr2, @Cast({"AVPixelFormat"}) int i, int i2, int i3, int i4);

    @NoException
    public static native int av_image_fill_black(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer, @Cast({"AVPixelFormat"}) int i, @Cast({"AVColorRange"}) int i2, int i3, int i4);

    @NoException
    public static native int av_image_fill_black(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer, @Cast({"AVPixelFormat"}) int i, @Cast({"AVColorRange"}) int i2, int i3, int i4);

    @NoException
    public static native int av_image_fill_black(@Cast({"uint8_t**"}) PointerPointer pointerPointer, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer, @Cast({"AVPixelFormat"}) int i, @Cast({"AVColorRange"}) int i2, int i3, int i4);

    @NoException
    public static native int av_image_fill_black(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, @Cast({"const ptrdiff_t*"}) SizeTPointer sizeTPointer, @Cast({"AVPixelFormat"}) int i, @Cast({"AVColorRange"}) int i2, int i3, int i4);

    @NoException
    public static native int av_image_fill_linesizes(IntBuffer intBuffer, @Cast({"AVPixelFormat"}) int i, int i2);

    @NoException
    public static native int av_image_fill_linesizes(IntPointer intPointer, @Cast({"AVPixelFormat"}) int i, int i2);

    @NoException
    public static native int av_image_fill_linesizes(int[] iArr, @Cast({"AVPixelFormat"}) int i, int i2);

    @NoException
    public static native void av_image_fill_max_pixsteps(IntBuffer intBuffer, IntBuffer intBuffer2, @Const AVPixFmtDescriptor aVPixFmtDescriptor);

    @NoException
    public static native void av_image_fill_max_pixsteps(IntPointer intPointer, IntPointer intPointer2, @Const AVPixFmtDescriptor aVPixFmtDescriptor);

    @NoException
    public static native void av_image_fill_max_pixsteps(int[] iArr, int[] iArr2, @Const AVPixFmtDescriptor aVPixFmtDescriptor);

    @NoException
    public static native int av_image_fill_pointers(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, @Cast({"AVPixelFormat"}) int i, int i2, @Cast({"uint8_t*"}) ByteBuffer byteBuffer2, @Const IntBuffer intBuffer);

    @NoException
    public static native int av_image_fill_pointers(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, @Cast({"AVPixelFormat"}) int i, int i2, @Cast({"uint8_t*"}) BytePointer bytePointer2, @Const IntPointer intPointer);

    @NoException
    public static native int av_image_fill_pointers(@Cast({"uint8_t**"}) PointerPointer pointerPointer, @Cast({"AVPixelFormat"}) int i, int i2, @Cast({"uint8_t*"}) BytePointer bytePointer, @Const IntPointer intPointer);

    @NoException
    public static native int av_image_fill_pointers(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, @Cast({"AVPixelFormat"}) int i, int i2, @Cast({"uint8_t*"}) byte[] bArr2, @Const int[] iArr);

    @NoException
    public static native int av_image_get_buffer_size(@Cast({"AVPixelFormat"}) int i, int i2, int i3, int i4);

    @NoException
    public static native int av_image_get_linesize(@Cast({"AVPixelFormat"}) int i, int i2, int i3);

    @NoException
    public static native double av_int2double(@Cast({"uint64_t"}) long j);

    @NoException
    public static native float av_int2float(@Cast({"uint32_t"}) int i);

    @NoException
    @Cast({"unsigned"})
    public static native int av_int_list_length_for_size(@Cast({"unsigned"}) int i, @Const Pointer pointer, @Cast({"uint64_t"}) long j);

    @NoException
    @ByVal
    public static native AVRational av_inv_q(@ByVal AVRational aVRational);

    @NoException
    @Const
    public static native int av_isdigit(int i);

    @NoException
    @Const
    public static native int av_isgraph(int i);

    @NoException
    @Const
    public static native int av_isspace(int i);

    @NoException
    @Const
    public static native int av_isxdigit(int i);

    @NoException
    @Cast({"unsigned int"})
    public static native int av_lfg_get(AVLFG avlfg);

    @NoException
    public static native void av_lfg_init(AVLFG avlfg, @Cast({"unsigned int"}) int i);

    @NoException
    public static native int av_lfg_init_from_data(AVLFG avlfg, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native int av_lfg_init_from_data(AVLFG avlfg, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native int av_lfg_init_from_data(AVLFG avlfg, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_log(Pointer pointer, int i, String str);

    @NoException
    public static native void av_log(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Const
    public static native int av_log2(@Cast({"unsigned"}) int i);

    @NoException
    @Const
    public static native int av_log2_16bit(@Cast({"unsigned"}) int i);

    @NoException
    public static native void av_log_default_callback(Pointer pointer, int i, String str, @Cast({"va_list*"}) @ByVal Pointer pointer2);

    @NoException
    public static native void av_log_default_callback(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2);

    @NoException
    public static native void av_log_format_line(Pointer pointer, int i, String str, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) ByteBuffer byteBuffer, int i2, IntBuffer intBuffer);

    @NoException
    public static native void av_log_format_line(Pointer pointer, int i, String str, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) BytePointer bytePointer, int i2, IntPointer intPointer);

    @NoException
    public static native void av_log_format_line(Pointer pointer, int i, String str, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) byte[] bArr, int i2, int[] iArr);

    @NoException
    public static native void av_log_format_line(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) ByteBuffer byteBuffer, int i2, IntBuffer intBuffer);

    @NoException
    public static native void av_log_format_line(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) BytePointer bytePointer2, int i2, IntPointer intPointer);

    @NoException
    public static native void av_log_format_line(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) byte[] bArr, int i2, int[] iArr);

    @NoException
    public static native int av_log_format_line2(Pointer pointer, int i, String str, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) ByteBuffer byteBuffer, int i2, IntBuffer intBuffer);

    @NoException
    public static native int av_log_format_line2(Pointer pointer, int i, String str, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) BytePointer bytePointer, int i2, IntPointer intPointer);

    @NoException
    public static native int av_log_format_line2(Pointer pointer, int i, String str, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) byte[] bArr, int i2, int[] iArr);

    @NoException
    public static native int av_log_format_line2(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) ByteBuffer byteBuffer, int i2, IntBuffer intBuffer);

    @NoException
    public static native int av_log_format_line2(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) BytePointer bytePointer2, int i2, IntPointer intPointer);

    @NoException
    public static native int av_log_format_line2(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2, @Cast({"char*"}) byte[] bArr, int i2, int[] iArr);

    @NoException
    public static native int av_log_get_flags();

    @NoException
    public static native int av_log_get_level();

    @NoException
    public static native void av_log_once(Pointer pointer, int i, int i2, IntBuffer intBuffer, String str);

    @NoException
    public static native void av_log_once(Pointer pointer, int i, int i2, IntBuffer intBuffer, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native void av_log_once(Pointer pointer, int i, int i2, IntPointer intPointer, String str);

    @NoException
    public static native void av_log_once(Pointer pointer, int i, int i2, IntPointer intPointer, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native void av_log_once(Pointer pointer, int i, int i2, int[] iArr, String str);

    @NoException
    public static native void av_log_once(Pointer pointer, int i, int i2, int[] iArr, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native void av_log_set_callback(Callback_Pointer_int_BytePointer_Pointer callback_Pointer_int_BytePointer_Pointer);

    @NoException
    public static native void av_log_set_callback(Callback_Pointer_int_String_Pointer callback_Pointer_int_String_Pointer);

    @NoException
    public static native void av_log_set_flags(int i);

    @NoException
    public static native void av_log_set_level(int i);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_make_error_string(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, int i);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_make_error_string(@Cast({"char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, int i);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_make_error_string(@Cast({"char*"}) byte[] bArr, @Cast({"size_t"}) long j, int i);

    @NoException
    @ByVal
    public static native AVRational av_make_q(int i, int i2);

    @NoException
    public static native Pointer av_malloc(@Cast({"size_t"}) long j);

    @NoException
    public static native Pointer av_malloc_array(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    @NoException
    public static native Pointer av_mallocz(@Cast({"size_t"}) long j);

    @NoException
    public static native Pointer av_mallocz_array(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    @NoException
    public static native AVMasteringDisplayMetadata av_mastering_display_metadata_alloc();

    @NoException
    public static native AVMasteringDisplayMetadata av_mastering_display_metadata_create_side_data(AVFrame aVFrame);

    @NoException
    public static native int av_match_list(String str, String str2, @Cast({"char"}) byte b);

    @NoException
    public static native int av_match_list(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"char"}) byte b);

    @NoException
    public static native int av_match_name(String str, String str2);

    @NoException
    public static native int av_match_name(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    public static native void av_max_alloc(@Cast({"size_t"}) long j);

    @NoException
    public static native AVMD5 av_md5_alloc();

    @NoException
    public static native void av_md5_final(AVMD5 avmd5, @Cast({"uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_md5_final(AVMD5 avmd5, @Cast({"uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_md5_final(AVMD5 avmd5, @Cast({"uint8_t*"}) byte[] bArr);

    @NoException
    public static native void av_md5_init(AVMD5 avmd5);

    @MemberGetter
    public static native int av_md5_size();

    @NoException
    public static native void av_md5_sum(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i);

    @NoException
    public static native void av_md5_sum(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_md5_sum(@Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i);

    @NoException
    public static native void av_md5_sum(@Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_md5_sum(@Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i);

    @NoException
    public static native void av_md5_sum(@Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_md5_update(AVMD5 avmd5, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void av_md5_update(AVMD5 avmd5, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_md5_update(AVMD5 avmd5, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void av_md5_update(AVMD5 avmd5, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_md5_update(AVMD5 avmd5, @Cast({"const uint8_t*"}) byte[] bArr, int i);

    @NoException
    public static native void av_md5_update(AVMD5 avmd5, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_memcpy_backptr(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native void av_memcpy_backptr(@Cast({"uint8_t*"}) BytePointer bytePointer, int i, int i2);

    @NoException
    public static native void av_memcpy_backptr(@Cast({"uint8_t*"}) byte[] bArr, int i, int i2);

    @NoException
    public static native Pointer av_memdup(@Const Pointer pointer, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"unsigned int"})
    public static native int av_mlfg_get(AVLFG avlfg);

    @NoException
    @Cast({"unsigned"})
    @Const
    public static native int av_mod_uintp2_c(@Cast({"unsigned"}) int i, @Cast({"unsigned"}) int i2);

    @NoException
    @Const
    @ByVal
    public static native AVRational av_mul_q(@ByVal AVRational aVRational, @ByVal AVRational aVRational2);

    @NoException
    @Cast({"AVMurMur3*"})
    public static native Pointer av_murmur3_alloc();

    @NoException
    public static native void av_murmur3_final(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_murmur3_final(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_murmur3_final(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"uint8_t*"}) byte[] bArr);

    @NoException
    public static native void av_murmur3_init(@Cast({"AVMurMur3*"}) Pointer pointer);

    @NoException
    public static native void av_murmur3_init_seeded(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"uint64_t"}) long j);

    @NoException
    public static native void av_murmur3_update(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void av_murmur3_update(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_murmur3_update(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void av_murmur3_update(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_murmur3_update(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"const uint8_t*"}) byte[] bArr, int i);

    @NoException
    public static native void av_murmur3_update(@Cast({"AVMurMur3*"}) Pointer pointer, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_nearer_q(@ByVal AVRational aVRational, @ByVal AVRational aVRational2, @ByVal AVRational aVRational3);

    @NoException
    @Const
    public static native AVClass av_opt_child_class_next(@Const AVClass aVClass, @Const AVClass aVClass2);

    @NoException
    public static native Pointer av_opt_child_next(Pointer pointer, Pointer pointer2);

    @NoException
    public static native int av_opt_copy(Pointer pointer, @Const Pointer pointer2);

    @NoException
    public static native int av_opt_eval_double(Pointer pointer, @Const AVOption aVOption, String str, DoubleBuffer doubleBuffer);

    @NoException
    public static native int av_opt_eval_double(Pointer pointer, @Const AVOption aVOption, String str, DoublePointer doublePointer);

    @NoException
    public static native int av_opt_eval_double(Pointer pointer, @Const AVOption aVOption, String str, double[] dArr);

    @NoException
    public static native int av_opt_eval_double(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, DoubleBuffer doubleBuffer);

    @NoException
    public static native int av_opt_eval_double(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, DoublePointer doublePointer);

    @NoException
    public static native int av_opt_eval_double(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, double[] dArr);

    @NoException
    public static native int av_opt_eval_flags(Pointer pointer, @Const AVOption aVOption, String str, IntBuffer intBuffer);

    @NoException
    public static native int av_opt_eval_flags(Pointer pointer, @Const AVOption aVOption, String str, IntPointer intPointer);

    @NoException
    public static native int av_opt_eval_flags(Pointer pointer, @Const AVOption aVOption, String str, int[] iArr);

    @NoException
    public static native int av_opt_eval_flags(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, IntBuffer intBuffer);

    @NoException
    public static native int av_opt_eval_flags(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, IntPointer intPointer);

    @NoException
    public static native int av_opt_eval_flags(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, int[] iArr);

    @NoException
    public static native int av_opt_eval_float(Pointer pointer, @Const AVOption aVOption, String str, FloatBuffer floatBuffer);

    @NoException
    public static native int av_opt_eval_float(Pointer pointer, @Const AVOption aVOption, String str, FloatPointer floatPointer);

    @NoException
    public static native int av_opt_eval_float(Pointer pointer, @Const AVOption aVOption, String str, float[] fArr);

    @NoException
    public static native int av_opt_eval_float(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, FloatBuffer floatBuffer);

    @NoException
    public static native int av_opt_eval_float(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, FloatPointer floatPointer);

    @NoException
    public static native int av_opt_eval_float(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, float[] fArr);

    @NoException
    public static native int av_opt_eval_int(Pointer pointer, @Const AVOption aVOption, String str, IntBuffer intBuffer);

    @NoException
    public static native int av_opt_eval_int(Pointer pointer, @Const AVOption aVOption, String str, IntPointer intPointer);

    @NoException
    public static native int av_opt_eval_int(Pointer pointer, @Const AVOption aVOption, String str, int[] iArr);

    @NoException
    public static native int av_opt_eval_int(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, IntBuffer intBuffer);

    @NoException
    public static native int av_opt_eval_int(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, IntPointer intPointer);

    @NoException
    public static native int av_opt_eval_int(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, int[] iArr);

    @NoException
    public static native int av_opt_eval_int64(Pointer pointer, @Const AVOption aVOption, String str, @Cast({"int64_t*"}) LongBuffer longBuffer);

    @NoException
    public static native int av_opt_eval_int64(Pointer pointer, @Const AVOption aVOption, String str, @Cast({"int64_t*"}) LongPointer longPointer);

    @NoException
    public static native int av_opt_eval_int64(Pointer pointer, @Const AVOption aVOption, String str, @Cast({"int64_t*"}) long[] jArr);

    @NoException
    public static native int av_opt_eval_int64(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"int64_t*"}) LongBuffer longBuffer);

    @NoException
    public static native int av_opt_eval_int64(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"int64_t*"}) LongPointer longPointer);

    @NoException
    public static native int av_opt_eval_int64(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"int64_t*"}) long[] jArr);

    @NoException
    public static native int av_opt_eval_q(Pointer pointer, @Const AVOption aVOption, String str, AVRational aVRational);

    @NoException
    public static native int av_opt_eval_q(Pointer pointer, @Const AVOption aVOption, @Cast({"const char*"}) BytePointer bytePointer, AVRational aVRational);

    @NoException
    @Const
    public static native AVOption av_opt_find(Pointer pointer, String str, String str2, int i, int i2);

    @NoException
    @Const
    public static native AVOption av_opt_find(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i, int i2);

    @NoException
    @Const
    public static native AVOption av_opt_find2(Pointer pointer, String str, String str2, int i, int i2, @ByPtrPtr @Cast({"void**"}) Pointer pointer2);

    @NoException
    @Const
    public static native AVOption av_opt_find2(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i, int i2, @ByPtrPtr @Cast({"void**"}) Pointer pointer2);

    @NoException
    @Const
    public static native AVOption av_opt_find2(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i, int i2, @Cast({"void**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_opt_flag_is_set(Pointer pointer, String str, String str2);

    @NoException
    public static native int av_opt_flag_is_set(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    public static native void av_opt_free(Pointer pointer);

    @NoException
    public static native void av_opt_freep_ranges(@ByPtrPtr AVOptionRanges aVOptionRanges);

    @NoException
    public static native void av_opt_freep_ranges(@Cast({"AVOptionRanges**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_opt_get(Pointer pointer, String str, int i, @ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int av_opt_get(Pointer pointer, String str, int i, @ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer);

    @NoException
    public static native int av_opt_get(Pointer pointer, String str, int i, @ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr);

    @NoException
    public static native int av_opt_get(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int av_opt_get(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer2);

    @NoException
    public static native int av_opt_get(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"uint8_t**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_opt_get(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr);

    @NoException
    public static native int av_opt_get_channel_layout(Pointer pointer, String str, int i, @Cast({"int64_t*"}) LongBuffer longBuffer);

    @NoException
    public static native int av_opt_get_channel_layout(Pointer pointer, String str, int i, @Cast({"int64_t*"}) LongPointer longPointer);

    @NoException
    public static native int av_opt_get_channel_layout(Pointer pointer, String str, int i, @Cast({"int64_t*"}) long[] jArr);

    @NoException
    public static native int av_opt_get_channel_layout(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"int64_t*"}) LongBuffer longBuffer);

    @NoException
    public static native int av_opt_get_channel_layout(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"int64_t*"}) LongPointer longPointer);

    @NoException
    public static native int av_opt_get_channel_layout(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"int64_t*"}) long[] jArr);

    @NoException
    public static native int av_opt_get_dict_val(Pointer pointer, String str, int i, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int av_opt_get_dict_val(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int av_opt_get_dict_val(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"AVDictionary**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_opt_get_double(Pointer pointer, String str, int i, DoubleBuffer doubleBuffer);

    @NoException
    public static native int av_opt_get_double(Pointer pointer, String str, int i, DoublePointer doublePointer);

    @NoException
    public static native int av_opt_get_double(Pointer pointer, String str, int i, double[] dArr);

    @NoException
    public static native int av_opt_get_double(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, DoubleBuffer doubleBuffer);

    @NoException
    public static native int av_opt_get_double(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, DoublePointer doublePointer);

    @NoException
    public static native int av_opt_get_double(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, double[] dArr);

    @NoException
    public static native int av_opt_get_image_size(Pointer pointer, String str, int i, IntBuffer intBuffer, IntBuffer intBuffer2);

    @NoException
    public static native int av_opt_get_image_size(Pointer pointer, String str, int i, IntPointer intPointer, IntPointer intPointer2);

    @NoException
    public static native int av_opt_get_image_size(Pointer pointer, String str, int i, int[] iArr, int[] iArr2);

    @NoException
    public static native int av_opt_get_image_size(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, IntBuffer intBuffer, IntBuffer intBuffer2);

    @NoException
    public static native int av_opt_get_image_size(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, IntPointer intPointer, IntPointer intPointer2);

    @NoException
    public static native int av_opt_get_image_size(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, int[] iArr, int[] iArr2);

    @NoException
    public static native int av_opt_get_int(Pointer pointer, String str, int i, @Cast({"int64_t*"}) LongBuffer longBuffer);

    @NoException
    public static native int av_opt_get_int(Pointer pointer, String str, int i, @Cast({"int64_t*"}) LongPointer longPointer);

    @NoException
    public static native int av_opt_get_int(Pointer pointer, String str, int i, @Cast({"int64_t*"}) long[] jArr);

    @NoException
    public static native int av_opt_get_int(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"int64_t*"}) LongBuffer longBuffer);

    @NoException
    public static native int av_opt_get_int(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"int64_t*"}) LongPointer longPointer);

    @NoException
    public static native int av_opt_get_int(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"int64_t*"}) long[] jArr);

    @NoException
    public static native int av_opt_get_key_value(@ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer, String str, String str2, @Cast({"unsigned"}) int i, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer2, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer3);

    @NoException
    public static native int av_opt_get_key_value(@ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"unsigned"}) int i, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer2, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer3);

    @NoException
    public static native int av_opt_get_key_value(@ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer, String str, String str2, @Cast({"unsigned"}) int i, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer3);

    @NoException
    public static native int av_opt_get_key_value(@ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, @Cast({"unsigned"}) int i, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer4, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer5);

    @NoException
    public static native int av_opt_get_key_value(@Cast({"const char**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"unsigned"}) int i, @Cast({"char**"}) PointerPointer pointerPointer2, @Cast({"char**"}) PointerPointer pointerPointer3);

    @NoException
    public static native int av_opt_get_key_value(@ByPtrPtr @Cast({"const char**"}) byte[] bArr, String str, String str2, @Cast({"unsigned"}) int i, @ByPtrPtr @Cast({"char**"}) byte[] bArr2, @ByPtrPtr @Cast({"char**"}) byte[] bArr3);

    @NoException
    public static native int av_opt_get_key_value(@ByPtrPtr @Cast({"const char**"}) byte[] bArr, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"unsigned"}) int i, @ByPtrPtr @Cast({"char**"}) byte[] bArr2, @ByPtrPtr @Cast({"char**"}) byte[] bArr3);

    @NoException
    public static native int av_opt_get_pixel_fmt(Pointer pointer, String str, int i, @Cast({"AVPixelFormat*"}) IntBuffer intBuffer);

    @NoException
    public static native int av_opt_get_pixel_fmt(Pointer pointer, String str, int i, @Cast({"AVPixelFormat*"}) IntPointer intPointer);

    @NoException
    public static native int av_opt_get_pixel_fmt(Pointer pointer, String str, int i, @Cast({"AVPixelFormat*"}) int[] iArr);

    @NoException
    public static native int av_opt_get_pixel_fmt(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"AVPixelFormat*"}) IntBuffer intBuffer);

    @NoException
    public static native int av_opt_get_pixel_fmt(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"AVPixelFormat*"}) IntPointer intPointer);

    @NoException
    public static native int av_opt_get_pixel_fmt(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"AVPixelFormat*"}) int[] iArr);

    @NoException
    public static native int av_opt_get_q(Pointer pointer, String str, int i, AVRational aVRational);

    @NoException
    public static native int av_opt_get_q(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, AVRational aVRational);

    @NoException
    public static native int av_opt_get_sample_fmt(Pointer pointer, String str, int i, @Cast({"AVSampleFormat*"}) IntBuffer intBuffer);

    @NoException
    public static native int av_opt_get_sample_fmt(Pointer pointer, String str, int i, @Cast({"AVSampleFormat*"}) IntPointer intPointer);

    @NoException
    public static native int av_opt_get_sample_fmt(Pointer pointer, String str, int i, @Cast({"AVSampleFormat*"}) int[] iArr);

    @NoException
    public static native int av_opt_get_sample_fmt(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"AVSampleFormat*"}) IntBuffer intBuffer);

    @NoException
    public static native int av_opt_get_sample_fmt(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"AVSampleFormat*"}) IntPointer intPointer);

    @NoException
    public static native int av_opt_get_sample_fmt(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Cast({"AVSampleFormat*"}) int[] iArr);

    @NoException
    public static native int av_opt_get_video_rate(Pointer pointer, String str, int i, AVRational aVRational);

    @NoException
    public static native int av_opt_get_video_rate(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, AVRational aVRational);

    @NoException
    public static native int av_opt_is_set_to_default(Pointer pointer, @Const AVOption aVOption);

    @NoException
    public static native int av_opt_is_set_to_default_by_name(Pointer pointer, String str, int i);

    @NoException
    public static native int av_opt_is_set_to_default_by_name(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    @Const
    public static native AVOption av_opt_next(@Const Pointer pointer, @Const AVOption aVOption);

    @NoException
    public static native Pointer av_opt_ptr(@Const AVClass aVClass, Pointer pointer, String str);

    @NoException
    public static native Pointer av_opt_ptr(@Const AVClass aVClass, Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_opt_query_ranges(@ByPtrPtr AVOptionRanges aVOptionRanges, Pointer pointer, String str, int i);

    @NoException
    public static native int av_opt_query_ranges(@ByPtrPtr AVOptionRanges aVOptionRanges, Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_opt_query_ranges(@Cast({"AVOptionRanges**"}) PointerPointer pointerPointer, Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_opt_query_ranges_default(@ByPtrPtr AVOptionRanges aVOptionRanges, Pointer pointer, String str, int i);

    @NoException
    public static native int av_opt_query_ranges_default(@ByPtrPtr AVOptionRanges aVOptionRanges, Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_opt_query_ranges_default(@Cast({"AVOptionRanges**"}) PointerPointer pointerPointer, Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_opt_serialize(Pointer pointer, int i, int i2, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer, byte b, byte b2);

    @NoException
    public static native int av_opt_serialize(Pointer pointer, int i, int i2, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer, byte b, byte b2);

    @NoException
    public static native int av_opt_serialize(Pointer pointer, int i, int i2, @Cast({"char**"}) PointerPointer pointerPointer, byte b, byte b2);

    @NoException
    public static native int av_opt_serialize(Pointer pointer, int i, int i2, @ByPtrPtr @Cast({"char**"}) byte[] bArr, byte b, byte b2);

    @NoException
    public static native int av_opt_set(Pointer pointer, String str, String str2, int i);

    @NoException
    public static native int av_opt_set(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i);

    @NoException
    public static native int av_opt_set_bin(Pointer pointer, String str, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native int av_opt_set_bin(Pointer pointer, String str, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i, int i2);

    @NoException
    public static native int av_opt_set_bin(Pointer pointer, String str, @Cast({"const uint8_t*"}) byte[] bArr, int i, int i2);

    @NoException
    public static native int av_opt_set_bin(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native int av_opt_set_bin(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, int i2);

    @NoException
    public static native int av_opt_set_bin(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) byte[] bArr, int i, int i2);

    @NoException
    public static native int av_opt_set_channel_layout(Pointer pointer, String str, @Cast({"int64_t"}) long j, int i);

    @NoException
    public static native int av_opt_set_channel_layout(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"int64_t"}) long j, int i);

    @NoException
    public static native void av_opt_set_defaults(Pointer pointer);

    @NoException
    public static native void av_opt_set_defaults2(Pointer pointer, int i, int i2);

    @NoException
    public static native int av_opt_set_dict(Pointer pointer, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int av_opt_set_dict(Pointer pointer, @Cast({"AVDictionary**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_opt_set_dict2(Pointer pointer, @ByPtrPtr AVDictionary aVDictionary, int i);

    @NoException
    public static native int av_opt_set_dict2(Pointer pointer, @Cast({"AVDictionary**"}) PointerPointer pointerPointer, int i);

    @NoException
    public static native int av_opt_set_dict_val(Pointer pointer, String str, @Const AVDictionary aVDictionary, int i);

    @NoException
    public static native int av_opt_set_dict_val(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Const AVDictionary aVDictionary, int i);

    @NoException
    public static native int av_opt_set_double(Pointer pointer, String str, double d, int i);

    @NoException
    public static native int av_opt_set_double(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, double d, int i);

    @NoException
    public static native int av_opt_set_from_string(Pointer pointer, String str, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer, String str2, String str3);

    @NoException
    public static native int av_opt_set_from_string(Pointer pointer, String str, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer, String str2, String str3);

    @NoException
    public static native int av_opt_set_from_string(Pointer pointer, String str, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr, String str2, String str3);

    @NoException
    public static native int av_opt_set_from_string(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) ByteBuffer byteBuffer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3);

    @NoException
    public static native int av_opt_set_from_string(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, @Cast({"const char*"}) BytePointer bytePointer4);

    @NoException
    public static native int av_opt_set_from_string(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*const*"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3);

    @NoException
    public static native int av_opt_set_from_string(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"const char*const*"}) byte[] bArr, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3);

    @NoException
    public static native int av_opt_set_image_size(Pointer pointer, String str, int i, int i2, int i3);

    @NoException
    public static native int av_opt_set_image_size(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i, int i2, int i3);

    @NoException
    public static native int av_opt_set_int(Pointer pointer, String str, @Cast({"int64_t"}) long j, int i);

    @NoException
    public static native int av_opt_set_int(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"int64_t"}) long j, int i);

    @NoException
    public static native int av_opt_set_pixel_fmt(Pointer pointer, String str, @Cast({"AVPixelFormat"}) int i, int i2);

    @NoException
    public static native int av_opt_set_pixel_fmt(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"AVPixelFormat"}) int i, int i2);

    @NoException
    public static native int av_opt_set_q(Pointer pointer, String str, @ByVal AVRational aVRational, int i);

    @NoException
    public static native int av_opt_set_q(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @ByVal AVRational aVRational, int i);

    @NoException
    public static native int av_opt_set_sample_fmt(Pointer pointer, String str, @Cast({"AVSampleFormat"}) int i, int i2);

    @NoException
    public static native int av_opt_set_sample_fmt(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"AVSampleFormat"}) int i, int i2);

    @NoException
    public static native int av_opt_set_video_rate(Pointer pointer, String str, @ByVal AVRational aVRational, int i);

    @NoException
    public static native int av_opt_set_video_rate(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @ByVal AVRational aVRational, int i);

    @NoException
    public static native int av_opt_show2(Pointer pointer, Pointer pointer2, int i, int i2);

    @NoException
    @Const
    public static native int av_parity_c(@Cast({"uint32_t"}) int i);

    @NoException
    public static native int av_parse_color(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, String str, int i, Pointer pointer);

    @NoException
    public static native int av_parse_color(@Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const char*"}) BytePointer bytePointer, int i, Pointer pointer);

    @NoException
    public static native int av_parse_color(@Cast({"uint8_t*"}) BytePointer bytePointer, String str, int i, Pointer pointer);

    @NoException
    public static native int av_parse_color(@Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i, Pointer pointer);

    @NoException
    public static native int av_parse_color(@Cast({"uint8_t*"}) byte[] bArr, String str, int i, Pointer pointer);

    @NoException
    public static native int av_parse_color(@Cast({"uint8_t*"}) byte[] bArr, @Cast({"const char*"}) BytePointer bytePointer, int i, Pointer pointer);

    @NoException
    public static native int av_parse_cpu_caps(@Cast({"unsigned*"}) IntBuffer intBuffer, String str);

    @NoException
    public static native int av_parse_cpu_caps(@Cast({"unsigned*"}) IntBuffer intBuffer, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_parse_cpu_caps(@Cast({"unsigned*"}) IntPointer intPointer, String str);

    @NoException
    public static native int av_parse_cpu_caps(@Cast({"unsigned*"}) IntPointer intPointer, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_parse_cpu_caps(@Cast({"unsigned*"}) int[] iArr, String str);

    @NoException
    public static native int av_parse_cpu_caps(@Cast({"unsigned*"}) int[] iArr, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Deprecated
    public static native int av_parse_cpu_flags(String str);

    @NoException
    @Deprecated
    public static native int av_parse_cpu_flags(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_parse_ratio(AVRational aVRational, String str, int i, int i2, Pointer pointer);

    @NoException
    public static native int av_parse_ratio(AVRational aVRational, @Cast({"const char*"}) BytePointer bytePointer, int i, int i2, Pointer pointer);

    @NoException
    public static native int av_parse_time(@Cast({"int64_t*"}) LongBuffer longBuffer, String str, int i);

    @NoException
    public static native int av_parse_time(@Cast({"int64_t*"}) LongBuffer longBuffer, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_parse_time(@Cast({"int64_t*"}) LongPointer longPointer, String str, int i);

    @NoException
    public static native int av_parse_time(@Cast({"int64_t*"}) LongPointer longPointer, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_parse_time(@Cast({"int64_t*"}) long[] jArr, String str, int i);

    @NoException
    public static native int av_parse_time(@Cast({"int64_t*"}) long[] jArr, @Cast({"const char*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_parse_video_rate(AVRational aVRational, String str);

    @NoException
    public static native int av_parse_video_rate(AVRational aVRational, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_parse_video_size(IntBuffer intBuffer, IntBuffer intBuffer2, String str);

    @NoException
    public static native int av_parse_video_size(IntBuffer intBuffer, IntBuffer intBuffer2, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_parse_video_size(IntPointer intPointer, IntPointer intPointer2, String str);

    @NoException
    public static native int av_parse_video_size(IntPointer intPointer, IntPointer intPointer2, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_parse_video_size(int[] iArr, int[] iArr2, String str);

    @NoException
    public static native int av_parse_video_size(int[] iArr, int[] iArr2, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_pix_fmt_count_planes(@Cast({"AVPixelFormat"}) int i);

    @NoException
    @Const
    public static native AVPixFmtDescriptor av_pix_fmt_desc_get(@Cast({"AVPixelFormat"}) int i);

    @NoException
    @Cast({"AVPixelFormat"})
    public static native int av_pix_fmt_desc_get_id(@Const AVPixFmtDescriptor aVPixFmtDescriptor);

    @NoException
    @Const
    public static native AVPixFmtDescriptor av_pix_fmt_desc_next(@Const AVPixFmtDescriptor aVPixFmtDescriptor);

    @NoException
    public static native int av_pix_fmt_get_chroma_sub_sample(@Cast({"AVPixelFormat"}) int i, IntBuffer intBuffer, IntBuffer intBuffer2);

    @NoException
    public static native int av_pix_fmt_get_chroma_sub_sample(@Cast({"AVPixelFormat"}) int i, IntPointer intPointer, IntPointer intPointer2);

    @NoException
    public static native int av_pix_fmt_get_chroma_sub_sample(@Cast({"AVPixelFormat"}) int i, int[] iArr, int[] iArr2);

    @NoException
    @Cast({"AVPixelFormat"})
    public static native int av_pix_fmt_swap_endianness(@Cast({"AVPixelFormat"}) int i);

    @NoException
    public static native av_pixelutils_sad_fn av_pixelutils_get_sad_fn(int i, int i2, int i3, Pointer pointer);

    @NoException
    @Const
    public static native int av_popcount64_c(@Cast({"uint64_t"}) long j);

    @NoException
    @Const
    public static native int av_popcount_c(@Cast({"uint32_t"}) int i);

    @NoException
    public static native double av_q2d(@ByVal AVRational aVRational);

    @NoException
    @Cast({"uint32_t"})
    public static native int av_q2intfloat(@ByVal AVRational aVRational);

    @NoException
    public static native AVRC4 av_rc4_alloc();

    @NoException
    public static native void av_rc4_crypt(AVRC4 avrc4, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_rc4_crypt(AVRC4 avrc4, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_rc4_crypt(AVRC4 avrc4, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native int av_rc4_init(AVRC4 avrc4, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native int av_rc4_init(AVRC4 avrc4, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i, int i2);

    @NoException
    public static native int av_rc4_init(AVRC4 avrc4, @Cast({"const uint8_t*"}) byte[] bArr, int i, int i2);

    @NoException
    public static native void av_read_image_line(@Cast({"uint16_t*"}) ShortBuffer shortBuffer, @ByPtrPtr @Cast({"const uint8_t**"}) ByteBuffer byteBuffer, @Const IntBuffer intBuffer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5);

    @NoException
    public static native void av_read_image_line(@Cast({"uint16_t*"}) ShortPointer shortPointer, @ByPtrPtr @Cast({"const uint8_t**"}) BytePointer bytePointer, @Const IntPointer intPointer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5);

    @NoException
    public static native void av_read_image_line(@Cast({"uint16_t*"}) ShortPointer shortPointer, @Cast({"const uint8_t**"}) PointerPointer pointerPointer, @Const IntPointer intPointer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5);

    @NoException
    public static native void av_read_image_line(@Cast({"uint16_t*"}) short[] sArr, @ByPtrPtr @Cast({"const uint8_t**"}) byte[] bArr, @Const int[] iArr, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5);

    @NoException
    public static native void av_read_image_line2(Pointer pointer, @ByPtrPtr @Cast({"const uint8_t**"}) ByteBuffer byteBuffer, @Const IntBuffer intBuffer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5, int i6);

    @NoException
    public static native void av_read_image_line2(Pointer pointer, @ByPtrPtr @Cast({"const uint8_t**"}) BytePointer bytePointer, @Const IntPointer intPointer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5, int i6);

    @NoException
    public static native void av_read_image_line2(Pointer pointer, @Cast({"const uint8_t**"}) PointerPointer pointerPointer, @Const IntPointer intPointer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5, int i6);

    @NoException
    public static native void av_read_image_line2(Pointer pointer, @ByPtrPtr @Cast({"const uint8_t**"}) byte[] bArr, @Const int[] iArr, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5, int i6);

    @NoException
    public static native Pointer av_realloc(Pointer pointer, @Cast({"size_t"}) long j);

    @NoException
    public static native Pointer av_realloc_array(Pointer pointer, @Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    @NoException
    public static native Pointer av_realloc_f(Pointer pointer, @Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    @NoException
    public static native int av_reallocp(Pointer pointer, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_reallocp_array(Pointer pointer, @Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    @NoException
    public static native int av_reduce(IntBuffer intBuffer, IntBuffer intBuffer2, @Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2, @Cast({"int64_t"}) long j3);

    @NoException
    public static native int av_reduce(IntPointer intPointer, IntPointer intPointer2, @Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2, @Cast({"int64_t"}) long j3);

    @NoException
    public static native int av_reduce(int[] iArr, int[] iArr2, @Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2, @Cast({"int64_t"}) long j3);

    @NoException
    @Cast({"int64_t"})
    @Const
    public static native long av_rescale(@Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2, @Cast({"int64_t"}) long j3);

    @NoException
    @Cast({"int64_t"})
    public static native long av_rescale_delta(@ByVal AVRational aVRational, @Cast({"int64_t"}) long j, @ByVal AVRational aVRational2, int i, @Cast({"int64_t*"}) LongBuffer longBuffer, @ByVal AVRational aVRational3);

    @NoException
    @Cast({"int64_t"})
    public static native long av_rescale_delta(@ByVal AVRational aVRational, @Cast({"int64_t"}) long j, @ByVal AVRational aVRational2, int i, @Cast({"int64_t*"}) LongPointer longPointer, @ByVal AVRational aVRational3);

    @NoException
    @Cast({"int64_t"})
    public static native long av_rescale_delta(@ByVal AVRational aVRational, @Cast({"int64_t"}) long j, @ByVal AVRational aVRational2, int i, @Cast({"int64_t*"}) long[] jArr, @ByVal AVRational aVRational3);

    @NoException
    @Cast({"int64_t"})
    @Const
    public static native long av_rescale_q(@Cast({"int64_t"}) long j, @ByVal AVRational aVRational, @ByVal AVRational aVRational2);

    @NoException
    @Cast({"int64_t"})
    @Const
    public static native long av_rescale_q_rnd(@Cast({"int64_t"}) long j, @ByVal AVRational aVRational, @ByVal AVRational aVRational2, @Cast({"AVRounding"}) int i);

    @NoException
    @Cast({"int64_t"})
    @Const
    public static native long av_rescale_rnd(@Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2, @Cast({"int64_t"}) long j3, @Cast({"AVRounding"}) int i);

    @NoException
    public static native AVRIPEMD av_ripemd_alloc();

    @NoException
    public static native void av_ripemd_final(AVRIPEMD avripemd, @Cast({"uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_ripemd_final(AVRIPEMD avripemd, @Cast({"uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_ripemd_final(AVRIPEMD avripemd, @Cast({"uint8_t*"}) byte[] bArr);

    @NoException
    public static native int av_ripemd_init(AVRIPEMD avripemd, int i);

    @MemberGetter
    public static native int av_ripemd_size();

    @NoException
    public static native void av_ripemd_update(AVRIPEMD avripemd, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_ripemd_update(AVRIPEMD avripemd, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_ripemd_update(AVRIPEMD avripemd, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_ripemd_update(AVRIPEMD avripemd, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_ripemd_update(AVRIPEMD avripemd, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_ripemd_update(AVRIPEMD avripemd, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_sample_fmt_is_planar(@Cast({"AVSampleFormat"}) int i);

    @NoException
    public static native int av_samples_alloc(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, IntBuffer intBuffer, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_alloc(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, IntPointer intPointer, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_alloc(@Cast({"uint8_t**"}) PointerPointer pointerPointer, IntPointer intPointer, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_alloc(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, int[] iArr, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_alloc_array_and_samples(@ByPtrPtr @Cast({"uint8_t***"}) PointerPointer pointerPointer, IntBuffer intBuffer, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_alloc_array_and_samples(@ByPtrPtr @Cast({"uint8_t***"}) PointerPointer pointerPointer, IntPointer intPointer, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_alloc_array_and_samples(@ByPtrPtr @Cast({"uint8_t***"}) PointerPointer pointerPointer, int[] iArr, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_copy(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, @ByPtrPtr @Cast({"uint8_t*const*"}) ByteBuffer byteBuffer2, int i, int i2, int i3, int i4, @Cast({"AVSampleFormat"}) int i5);

    @NoException
    public static native int av_samples_copy(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, @ByPtrPtr @Cast({"uint8_t*const*"}) BytePointer bytePointer2, int i, int i2, int i3, int i4, @Cast({"AVSampleFormat"}) int i5);

    @NoException
    public static native int av_samples_copy(@Cast({"uint8_t**"}) PointerPointer pointerPointer, @Cast({"uint8_t*const*"}) PointerPointer pointerPointer2, int i, int i2, int i3, int i4, @Cast({"AVSampleFormat"}) int i5);

    @NoException
    public static native int av_samples_copy(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, @ByPtrPtr @Cast({"uint8_t*const*"}) byte[] bArr2, int i, int i2, int i3, int i4, @Cast({"AVSampleFormat"}) int i5);

    @NoException
    public static native int av_samples_fill_arrays(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, IntBuffer intBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_fill_arrays(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, IntPointer intPointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_fill_arrays(@Cast({"uint8_t**"}) PointerPointer pointerPointer, IntPointer intPointer, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_fill_arrays(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, int[] iArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_get_buffer_size(IntBuffer intBuffer, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_get_buffer_size(IntPointer intPointer, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_get_buffer_size(int[] iArr, int i, int i2, @Cast({"AVSampleFormat"}) int i3, int i4);

    @NoException
    public static native int av_samples_set_silence(@ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, int i, int i2, int i3, @Cast({"AVSampleFormat"}) int i4);

    @NoException
    public static native int av_samples_set_silence(@ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, int i, int i2, int i3, @Cast({"AVSampleFormat"}) int i4);

    @NoException
    public static native int av_samples_set_silence(@Cast({"uint8_t**"}) PointerPointer pointerPointer, int i, int i2, int i3, @Cast({"AVSampleFormat"}) int i4);

    @NoException
    public static native int av_samples_set_silence(@ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, int i, int i2, int i3, @Cast({"AVSampleFormat"}) int i4);

    @NoException
    public static native int av_sat_add32_c(int i, int i2);

    @NoException
    @Cast({"int64_t"})
    public static native long av_sat_add64_c(@Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2);

    @NoException
    public static native int av_sat_dadd32_c(int i, int i2);

    @NoException
    public static native int av_sat_dsub32_c(int i, int i2);

    @NoException
    public static native int av_sat_sub32_c(int i, int i2);

    @NoException
    @Cast({"int64_t"})
    public static native long av_sat_sub64_c(@Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2);

    @NoException
    @Deprecated
    public static native void av_set_cpu_flags_mask(int i);

    @NoException
    public static native int av_set_options_string(Pointer pointer, String str, String str2, String str3);

    @NoException
    public static native int av_set_options_string(Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3);

    @NoException
    public static native AVSHA512 av_sha512_alloc();

    @NoException
    public static native void av_sha512_final(AVSHA512 avsha512, @Cast({"uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_sha512_final(AVSHA512 avsha512, @Cast({"uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_sha512_final(AVSHA512 avsha512, @Cast({"uint8_t*"}) byte[] bArr);

    @NoException
    public static native int av_sha512_init(AVSHA512 avsha512, int i);

    @MemberGetter
    public static native int av_sha512_size();

    @NoException
    public static native void av_sha512_update(AVSHA512 avsha512, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_sha512_update(AVSHA512 avsha512, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_sha512_update(AVSHA512 avsha512, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_sha512_update(AVSHA512 avsha512, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_sha512_update(AVSHA512 avsha512, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_sha512_update(AVSHA512 avsha512, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    public static native AVSHA av_sha_alloc();

    @NoException
    public static native void av_sha_final(AVSHA avsha, @Cast({"uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_sha_final(AVSHA avsha, @Cast({"uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_sha_final(AVSHA avsha, @Cast({"uint8_t*"}) byte[] bArr);

    @NoException
    public static native int av_sha_init(AVSHA avsha, int i);

    @MemberGetter
    public static native int av_sha_size();

    @NoException
    public static native void av_sha_update(AVSHA avsha, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_sha_update(AVSHA avsha, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_sha_update(AVSHA avsha, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_sha_update(AVSHA avsha, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void av_sha_update(AVSHA avsha, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_sha_update(AVSHA avsha, @Cast({"const uint8_t*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_size_mult(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_small_strptime(String str, String str2, tm tmVar);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_small_strptime(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, tm tmVar);

    @NoException
    public static native AVSphericalMapping av_spherical_alloc(@Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @NoException
    public static native int av_spherical_from_name(String str);

    @NoException
    public static native int av_spherical_from_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_spherical_projection_name(@Cast({"AVSphericalProjection"}) int i);

    @NoException
    public static native void av_spherical_tile_bounds(@Const AVSphericalMapping aVSphericalMapping, @Cast({"size_t"}) long j, @Cast({"size_t"}) long j2, @Cast({"size_t*"}) SizeTPointer sizeTPointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer2, @Cast({"size_t*"}) SizeTPointer sizeTPointer3, @Cast({"size_t*"}) SizeTPointer sizeTPointer4);

    @NoException
    public static native int av_sscanf(String str, String str2);

    @NoException
    public static native int av_sscanf(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    public static native AVStereo3D av_stereo3d_alloc();

    @NoException
    public static native AVStereo3D av_stereo3d_create_side_data(AVFrame aVFrame);

    @NoException
    public static native int av_stereo3d_from_name(String str);

    @NoException
    public static native int av_stereo3d_from_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_stereo3d_type_name(@Cast({"unsigned int"}) int i);

    @NoException
    public static native int av_strcasecmp(String str, String str2);

    @NoException
    public static native int av_strcasecmp(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_strdup(String str);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_strdup(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int av_strerror(int i, @Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_strerror(int i, @Cast({"char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_strerror(int i, @Cast({"char*"}) byte[] bArr, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_strireplace(String str, String str2, String str3);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_strireplace(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3);

    @NoException
    public static native int av_stristart(String str, String str2, @ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int av_stristart(String str, String str2, @ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer);

    @NoException
    public static native int av_stristart(String str, String str2, @ByPtrPtr @Cast({"const char**"}) byte[] bArr);

    @NoException
    public static native int av_stristart(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int av_stristart(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer3);

    @NoException
    public static native int av_stristart(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_stristart(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"const char**"}) byte[] bArr);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_stristr(String str, String str2);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_stristr(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcat(@Cast({"char*"}) ByteBuffer byteBuffer, String str, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcat(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcat(@Cast({"char*"}) BytePointer bytePointer, String str, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcat(@Cast({"char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcat(@Cast({"char*"}) byte[] bArr, String str, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcat(@Cast({"char*"}) byte[] bArr, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcatf(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, String str);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcatf(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcatf(@Cast({"char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, String str);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcatf(@Cast({"char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const char*"}) BytePointer bytePointer2);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcatf(@Cast({"char*"}) byte[] bArr, @Cast({"size_t"}) long j, String str);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcatf(@Cast({"char*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcpy(@Cast({"char*"}) ByteBuffer byteBuffer, String str, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcpy(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcpy(@Cast({"char*"}) BytePointer bytePointer, String str, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcpy(@Cast({"char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcpy(@Cast({"char*"}) byte[] bArr, String str, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strlcpy(@Cast({"char*"}) byte[] bArr, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_strncasecmp(String str, String str2, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_strncasecmp(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_strndup(String str, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_strndup(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strnlen(String str, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"size_t"})
    public static native long av_strnlen(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_strnstr(String str, String str2, @Cast({"size_t"}) long j);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_strnstr(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j);

    @NoException
    public static native int av_strstart(String str, String str2, @ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int av_strstart(String str, String str2, @ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer);

    @NoException
    public static native int av_strstart(String str, String str2, @ByPtrPtr @Cast({"const char**"}) byte[] bArr);

    @NoException
    public static native int av_strstart(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"const char**"}) ByteBuffer byteBuffer);

    @NoException
    public static native int av_strstart(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"const char**"}) BytePointer bytePointer3);

    @NoException
    public static native int av_strstart(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_strstart(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"const char**"}) byte[] bArr);

    @NoException
    public static native double av_strtod(String str, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer);

    @NoException
    public static native double av_strtod(String str, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer);

    @NoException
    public static native double av_strtod(String str, @ByPtrPtr @Cast({"char**"}) byte[] bArr);

    @NoException
    public static native double av_strtod(@Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer);

    @NoException
    public static native double av_strtod(@Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer2);

    @NoException
    public static native double av_strtod(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"char**"}) PointerPointer pointerPointer);

    @NoException
    public static native double av_strtod(@Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"char**"}) byte[] bArr);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_strtok(@Cast({"char*"}) ByteBuffer byteBuffer, String str, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer2);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_strtok(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer2);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_strtok(@Cast({"char*"}) BytePointer bytePointer, String str, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer2);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_strtok(@Cast({"char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer3);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_strtok(@Cast({"char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"char**"}) PointerPointer pointerPointer);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_strtok(@Cast({"char*"}) byte[] bArr, String str, @ByPtrPtr @Cast({"char**"}) byte[] bArr2);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_strtok(@Cast({"char*"}) byte[] bArr, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"char**"}) byte[] bArr2);

    @NoException
    @Const
    @ByVal
    public static native AVRational av_sub_q(@ByVal AVRational aVRational, @ByVal AVRational aVRational2);

    @NoException
    public static native AVTEA av_tea_alloc();

    @NoException
    public static native void av_tea_crypt(AVTEA avtea, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_tea_crypt(AVTEA avtea, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_tea_crypt(AVTEA avtea, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native void av_tea_init(AVTEA avtea, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native void av_tea_init(AVTEA avtea, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native void av_tea_init(AVTEA avtea, @Cast({"const uint8_t*"}) byte[] bArr, int i);

    @MemberGetter
    public static native int av_tea_size();

    @NoException
    public static native int av_tempfile(String str, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer, int i, Pointer pointer);

    @NoException
    public static native int av_tempfile(String str, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer, int i, Pointer pointer);

    @NoException
    public static native int av_tempfile(String str, @ByPtrPtr @Cast({"char**"}) byte[] bArr, int i, Pointer pointer);

    @NoException
    public static native int av_tempfile(@Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer, int i, Pointer pointer);

    @NoException
    public static native int av_tempfile(@Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"char**"}) BytePointer bytePointer2, int i, Pointer pointer);

    @NoException
    public static native int av_tempfile(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"char**"}) PointerPointer pointerPointer, int i, Pointer pointer);

    @NoException
    public static native int av_tempfile(@Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr @Cast({"char**"}) byte[] bArr, int i, Pointer pointer);

    @NoException
    public static native void av_thread_message_flush(AVThreadMessageQueue aVThreadMessageQueue);

    @NoException
    public static native int av_thread_message_queue_alloc(@ByPtrPtr AVThreadMessageQueue aVThreadMessageQueue, @Cast({"unsigned"}) int i, @Cast({"unsigned"}) int i2);

    @NoException
    public static native int av_thread_message_queue_alloc(@Cast({"AVThreadMessageQueue**"}) PointerPointer pointerPointer, @Cast({"unsigned"}) int i, @Cast({"unsigned"}) int i2);

    @NoException
    public static native void av_thread_message_queue_free(@ByPtrPtr AVThreadMessageQueue aVThreadMessageQueue);

    @NoException
    public static native void av_thread_message_queue_free(@Cast({"AVThreadMessageQueue**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_thread_message_queue_nb_elems(AVThreadMessageQueue aVThreadMessageQueue);

    @NoException
    public static native int av_thread_message_queue_recv(AVThreadMessageQueue aVThreadMessageQueue, Pointer pointer, @Cast({"unsigned"}) int i);

    @NoException
    public static native int av_thread_message_queue_send(AVThreadMessageQueue aVThreadMessageQueue, Pointer pointer, @Cast({"unsigned"}) int i);

    @NoException
    public static native void av_thread_message_queue_set_err_recv(AVThreadMessageQueue aVThreadMessageQueue, int i);

    @NoException
    public static native void av_thread_message_queue_set_err_send(AVThreadMessageQueue aVThreadMessageQueue, int i);

    @NoException
    public static native void av_thread_message_queue_set_free_func(AVThreadMessageQueue aVThreadMessageQueue, Free_func_Pointer free_func_Pointer);

    @NoException
    public static native int av_timecode_adjust_ntsc_framenum2(int i, int i2);

    @NoException
    public static native int av_timecode_check_frame_rate(@ByVal AVRational aVRational);

    @NoException
    @Cast({"uint32_t"})
    public static native int av_timecode_get_smpte_from_framenum(@Const AVTimecode aVTimecode, int i);

    @NoException
    public static native int av_timecode_init(AVTimecode aVTimecode, @ByVal AVRational aVRational, int i, int i2, Pointer pointer);

    @NoException
    public static native int av_timecode_init_from_string(AVTimecode aVTimecode, @ByVal AVRational aVRational, String str, Pointer pointer);

    @NoException
    public static native int av_timecode_init_from_string(AVTimecode aVTimecode, @ByVal AVRational aVRational, @Cast({"const char*"}) BytePointer bytePointer, Pointer pointer);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_timecode_make_mpeg_tc_string(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"uint32_t"}) int i);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_timecode_make_mpeg_tc_string(@Cast({"char*"}) BytePointer bytePointer, @Cast({"uint32_t"}) int i);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_timecode_make_mpeg_tc_string(@Cast({"char*"}) byte[] bArr, @Cast({"uint32_t"}) int i);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_timecode_make_smpte_tc_string(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"uint32_t"}) int i, int i2);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_timecode_make_smpte_tc_string(@Cast({"char*"}) BytePointer bytePointer, @Cast({"uint32_t"}) int i, int i2);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_timecode_make_smpte_tc_string(@Cast({"char*"}) byte[] bArr, @Cast({"uint32_t"}) int i, int i2);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_timecode_make_string(@Const AVTimecode aVTimecode, @Cast({"char*"}) ByteBuffer byteBuffer, int i);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_timecode_make_string(@Const AVTimecode aVTimecode, @Cast({"char*"}) BytePointer bytePointer, int i);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_timecode_make_string(@Const AVTimecode aVTimecode, @Cast({"char*"}) byte[] bArr, int i);

    @NoException
    @Cast({"time_t*"})
    @ByVal
    public static native Pointer av_timegm(tm tmVar);

    @NoException
    @Const
    public static native int av_tolower(int i);

    @NoException
    @Const
    public static native int av_toupper(int i);

    @NoException
    public static native void av_tree_destroy(AVTreeNode aVTreeNode);

    @NoException
    public static native void av_tree_enumerate(AVTreeNode aVTreeNode, Pointer pointer, Cmp_Pointer_Pointer cmp_Pointer_Pointer, Enu_Pointer_Pointer enu_Pointer_Pointer);

    @NoException
    public static native Pointer av_tree_find(@Const AVTreeNode aVTreeNode, Pointer pointer, @Cast({"int (*)(const void*, const void*)"}) Cmp_Const_Pointer_Const_Pointer cmp_Const_Pointer_Const_Pointer, @ByPtrPtr @Cast({"void**"}) Pointer pointer2);

    @NoException
    public static native Pointer av_tree_find(@Const AVTreeNode aVTreeNode, Pointer pointer, @Cast({"int (*)(const void*, const void*)"}) Cmp_Const_Pointer_Const_Pointer cmp_Const_Pointer_Const_Pointer, @Cast({"void**"}) PointerPointer pointerPointer);

    @NoException
    public static native Pointer av_tree_insert(@ByPtrPtr AVTreeNode aVTreeNode, Pointer pointer, @Cast({"int (*)(const void*, const void*)"}) Cmp_Const_Pointer_Const_Pointer cmp_Const_Pointer_Const_Pointer, @ByPtrPtr AVTreeNode aVTreeNode2);

    @NoException
    public static native Pointer av_tree_insert(@Cast({"AVTreeNode**"}) PointerPointer pointerPointer, Pointer pointer, @Cast({"int (*)(const void*, const void*)"}) Cmp_Const_Pointer_Const_Pointer cmp_Const_Pointer_Const_Pointer, @Cast({"AVTreeNode**"}) PointerPointer pointerPointer2);

    @NoException
    public static native AVTreeNode av_tree_node_alloc();

    @MemberGetter
    public static native int av_tree_node_size();

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_ts_make_string(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"int64_t"}) long j);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_ts_make_string(@Cast({"char*"}) BytePointer bytePointer, @Cast({"int64_t"}) long j);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_ts_make_string(@Cast({"char*"}) byte[] bArr, @Cast({"int64_t"}) long j);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer av_ts_make_time_string(@Cast({"char*"}) ByteBuffer byteBuffer, @Cast({"int64_t"}) long j, AVRational aVRational);

    @NoException
    @Cast({"char*"})
    public static native BytePointer av_ts_make_time_string(@Cast({"char*"}) BytePointer bytePointer, @Cast({"int64_t"}) long j, AVRational aVRational);

    @NoException
    @Cast({"char*"})
    public static native byte[] av_ts_make_time_string(@Cast({"char*"}) byte[] bArr, @Cast({"int64_t"}) long j, AVRational aVRational);

    @NoException
    public static native AVTWOFISH av_twofish_alloc();

    @NoException
    public static native void av_twofish_crypt(AVTWOFISH avtwofish, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_twofish_crypt(AVTWOFISH avtwofish, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_twofish_crypt(AVTWOFISH avtwofish, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native int av_twofish_init(AVTWOFISH avtwofish, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer, int i);

    @NoException
    public static native int av_twofish_init(AVTWOFISH avtwofish, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

    @NoException
    public static native int av_twofish_init(AVTWOFISH avtwofish, @Cast({"const uint8_t*"}) byte[] bArr, int i);

    @MemberGetter
    public static native int av_twofish_size();

    @NoException
    public static native int av_tx_init(@ByPtrPtr AVTXContext aVTXContext, @ByPtrPtr av_tx_fn av_tx_fn, @Cast({"AVTXType"}) int i, int i2, int i3, @Const Pointer pointer, @Cast({"uint64_t"}) long j);

    @NoException
    public static native int av_tx_init(@Cast({"AVTXContext**"}) PointerPointer pointerPointer, @ByPtrPtr av_tx_fn av_tx_fn, @Cast({"AVTXType"}) int i, int i2, int i3, @Const Pointer pointer, @Cast({"uint64_t"}) long j);

    @NoException
    public static native void av_tx_uninit(@ByPtrPtr AVTXContext aVTXContext);

    @NoException
    public static native void av_tx_uninit(@Cast({"AVTXContext**"}) PointerPointer pointerPointer);

    @NoException
    public static native int av_usleep(@Cast({"unsigned"}) int i);

    @NoException
    public static native int av_utf8_decode(IntBuffer intBuffer, @ByPtrPtr @Cast({"const uint8_t**"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, @Cast({"unsigned int"}) int i);

    @NoException
    public static native int av_utf8_decode(IntPointer intPointer, @ByPtrPtr @Cast({"const uint8_t**"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, @Cast({"unsigned int"}) int i);

    @NoException
    public static native int av_utf8_decode(IntPointer intPointer, @Cast({"const uint8_t**"}) PointerPointer pointerPointer, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

    @NoException
    public static native int av_utf8_decode(int[] iArr, @ByPtrPtr @Cast({"const uint8_t**"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, @Cast({"unsigned int"}) int i);

    @NoException
    public static native void av_vbprintf(AVBPrint aVBPrint, String str, @Cast({"va_list*"}) @ByVal Pointer pointer);

    @NoException
    public static native void av_vbprintf(AVBPrint aVBPrint, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer av_version_info();

    @NoException
    public static native void av_vlog(Pointer pointer, int i, String str, @Cast({"va_list*"}) @ByVal Pointer pointer2);

    @NoException
    public static native void av_vlog(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2);

    @NoException
    public static native void av_write_image_line(@Cast({"const uint16_t*"}) ShortBuffer shortBuffer, @ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, @Const IntBuffer intBuffer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4);

    @NoException
    public static native void av_write_image_line(@Cast({"const uint16_t*"}) ShortPointer shortPointer, @ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, @Const IntPointer intPointer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4);

    @NoException
    public static native void av_write_image_line(@Cast({"const uint16_t*"}) ShortPointer shortPointer, @Cast({"uint8_t**"}) PointerPointer pointerPointer, @Const IntPointer intPointer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4);

    @NoException
    public static native void av_write_image_line(@Cast({"const uint16_t*"}) short[] sArr, @ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, @Const int[] iArr, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4);

    @NoException
    public static native void av_write_image_line2(@Const Pointer pointer, @ByPtrPtr @Cast({"uint8_t**"}) ByteBuffer byteBuffer, @Const IntBuffer intBuffer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5);

    @NoException
    public static native void av_write_image_line2(@Const Pointer pointer, @ByPtrPtr @Cast({"uint8_t**"}) BytePointer bytePointer, @Const IntPointer intPointer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5);

    @NoException
    public static native void av_write_image_line2(@Const Pointer pointer, @Cast({"uint8_t**"}) PointerPointer pointerPointer, @Const IntPointer intPointer, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5);

    @NoException
    public static native void av_write_image_line2(@Const Pointer pointer, @ByPtrPtr @Cast({"uint8_t**"}) byte[] bArr, @Const int[] iArr, @Const AVPixFmtDescriptor aVPixFmtDescriptor, int i, int i2, int i3, int i4, int i5);

    @NoException
    public static native Pointer av_x_if_null(@Const Pointer pointer, @Const Pointer pointer2);

    @NoException
    public static native AVXTEA av_xtea_alloc();

    @NoException
    public static native void av_xtea_crypt(AVXTEA avxtea, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_xtea_crypt(AVXTEA avxtea, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_xtea_crypt(AVXTEA avxtea, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native void av_xtea_init(AVXTEA avxtea, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_xtea_init(AVXTEA avxtea, @Cast({"const uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_xtea_init(AVXTEA avxtea, @Cast({"const uint8_t*"}) byte[] bArr);

    @NoException
    public static native void av_xtea_le_crypt(AVXTEA avxtea, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer2, int i, @Cast({"uint8_t*"}) ByteBuffer byteBuffer3, int i2);

    @NoException
    public static native void av_xtea_le_crypt(AVXTEA avxtea, @Cast({"uint8_t*"}) BytePointer bytePointer, @Cast({"const uint8_t*"}) BytePointer bytePointer2, int i, @Cast({"uint8_t*"}) BytePointer bytePointer3, int i2);

    @NoException
    public static native void av_xtea_le_crypt(AVXTEA avxtea, @Cast({"uint8_t*"}) byte[] bArr, @Cast({"const uint8_t*"}) byte[] bArr2, int i, @Cast({"uint8_t*"}) byte[] bArr3, int i2);

    @NoException
    public static native void av_xtea_le_init(AVXTEA avxtea, @Cast({"const uint8_t*"}) ByteBuffer byteBuffer);

    @NoException
    public static native void av_xtea_le_init(AVXTEA avxtea, @Cast({"const uint8_t*"}) BytePointer bytePointer);

    @NoException
    public static native void av_xtea_le_init(AVXTEA avxtea, @Cast({"const uint8_t*"}) byte[] bArr);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avutil_configuration();

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avutil_license();

    @NoException
    @Cast({"unsigned"})
    public static native int avutil_version();

    public static native LogCallback logCallback();

    public static native void logCallback(LogCallback logCallback);

    @NoException
    public static native void log_callback(Pointer pointer, int i, String str, @Cast({"va_list*"}) @ByVal Pointer pointer2);

    @NoException
    public static native void log_callback(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2);

    @NoException
    public static native void setLogCallback(LogCallback logCallback);

    static {
        Loader.load();
    }
}
