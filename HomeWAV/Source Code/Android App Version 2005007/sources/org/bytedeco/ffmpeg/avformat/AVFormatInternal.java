package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
@Opaque
public class AVFormatInternal extends Pointer {
    public AVFormatInternal() {
        super((Pointer) null);
    }

    public AVFormatInternal(Pointer pointer) {
        super(pointer);
    }
}
