package org.bytedeco.ffmpeg.presets;

import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.ffmpeg.global.avutil", inherit = {javacpp.class}, target = "org.bytedeco.ffmpeg.avutil", value = {@Platform(cinclude = {"<libavutil/avutil.h>", "<libavutil/error.h>", "<libavutil/mem.h>", "<libavutil/time.h>", "<libavutil/mathematics.h>", "<libavutil/rational.h>", "<libavutil/log.h>", "<libavutil/buffer.h>", "<libavutil/pixfmt.h>", "<libavutil/frame.h>", "<libavutil/samplefmt.h>", "<libavutil/channel_layout.h>", "<libavutil/cpu.h>", "<libavutil/dict.h>", "<libavutil/opt.h>", "<libavutil/pixdesc.h>", "<libavutil/imgutils.h>", "<libavutil/downmix_info.h>", "<libavutil/stereo3d.h>", "<libavutil/ffversion.h>", "<libavutil/motion_vector.h>", "<libavutil/fifo.h>", "<libavutil/audio_fifo.h>", "<libavutil/hwcontext.h>", "<libavutil/adler32.h>", "<libavutil/aes.h>", "<libavutil/aes_ctr.h>", "<libavutil/base64.h>", "<libavutil/blowfish.h>", "<libavutil/cast5.h>", "<libavutil/camellia.h>", "<libavutil/crc.h>", "<libavutil/des.h>", "<libavutil/lfg.h>", "<libavutil/hmac.h>", "<libavutil/md5.h>", "<libavutil/rc4.h>", "<libavutil/ripemd.h>", "<libavutil/tea.h>", "<libavutil/twofish.h>", "<libavutil/sha.h>", "<libavutil/sha512.h>", "<libavutil/xtea.h>", "<libavutil/avstring.h>", "<libavutil/bprint.h>", "<libavutil/common.h>", "<libavutil/display.h>", "<libavutil/eval.h>", "<libavutil/encryption_info.h>", "<libavutil/file.h>", "<libavutil/hash.h>", "<libavutil/hdr_dynamic_metadata.h>", "<libavutil/intfloat.h>", "<libavutil/intreadwrite.h>", "<libavutil/mastering_display_metadata.h>", "<libavutil/murmur3.h>", "<libavutil/parseutils.h>", "<libavutil/pixelutils.h>", "<libavutil/random_seed.h>", "<libavutil/replaygain.h>", "<libavutil/spherical.h>", "<libavutil/threadmessage.h>", "<libavutil/timecode.h>", "<libavutil/timestamp.h>", "<libavutil/tree.h>", "<libavutil/tx.h>", "log_callback.h"}, compiler = {"default", "nodeprecated"}, define = {"__STDC_CONSTANT_MACROS", "__STDC_FORMAT_MACROS"}, includepath = {"/usr/local/include/ffmpeg/", "/opt/local/include/ffmpeg/", "/usr/include/ffmpeg/"}, link = {"avutil@.56"}), @Platform(includepath = {"C:/MinGW/local/include/ffmpeg/", "C:/MinGW/include/ffmpeg/"}, preload = {"avutil-56"}, value = {"windows"})})
public class avutil implements InfoMapper {
    @MemberGetter
    @Name({"AVERROR(EACCES)"})
    public static native int AVERROR_EACCES();

    @MemberGetter
    @Name({"AVERROR(EAGAIN)"})
    public static native int AVERROR_EAGAIN();

    @MemberGetter
    @Name({"AVERROR(EBADF)"})
    public static native int AVERROR_EBADF();

    @MemberGetter
    @Name({"AVERROR(EDOM)"})
    public static native int AVERROR_EDOM();

    @MemberGetter
    @Name({"AVERROR(EEXIST)"})
    public static native int AVERROR_EEXIST();

    @MemberGetter
    @Name({"AVERROR(EFAULT)"})
    public static native int AVERROR_EFAULT();

