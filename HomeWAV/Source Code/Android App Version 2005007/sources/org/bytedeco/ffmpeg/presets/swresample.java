package org.bytedeco.ffmpeg.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.ffmpeg.global.swresample", inherit = {avutil.class}, target = "org.bytedeco.ffmpeg.swresample", value = {@Platform(cinclude = {"<libswresample/swresample.h>"}, link = {"swresample@.3"}), @Platform(preload = {"swresample-3"}, value = {"windows"})})
public class swresample implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
