package org.bytedeco.ffmpeg.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.ffmpeg.global.avfilter", inherit = {avformat.class, postproc.class, swresample.class, swscale.class}, target = "org.bytedeco.ffmpeg.avfilter", value = {@Platform(cinclude = {"<libavfilter/avfilter.h>", "<libavfilter/buffersink.h>", "<libavfilter/buffersrc.h>"}, link = {"avfilter@.7"}), @Platform(preload = {"avfilter-7"}, value = {"windows"})})
public class avfilter implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("AVFilterPool", "AVFilterCommand", "AVFilterChannelLayouts", "FFFrameQueue").cast().pointerTypes("Pointer")).put(new Info("AV_HAVE_INCOMPATIBLE_LIBAV_ABI || !FF_API_OLD_GRAPH_PARSE").define(true)).put(new Info("!FF_API_FOO_COUNT", "FF_INTERNAL_FIELDS").define(false));
    }
}
