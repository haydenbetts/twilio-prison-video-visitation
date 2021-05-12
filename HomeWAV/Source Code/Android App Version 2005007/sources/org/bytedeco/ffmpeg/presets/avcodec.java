package org.bytedeco.ffmpeg.presets;

import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.ffmpeg.global.avcodec", inherit = {swresample.class}, target = "org.bytedeco.ffmpeg.avcodec", value = {@Platform(cinclude = {"<libavcodec/codec_id.h>", "<libavcodec/codec_desc.h>", "<libavcodec/codec_par.h>", "<libavcodec/packet.h>", "<libavcodec/bsf.h>", "<libavcodec/codec.h>", "<libavcodec/avcodec.h>", "<libavcodec/jni.h>", "<libavcodec/avfft.h>"}, link = {"avcodec@.58"}), @Platform(preload = {"asound@.2", "vchiq_arm", "vcos", "vcsm", "bcm_host", "mmal_core", "mmal_util", "mmal_vc_client"}, value = {"linux-arm"}), @Platform(preload = {"avcodec-58"}, value = {"windows"})})
public class avcodec implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("!FF_API_LOWRES", "!FF_API_DEBUG_MV").define(false)).put(new Info("CODEC_FLAG_CLOSED_GOP").translate().cppTypes(LongTypedProperty.TYPE)).put(new Info("AVCodecHWConfigInternal").cast().pointerTypes("Pointer")).putFirst(new Info("AVPanScan").pointerTypes("AVPanScan")).putFirst(new Info("AVCodecContext").pointerTypes("AVCodecContext"));
    }
}
