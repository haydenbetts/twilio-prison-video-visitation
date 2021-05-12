package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
@Opaque
public class AVFilterPad extends Pointer {
    public AVFilterPad() {
        super((Pointer) null);
    }

    public AVFilterPad(Pointer pointer) {
        super(pointer);
    }
}
