package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
@Opaque
public class AVStreamInternal extends Pointer {
    public AVStreamInternal() {
        super((Pointer) null);
    }

    public AVStreamInternal(Pointer pointer) {
        super(pointer);
    }
}