    @MemberGetter
    @Name({"AVERROR(EFBIG)"})
    public static native int AVERROR_EFBIG();

    @MemberGetter
    @Name({"AVERROR(EILSEQ)"})
    public static native int AVERROR_EILSEQ();

    @MemberGetter
    @Name({"AVERROR(EINTR)"})
    public static native int AVERROR_EINTR();

    @MemberGetter
    @Name({"AVERROR(EINVAL)"})
    public static native int AVERROR_EINVAL();

    @MemberGetter
    @Name({"AVERROR(EIO)"})
    public static native int AVERROR_EIO();

    @MemberGetter
    @Name({"AVERROR(ENAMETOOLONG)"})
    public static native int AVERROR_ENAMETOOLONG();

    @MemberGetter
    @Name({"AVERROR(ENODEV)"})
    public static native int AVERROR_ENODEV();

    @MemberGetter
    @Name({"AVERROR(ENOENT)"})
    public static native int AVERROR_ENOENT();

    @MemberGetter
    @Name({"AVERROR(ENOMEM)"})
    public static native int AVERROR_ENOMEM();

    @MemberGetter
    @Name({"AVERROR(ENOSPC)"})
    public static native int AVERROR_ENOSPC();

    @MemberGetter
    @Name({"AVERROR(ENOSYS)"})
    public static native int AVERROR_ENOSYS();

    @MemberGetter
    @Name({"AVERROR(ENXIO)"})
    public static native int AVERROR_ENXIO();

    @MemberGetter
    @Name({"AVERROR(EPERM)"})
    public static native int AVERROR_EPERM();

    @MemberGetter
    @Name({"AVERROR(EPIPE)"})
    public static native int AVERROR_EPIPE();

    @MemberGetter
    @Name({"AVERROR(ERANGE)"})
    public static native int AVERROR_ERANGE();

    @MemberGetter
    @Name({"AVERROR(ESPIPE)"})
    public static native int AVERROR_ESPIPE();

    @MemberGetter
    @Name({"AVERROR(EXDEV)"})
    public static native int AVERROR_EXDEV();

    static {
        Loader.checkVersion("org.bytedeco", "ffmpeg");
    }

