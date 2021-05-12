package org.bytedeco.ffmpeg.swresample;

import org.bytedeco.ffmpeg.presets.swresample;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {swresample.class})
@Opaque
public class SwrContext extends Pointer {
    public SwrContext() {
        super((Pointer) null);
    }

    public SwrContext(Pointer pointer) {
        super(pointer);
    }
}
