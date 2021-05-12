package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
@Opaque
public class FFTContext extends Pointer {
    public FFTContext() {
        super((Pointer) null);
    }

    public FFTContext(Pointer pointer) {
        super(pointer);
    }
}