    public void map(InfoMap infoMap) {
        infoMap.put(new Info("AV_NOPTS_VALUE").cppTypes("int64_t").translate(false)).put(new Info("NAN", "INFINITY").cppTypes(DoubleTypedProperty.TYPE)).put(new Info("AV_TIME_BASE_Q", "PixelFormat", "CodecID", "AVCOL_SPC_YCGCO", "AVCOL_SPC_YCOCG", "FF_CEIL_RSHIFT", "av_ceil_log2", "av_clip", "av_clip64", "av_clip_uint8", "av_clip_int8", "av_clip_uint16", "av_clip_int16", "av_clipl_int32", "av_clip_intp2", "av_clip_uintp2", "av_mod_uintp2", "av_sat_add32", "av_sat_dadd32", "av_sat_sub32", "av_sat_dsub32", "av_clipf", "av_clipd", "av_popcount", "av_popcount64", "av_parity", "av_sat_add64", "av_sat_sub64").cppTypes(new String[0]).translate()).put(new Info("av_const").annotations("@Const")).put(new Info("FF_CONST_AVUTIL55").annotations(new String[0])).put(new Info("av_malloc_attrib", "av_alloc_size", "av_always_inline", "av_warn_unused_result", "av_alias").cppTypes(new String[0]).annotations(new String[0])).put(new Info("attribute_deprecated").annotations("@Deprecated")).put(new Info("DWORD", "UINT").cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]")).put(new Info("AVPanScan", "AVCodecContext", "AVMurMur3", "CUcontext", "CUstream", "ID3D11Device", "ID3D11DeviceContext", "ID3D11Texture2D", "ID3D11VideoContext", "ID3D11VideoDevice", "IDirect3DDeviceManager9", "IDirect3DSurface9", "IDirectXVideoDecoder", "mfxFrameSurface1", "mfxSession", "VAConfigID", "VASurfaceID", "VASurfaceAttrib", "VADisplay", "VdpDevice", "VdpGetProcAddress").cast().pointerTypes("Pointer")).put(new Info("FF_API_VAAPI").define()).put(new Info("AV_PIX_FMT_ABI_GIT_MASTER", "AV_HAVE_INCOMPATIBLE_LIBAV_ABI", "!FF_API_XVMC", "FF_API_GET_BITS_PER_SAMPLE_FMT", "FF_API_FIND_OPT").define(false)).put(new Info("ff_check_pixfmt_descriptors").skip()).put(new Info("AV_CH_FRONT_LEFT", "AV_CH_FRONT_RIGHT", "AV_CH_FRONT_CENTER", "AV_CH_LOW_FREQUENCY", "AV_CH_BACK_LEFT", "AV_CH_BACK_RIGHT", "AV_CH_FRONT_LEFT_OF_CENTER", "AV_CH_FRONT_RIGHT_OF_CENTER", "AV_CH_BACK_CENTER", "AV_CH_SIDE_LEFT", "AV_CH_SIDE_RIGHT", "AV_CH_TOP_CENTER", "AV_CH_TOP_FRONT_LEFT", "AV_CH_TOP_FRONT_CENTER", "AV_CH_TOP_FRONT_RIGHT", "AV_CH_TOP_BACK_LEFT", "AV_CH_TOP_BACK_CENTER", "AV_CH_TOP_BACK_RIGHT", "AV_CH_STEREO_LEFT", "AV_CH_STEREO_RIGHT", "AV_CH_WIDE_LEFT", "AV_CH_WIDE_RIGHT", "AV_CH_SURROUND_DIRECT_LEFT", "AV_CH_SURROUND_DIRECT_RIGHT", "AV_CH_LOW_FREQUENCY_2", "AV_CH_LAYOUT_NATIVE", "AV_CH_LAYOUT_MONO", "AV_CH_LAYOUT_STEREO", "AV_CH_LAYOUT_2POINT1", "AV_CH_LAYOUT_2_1", "AV_CH_LAYOUT_SURROUND", "AV_CH_LAYOUT_3POINT1", "AV_CH_LAYOUT_4POINT0", "AV_CH_LAYOUT_4POINT1", "AV_CH_LAYOUT_2_2", "AV_CH_LAYOUT_QUAD", "AV_CH_LAYOUT_5POINT0", "AV_CH_LAYOUT_5POINT1", "AV_CH_LAYOUT_5POINT0_BACK", "AV_CH_LAYOUT_5POINT1_BACK", "AV_CH_LAYOUT_6POINT0", "AV_CH_LAYOUT_6POINT0_FRONT", "AV_CH_LAYOUT_HEXAGONAL", "AV_CH_LAYOUT_6POINT1", "AV_CH_LAYOUT_6POINT1_BACK", "AV_CH_LAYOUT_6POINT1_FRONT", "AV_CH_LAYOUT_7POINT0", "AV_CH_LAYOUT_7POINT0_FRONT", "AV_CH_LAYOUT_7POINT1", "AV_CH_LAYOUT_7POINT1_WIDE", "AV_CH_LAYOUT_7POINT1_WIDE_BACK", "AV_CH_LAYOUT_OCTAGONAL", "AV_CH_LAYOUT_HEXADECAGONAL", "AV_CH_LAYOUT_STEREO_DOWNMIX").translate().cppTypes(LongTypedProperty.TYPE)).put(new Info("MKTAG", "MKBETAG").cppTypes("int", "char", "char", "char", "char")).put(new Info("int (*)(const void*, const void*)").cast().pointerTypes("Cmp_Const_Pointer_Const_Pointer")).put(new Info("int (*)(void*, void*, int)").pointerTypes("Int_func_Pointer_Pointer_int"));
    }
}
