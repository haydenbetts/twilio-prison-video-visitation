package org.bytedeco.ffmpeg;

import org.bytedeco.ffmpeg.presets.avdevice;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avdevice.class, avfilter.class}, value = {@Platform(executable = {"ffmpeg"})})
public class ffmpeg {
    static {
        Loader.load();
    }
}
