package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
@Opaque
public class AVFilterFormats extends Pointer {
    public AVFilterFormats() {
        super((Pointer) null);
    }

    public AVFilterFormats(Pointer pointer) {
        super(pointer);
    }
}
