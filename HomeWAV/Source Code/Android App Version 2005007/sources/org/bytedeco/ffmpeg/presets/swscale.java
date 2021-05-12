package org.bytedeco.ffmpeg.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.ffmpeg.global.swscale", inherit = {avutil.class}, target = "org.bytedeco.ffmpeg.swscale", value = {@Platform(cinclude = {"<libswscale/swscale.h>"}, link = {"swscale@.5"}), @Platform(preload = {"swscale-5"}, value = {"windows"})})
public class swscale implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
