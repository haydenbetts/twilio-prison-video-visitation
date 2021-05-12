package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
@Opaque
public class AVCodecDefault extends Pointer {
    public AVCodecDefault() {
        super((Pointer) null);
    }

    public AVCodecDefault(Pointer pointer) {
        super(pointer);
    }
}
