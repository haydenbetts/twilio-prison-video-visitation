package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
@Opaque
public class AVFilterInternal extends Pointer {
    public AVFilterInternal() {
        super((Pointer) null);
    }

    public AVFilterInternal(Pointer pointer) {
        super(pointer);
    }
}
